package treeStr;

// BINARY NODE CLASS
public class BinN extends Node {


	public BinN(String content) {
		// TODO Auto-generated constructor stub
		this(content, null, null, null);
	}

	public BinN(String content, Node s1, Node s2, Node parent){

		super(content);
		this.sons[0] = s1;
		this.sons[1] = s2;
		this.sons_needed = 2;
		this.parent = parent;

	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		int hr = -1;
		int hl = -1;
		if(sons[0] != null){
			hl = sons[0].height();
		}
		if(sons[1] != null){
			hr = sons[1].height();
		}
		return 1 + Math.max(hl, hr);
	}

	@Override
	public void inorder() {
		// TODO Auto-generated method stub
		System.out.print("(");
		if(sons[0] != null){
			sons[0].inorder();
		}
		System.out.print(content);
		if(sons[1] != null){
			sons[1].inorder();
		}
		System.out.print(")");
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		int size = 1;
		if(sons[0] != null){
			size = size + sons[0].size();
		}
		if(sons[1] != null){
			size = size + sons[1].size();
		}

		return size;
	}

	@Override
	public void setSons(Node[] sons) {
		// TODO Auto-generated method stub
		this.sons[0] = sons[0];
		this.sons[1] = sons[1];
	}

	@Override
	public Node[] getSons() {
		// TODO Auto-generated method stub		
		return this.sons;
	}

	@Override
	public String inorder_string() {
		// TODO Auto-generated method stub
		String cadena="(";

		if(sons[0] != null){
			cadena= cadena + sons[0].inorder_string();
		}
		cadena=cadena+content;

		if(sons[1] != null){
			cadena= cadena + sons[1].inorder_string();
		}

		return cadena+")";
	}

	@Override
	public void setParent(Node parent) {
		// TODO Auto-generated method stub
		this.parent = parent;
	}

	@Override
	public void setOneSon(Node son, int idx) {
		// TODO Auto-generated method stub
		this.sons[idx] = son;
	}

}


