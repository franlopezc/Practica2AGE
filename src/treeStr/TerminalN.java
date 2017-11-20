package treeStr;

// TERMINAL NODE CLASS
public class TerminalN extends Node {

	public TerminalN(String content) {
		// TODO Auto-generated constructor stub
		this(content, null);
	}

	public TerminalN(String content, Node parent) {
		// TODO Auto-generated constructor stub
		super(content);
		this.sons_needed = 0;
		this.parent = parent;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void inorder() {
		// TODO Auto-generated method stub
		System.out.print("("+content+")");
	}

	@Override
	public String inorder_string() {
		// TODO Auto-generated method stub
		return "("+content+")";
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void setSons(Node[] sons) {
		// TODO Auto-generated method stub

		// Won't be used
	}

	@Override
	public Node[] getSons() {
		// TODO Auto-generated method stub

		// Won't be used
		return null;
	}

	@Override
	public void setParent(Node parent) {
		// TODO Auto-generated method stub
		this.parent = parent;
	}

	@Override
	public void setOneSon(Node son, int idx) {
		// TODO Auto-generated method stub

		// Won't be used
	}

}
