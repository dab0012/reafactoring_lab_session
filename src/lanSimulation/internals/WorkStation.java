package lanSimulation.internals;

public class WorkStation extends Node{

	public WorkStation(byte workstation, String name) {
		super(WORKSTATION,name);
		// TODO Auto-generated constructor stub
	}
	
	public void printOn(StringBuffer buf, Node currentNode) {
		buf.append("Workstation ");
		buf.append(currentNode.name_);
		buf.append(" [Workstation]");
		buf.append(" -> ");
	}

}
