package treeStr;

public class Tree {

	private Node root;

	public Tree(){
		this.root = null;
	}

	public Tree(Node root){
		this.root = root;
	}

	public int size(){
		int size = 0;

		if(root != null){
			size = root.size();
		}

		return size;
	}

	public int height(){
		int h = -1;

		if(root != null){
			h = root.height();
		}

		return h;
	}

	public Node getRoot(){
		return this.root;
	}

	public void setRoot(Node root){
		this.root = root;
	}
}
