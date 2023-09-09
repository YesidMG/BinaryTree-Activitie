package Model;

public class Tree {
	private Nodo raiz;

	public Tree() {
		this.raiz=null;
	}

	public void insert(int num) {
		Nodo newNodo= new Nodo(num);
		if(this.raiz==null) {
			this.raiz=newNodo;
		}else {
			this.raiz=insertOrderAVL(newNodo, this.raiz);
		}
	}

	public Nodo insertOrderAVL(Nodo newNodo,Nodo subTree) {
		Nodo newFather= subTree;
		if (newNodo.getValue()<subTree.getValue()) {
			if(subTree.getLeft()==null) {
				subTree.setLeft(newNodo);
			}else {
				subTree.setLeft(this.insertOrderAVL(newNodo, subTree.getLeft()));
				if((this.getFE(subTree.getLeft()))-(this.getFE(subTree.getRight()))==2) {
					if(newNodo.getValue()<subTree.getLeft().getValue()) {
						newFather=this.leftRotateSimple(subTree);
					}else {
						newFather=this.leftRotateDouble(subTree);
					}
				}
			}
		}else if(newNodo.getValue()> subTree.getValue()) {
			if(subTree.getRight()==null) {
				subTree.setRight(newNodo);
			}else {
				subTree.setRight(this.insertOrderAVL(newNodo, subTree.getRight()));
				if((this.getFE(subTree.getRight()))-(this.getFE(subTree.getLeft()))==2) {
					if(newNodo.getValue()>subTree.getRight().getValue()) {
						newFather=this.rightRotateSimple(subTree);
					}else {
						newFather=this.righttRotateDouble(subTree);
					}
				}
			}
		}else {
			System.out.println("Nodo ya existente");
		}
		if((subTree.getLeft()==null)&&(subTree.getRight()!=null)) {
			subTree.setEquilibrateFactor(subTree.getRight().getEquilibrateFactor()+1);
		}else if((subTree.getRight()==null)&&(subTree.getLeft()!=null)) {
			subTree.setEquilibrateFactor(subTree.getLeft().getEquilibrateFactor()+1);
		}else {
			subTree.setEquilibrateFactor(Math.max(this.getFE(subTree.getLeft()), this.getFE(subTree.getRight()))+1);
		}
		return newFather;
	}

	public int getFE(Nodo nodo) {
		if(nodo==null) {
			return -1;
		}else {
			return nodo.getEquilibrateFactor();
		}
	}

	public Nodo leftRotateSimple(Nodo nodo) {
		Nodo aux=nodo.getLeft();
		nodo.setLeft(aux.getRight());
		aux.setRight(nodo);
		nodo.setEquilibrateFactor(Math.max(this.getFE(nodo.getLeft()), this.getFE(nodo.getRight()))+1);
		aux.setEquilibrateFactor(Math.max(this.getFE(aux.getLeft()), this.getFE(aux.getRight()))+1);
		return aux;
	}

	public Nodo rightRotateSimple(Nodo nodo) {
		Nodo aux=nodo.getRight();
		nodo.setRight(aux.getLeft());
		aux.setLeft(nodo);
		nodo.setEquilibrateFactor(Math.max(this.getFE(nodo.getLeft()), this.getFE(nodo.getRight()))+1);
		aux.setEquilibrateFactor(Math.max(this.getFE(aux.getLeft()), this.getFE(aux.getRight()))+1);
		return aux;
	}

	public Nodo leftRotateDouble(Nodo nodo) {
		Nodo temp;
		nodo.setLeft(this.rightRotateSimple(nodo.getLeft()));
		temp= this.leftRotateSimple(nodo);
		return temp;
	}

	public Nodo righttRotateDouble(Nodo nodo) {
		Nodo temp;
		nodo.setRight(this.leftRotateSimple(nodo.getRight()));
		temp= this.rightRotateSimple(nodo);
		return temp;
	}

	public Nodo eliminar(Nodo nodo, int valor) {
		if (nodo == null) {
			return nodo;
		}
		if (valor < nodo.getValue()) {
			nodo.setLeft(eliminar(nodo.getLeft(), valor));
		}else if (valor > nodo.getValue()) {
			nodo.setRight(eliminar(nodo.getRight(), valor));
		}else {
			if ((nodo.getLeft() == null) || (nodo.getRight() == null)) {
				Nodo temp = null;
				if (temp == nodo.getLeft()) {
					temp = nodo.getRight();
				} else {
					temp = nodo.getLeft();
				}
				if (temp == null) {
					temp = nodo;
					nodo = null;
				} else { 
					nodo = temp; 
				}
			} else {
				Nodo temp = minValueNode(nodo.getRight());
				nodo.setValue(temp.getValue());
				nodo.setRight(eliminar(nodo.getRight(), temp.getValue()));
			}
		}
		if (nodo == null) {
			return nodo;
		}
		nodo.setEquilibrateFactor(1 + Math.max(getFE(nodo.getLeft()), getFE(nodo.getRight())));
		int balance = nodo.getEquilibrateFactor();

		if (balance > 1 && nodo.getLeft().getEquilibrateFactor() >= 0) {
			return rightRotateSimple(nodo);
		}
		if (balance > 1 && nodo.getLeft().getEquilibrateFactor() < 0) {
			nodo.setLeft(leftRotateSimple(nodo.getLeft()));
			return rightRotateSimple(nodo);
		}
		if (balance < -1 && nodo.getRight().getEquilibrateFactor() <= 0) {
			return leftRotateSimple(nodo);
		}
		if (balance < -1 && nodo.getRight().getEquilibrateFactor() > 0) {
			nodo.setRight(rightRotateSimple(nodo.getRight()));
			return leftRotateSimple(nodo);
		}
		return nodo;
	}

	private Nodo minValueNode(Nodo node) {
		Nodo current = node;
		while (current.getLeft() != null)
			current = current.getLeft();
		return current;
	}

	public String inOrder(Nodo tree) {
		if(tree==null) {
			return "";
		}
		String resultado = "";
		resultado += inOrder(tree.getLeft());
		resultado += tree.getValue() + ", ";
		resultado += inOrder(tree.getRight());
		return resultado;
	}

	public String preOrder(Nodo tree) {
		if(tree==null) {
			return "";
		}
		String resultado = "";
		resultado += tree.getValue() + ", ";
		resultado += preOrder(tree.getLeft());
		resultado += preOrder(tree.getRight());
		return resultado;
	}

	public String postOrder(Nodo tree) {
		if(tree==null) {
			return "";
		}
		String resultado = "";
		resultado += postOrder(tree.getLeft());
		resultado += postOrder(tree.getRight());
		resultado += tree.getValue() + ", ";
		return resultado;
	}

	public Nodo getRaiz() {
		return raiz;
	}

}
