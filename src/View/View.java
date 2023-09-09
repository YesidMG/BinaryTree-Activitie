package View;

import java.util.Scanner;

public class View {
	
	private Scanner sc ;
	
	public View(){
		sc = new Scanner(System.in);
	}
	
	public int menu() {
		System.out.println("Ingrese una de las siguientes opciones: "
				+ "\n1)Recorrer Arbol"
				+ "\n2)Insertar elemento en arbol"
				+ "\n3)Eliminar elemento de arbol"
				+ "\n4)Finalizar ejecucion");
		return sc.nextInt();
	}
	
	public int menuRecorrido() {
		System.out.println("Elija el recorrido: "
				+ "\n1)In-Order"
				+ "\n2)Pre-Order"
				+ "\n3)Post-Order");
		return sc.nextInt();
	}
	public int newElement() {
		System.out.println("Ingrese un nuevo elemento");
		return sc.nextInt();
	}
	public int deleteElement() {
		System.out.println("Ingrese el elemento a eliminar");
		return sc.nextInt();
	}
	
	public void showRecorrido(String recorridoType, String recorrido) {
		System.out.println("El recorrido en "+recorridoType+" es: "+recorrido );
	}
	
	public void error() {
		System.out.println("Opcion no valida");
	}
	public void finish() {
		System.out.println("Ejecucion terminada");
	}
}
