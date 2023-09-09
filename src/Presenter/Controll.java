package Presenter;

import Model.Tree;
import View.View;

public class Controll {

	private Tree tree;
	private View view;

	public Controll () {
		tree = new Tree();
		view = new View();
		this.init();

	}
	public void init() {
		boolean run =true;
		while (run) {
			switch (view.menu()) {
			case 1:{
				switch (view.menuRecorrido()) {
				case 1: {
					view.showRecorrido("in-Order", tree.inOrder(tree.getRaiz()));
					break;
				}
				case 2: {
					view.showRecorrido("pre-Order", tree.preOrder(tree.getRaiz()));
					break;
				}
				case 3: {
					view.showRecorrido("post-Order", tree.postOrder(tree.getRaiz()));
					break;
				}
				default:
					view.error();
				}
				break;
			}
			case 2:{
				tree.insert(view.newElement());
				break;
			}
			case 3:{
				tree.eliminar(tree.getRaiz(), view.deleteElement());
				break;
			}
			case 4:{
				view.finish();
				run=false;
				break;
			}
			default:
				view.error();
			}
		}

	}
	public static void main(String[] args) {
		new Controll();

	}

}
