package lanSimulation.internals;

public class Printer extends Node {

	public Printer(byte printer, String name) {
		super(PRINTER, name);
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
}
