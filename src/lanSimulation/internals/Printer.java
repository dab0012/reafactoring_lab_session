package lanSimulation.internals;

public class Printer extends Node {

	public Printer(byte printer, String name) {
		super(PRINTER, name);
		// TODO Auto-generated constructor stub
	}
	
	public void printOn(StringBuffer buf, Node currentNode) {
		buf.append("Printer ");
		buf.append(currentNode.name_);
		buf.append(" [Printer]");
		buf.append(" -> ");
	}

}
