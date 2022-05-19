package lanSimulation.internals;

public class WorkStation extends Node{

	public WorkStation(byte workstation, String name) {
		super(WORKSTATION,name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void printOn(StringBuffer buf) {
		buf.append("Workstation ");
		buf.append(name_);
		buf.append(" [Workstation]");
		buf.append(" -> ");
	}
	
	@Override
	public void printHTMLOn(StringBuffer buf) {
		buf.append("\n\t<LI> ");
		buf.append("Workstation ");
		buf.append(name_);
		buf.append(" [Workstation]");
		buf.append(" </LI>");
	}
	
	@Override
	public void printXMLOn(StringBuffer buf, Node currentNode) {
		buf.append("\n\t");
		buf.append("<workstation>");
		buf.append(currentNode.name_);
		buf.append("</workstation>");

	}

}
