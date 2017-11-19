package treeStr;

public abstract class Node {

	public String content;
	public Node[] sons;
	public int sons_needed; // 2-binary; 1-unary; 0-terminal
 	public Node parent;
	
	Node(String content){
		this.content = content;
		this.sons = new Node[2];
	}
	
	public abstract int size(); // number of nodes, useful for penalizing "big" solutions
	public abstract int height(); // depth
	public abstract void inorder();
	public abstract String inorder_string();
	
	public abstract void setSons(Node[] sons);
	public abstract Node[] getSons();
	public abstract void setParent(Node parent);
	
}
