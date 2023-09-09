package Model;

public class Nodo {
	private int value;
	private int equilibrateFactor;
	private Nodo left;
	private Nodo right;
	
	public Nodo(int value) {
		this.value=value;
		this.equilibrateFactor=0;
		this.right=null;
		this.left=null;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getEquilibrateFactor() {
		return equilibrateFactor;
	}

	public void setEquilibrateFactor(int equilibrateFactor) {
		this.equilibrateFactor = equilibrateFactor;
	}

	public Nodo getLeft() {
		return left;
	}

	public void setLeft(Nodo left) {
		this.left = left;
	}

	public Nodo getRight() {
		return right;
	}

	public void setRight(Nodo right) {
		this.right = right;
	}
	
	
	
}
