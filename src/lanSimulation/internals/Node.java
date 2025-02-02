/*   This file is part of lanSimulation.
 *
 *   lanSimulation is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation; either version 2 of the License, or
 *   (at your option) any later version.
 *
 *   lanSimulation is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with lanSimulation; if not, write to the Free Software
 *   Foundation, Inc. 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 *   Copyright original Java version: 2004 Bart Du Bois, Serge Demeyer
 *   Copyright C++ version: 2006 Matthias Rieger, Bart Van Rompaey
 */
package lanSimulation.internals;

import java.io.IOException;
import java.io.Writer;

import lanSimulation.Network;

/**
 * A <em>Node</em> represents a single Node in a Local Area Network (LAN).
 * Several types of Nodes exist.
 */
public class Node {
	// enumeration constants specifying all legal node types
	/**
	 * A node with type NODE has only basic functionality.
	 */
	public static final byte NODE = 0;
	/**
	 * A node with type WORKSTATION may initiate requests on the LAN.
	 */
	public static final byte WORKSTATION = 1;
	/**
	 * A node with type PRINTER may accept packages to be printed.
	 */
	public static final byte PRINTER = 2;

	/**
	 * Holds the type of the Node.
	 */
	//public byte type_;
	/**
	 * Holds the name of the Node.
	 */
	public String name_;
	/**
	 * Holds the next Node in the token ring architecture.
	 * 
	 * @see lanSimulation.internals.Node
	 */
	public Node nextNode_;

	/**
	 * Construct a <em>Node</em> with given #type and #name.
	 * <p>
	 * <strong>Precondition:</strong> (type >= NODE) & (type <= PRINTER);
	 * </p>
	 */
	public Node(String name) {
		//assert (type >= NODE) & (type <= PRINTER);
		//type_ = type;
		name_ = name;
		nextNode_ = null;
	}

	/**
	 * Construct a <em>Node</em> with given #type and #name, and which is linked to
	 * #nextNode.
	 * <p>
	 * <strong>Precondition:</strong> (type >= NODE) & (type <= PRINTER);
	 * </p>
	 */
	
	public Node(String name, Node nextNode) {
		//assert (type >= NODE) & (type <= PRINTER);
		//type_ = type;
		name_ = name;
		nextNode_ = nextNode;
	}
	


	public void logging(Writer report, boolean aceptar) {
		
		try {	
			if (aceptar) {
				report.write("\tNode '");
				report.write(name_);
				report.write("' accepts broadcase packet.\n");
			}
			
			report.write("\tNode '");
			report.write(name_);
			report.write("' passes packet on.\n");
			report.flush();


			
		} catch (IOException exc) {
				// just ignore
		}
		
	}

	public boolean printDocument(Network network, Packet document, Writer report) {

		try {
			report.write(">>> Destinition is not a printer, print job cancelled.\n\n");
			report.flush();
		} catch (IOException exc) {
			// just ignore
		}
		
		return false;
		
	}

	public boolean atDestination(Node destination) {
		return this != destination;
	}
	
	public Node nextNode() {
		return nextNode_;
	}

	/**
	 * Write a printable representation of #receiver on the given #buf.
	 * <p>
	 * <strong>Precondition:</strong> isInitialized();
	 * </p>
	 * @param network TODO
	 * @param buf TODO
	 */
	public void printOn(Network network, StringBuffer buf) {
		assert network.isInitialized();
		Node currentNode = this;
		Node destination = this;
		do {
			currentNode.printOn(buf);
			currentNode = currentNode.nextNode();
		} while (currentNode.atDestination(destination));
		buf.append(" ... ");
	}

	public void printOn(StringBuffer buf) {
		buf.append("Node ");
		buf.append(name_);
		buf.append(" [Node]");
		buf.append(" -> ");	
	}

	
	/**
	 * Write a HTML representation of #receiver on the given #buf.
	 * <p>
	 * <strong>Precondition:</strong> isInitialized();
	 * </p>
	 * @param network TODO
	 * @param buf TODO
	 */
	public void printHTMLOn(Network network, StringBuffer buf) {
		assert network.isInitialized();
	
		buf.append("<HTML>\n<HEAD>\n<TITLE>LAN Simulation</TITLE>\n</HEAD>\n<BODY>\n<H1>LAN SIMULATION</H1>");
		Node currentNode = this;
		Node destination = this;
		buf.append("\n\n<UL>");
		do {
			currentNode.printHTMLOn(buf);
			currentNode = currentNode.nextNode();
		} while (currentNode.atDestination(destination));
		buf.append("\n\t<LI>...</LI>\n</UL>\n\n</BODY>\n</HTML>\n");
	}

	public void printHTMLOn(StringBuffer buf) {
		buf.append("\n\t<LI> ");
		buf.append("Node ");
		buf.append(name_);
		buf.append(" [Node]");
		buf.append(" </LI>");
	}

	/**
	 * Write an XML representation of #receiver on the given #buf.
	 * <p>
	 * <strong>Precondition:</strong> isInitialized();
	 * </p>
	 * @param network TODO
	 * @param buf TODO
	 */
	public void printXMLOn(Network network, StringBuffer buf) {
		assert network.isInitialized();
	
		Node currentNode = this;
		Node destination = this;
		buf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n<network>");
		do {
			currentNode.printXMLOn(buf);
			currentNode = currentNode.nextNode();
		} while (currentNode.atDestination(destination));
		buf.append("\n</network>");
	}

	public void printXMLOn(StringBuffer buf) {
		buf.append("\n\t");
		buf.append("<node>");
		buf.append(name_);
		buf.append("</node>");

	}

}