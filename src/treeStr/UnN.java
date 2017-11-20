package treeStr;

// UNARY NODE CLASS
public class UnN extends Node {

	public UnN(String content) {
		// TODO Auto-generated constructor stub
		this(content, null, null);
	}

	public UnN(String content, Node s, Node parent){

		super(content);
		this.sons[0] = s;
		this.sons_needed = 1;
		this.parent = parent;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		int h = -1;
		if(sons[0] != null){
			h = sons[0].height();
		}
		return 1 + h;
	}

	@Override
	public void inorder() {
		// TODO Auto-generated method stub
		System.out.print("("+content);
		if(sons[0] != null){
			sons[0].inorder();
		}
		System.out.print(")");
	}

	public String inorder_string() {
		// TODO Auto-generated method stub
		String cadena="";
		cadena=cadena+"("+content;

		if(sons[0] != null){
			cadena=cadena+ sons[0].inorder_string();
		}
		cadena=cadena+")";
		return cadena;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		int size = 1;
		if(sons[0] != null){
			size = size + sons[0].size();
		}

		return size;
	}

	@Override
	public void setSons(Node[] sons) {
		// TODO Auto-generated method stub
		this.sons[0] = sons[0];
	}

	@Override
	public Node[] getSons() {
		// TODO Auto-generated method stub
		return this.sons;
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
