package lanSimulation.internals;

import java.io.IOException;
import java.io.Writer;

import lanSimulation.Network;

public class Printer extends Node {

	public Printer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void printOn(StringBuffer buf) {
		buf.append("Printer ");
		buf.append(name_);
		buf.append(" [Printer]");
		buf.append(" -> ");
	}
	
	@Override
	public void printHTMLOn(StringBuffer buf) {
		buf.append("\n\t<LI> ");
		buf.append("Printer ");
		buf.append(name_);
		buf.append(" [Printer]");
		buf.append(" </LI>");
	}
	
	@Override
	public void printXMLOn(StringBuffer buf) {
		buf.append("\n\t");
		buf.append("<printer>");
		buf.append(name_);
		buf.append("</printer>");

	}
	
	@Override
	public boolean printDocument(Network network, Packet document, Writer report) {
		String author = "Unknown";
		String title = "Untitled";
		int startPos = 0, endPos = 0;
		String cadena= "";
	
		
		try {
			if (document.message_.startsWith("!PS")) {
				startPos = document.message_.indexOf("author:");
				if (startPos >= 0) {
					endPos = document.message_.indexOf(".", startPos + 7);
					if (endPos < 0) {
						endPos = document.message_.length();
					}
					;
					author = document.message_.substring(startPos + 7, endPos);
				}
				;
				startPos = document.message_.indexOf("title:");
				if (startPos >= 0) {
					endPos = document.message_.indexOf(".", startPos + 6);
					if (endPos < 0) {
						endPos = document.message_.length();
					}
					;
					title = document.message_.substring(startPos + 6, endPos);
				}
				;
				
			cadena=">>> Postscript job delivered.\n\n";
				network.accounting(report, author, title, cadena);
			} else {
				title = "ASCII DOCUMENT";
				if (document.message_.length() >= 16) {
					author = document.message_.substring(8, 16);
				}
				;
				cadena=">>> ASCII Print job delivered.\n\n";
				network.accounting(report, author, title, cadena);
			}
			;
		} catch (IOException exc) {
			// just ignore
		}
		;
		return true;

		
	}
}
