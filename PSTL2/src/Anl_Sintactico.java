import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Anl_Sintactico {
	
	Stack<String> stck = new Stack<String>();
	
	
	public Anl_Sintactico() {
		// TODO Auto-generated constructor stub
	}

	void ejemplo1() {
		stck.push("2");
		stck.push("3");
		stck.push("4");
		stck.push("5");
		for (Iterator<String> i = stck.iterator(); i.hasNext();) {
			String item = i.next();
			System.out.println(item);
		}
		System.out.println("El tope de la pila es -> " + stck.lastElement());
		stck.pop();
		System.out.println("Ahora el tope de la pila es -> " + stck.lastElement());
	}
	
	void ejemplo2(ArrayList<Componente> lstComponent) {
		System.out.println("Ejemplo #2");
		for(int i = 0; i < lstComponent.size(); i++)
			System.out.println("Palabra: " + lstComponent.get(i).word + " | Token " + lstComponent.get(i).token);
	}
	
	void ejemplo3(ArrayList<Componente> lstComponent) {
		System.out.println("Ejemplo #3");
		stck.clear();
		int index = 0;
		int tbl[][] = {{2,0,1},{0,-1,0},{0,-2,0}};
		boolean acceptation = false;
		stck.push("$");
		stck.push("0");
		int fila = Integer.valueOf(stck.peek());
		int columna = Integer.valueOf(lstComponent.get(index).token);
		int accion = tbl[fila][columna];
		System.out.println("Entrada: " + lstComponent.get(index).token);
		System.out.println("Acción: " + accion);
	}
	
	void ejercicio1() {
		System.out.println("Ejercicio #1");
		ArrayList<Componente> lstComponent = new ArrayList<Componente>();
		lstComponent.add(new Componente("a", "0"));
		lstComponent.add(new Componente("+", "1"));
		lstComponent.add(new Componente("b", "0"));
		lstComponent.add(new Componente("$", "2"));
		lstComponent.add(new Componente("$", "2"));
		int tbl[][] = {{2,0,0,1},{0,0,-1,0},{0,3,0,0},{4,0,0,0},{0,0,-2,0}};
		stck.clear();
		stck.push("$");
		stck.push("0");
		System.out.print("Pila: ");
		for(int p = 0; p < stck.size(); p++)
			System.out.print(stck.elementAt(p));
		System.out.print("\n");
		int i = 0;
		while (true) {
			int action = tbl[Integer.valueOf(stck.peek())][Integer.valueOf(lstComponent.get(i).token)];
			// System.out.println("tbl[" + Integer.valueOf(stck.peek()) + "][" + Integer.valueOf(lstComponent.get(i).token) + "] = " + action);
			if(action > 0) { // desplazamiento
				stck.push(lstComponent.get(i).word);
				stck.push(String.valueOf(action));
			} else if(action < 0) { // reducción
				if(action == -2) {
					stck.pop(); stck.pop(); stck.pop(); stck.pop(); stck.pop(); stck.pop();
					stck.push("E");
					stck.push("1");
				} else if (action == -1) {
					System.out.println("Aceptación");
					break;
				}
			} else {
				System.out.println("error alv");
				break;
			}
			System.out.print("Pila: ");
			for(int p = 0; p < stck.size(); p++)
				System.out.print(stck.elementAt(p));
			System.out.print("\n");
			i++;
		}
	}	
	
	void ejercicio2() {
		System.out.println("Ejercicio #1");
		ArrayList<Componente> lstComponent = new ArrayList<Componente>();
		lstComponent.add(new Componente("a", "0"));
		lstComponent.add(new Componente("+", "1"));
		lstComponent.add(new Componente("b", "0"));
		lstComponent.add(new Componente("$", "2"));
		lstComponent.add(new Componente("$", "2"));
		int tbl[][] = {{2,0,0,1},{0,0,-1,0},{0,3,-3,0},{2,0,0,4},{0,0,-2,0}};
		stck.clear();
		stck.push("$");
		stck.push("0");
		System.out.print("Pila: ");
		for(int p = 0; p < stck.size(); p++)
			System.out.print(stck.elementAt(p));
		System.out.print("\n");
		int i = 0;
		while (true) {
			int action = tbl[Integer.valueOf(stck.peek())][Integer.valueOf(lstComponent.get(i).token)];
			// System.out.println("tbl[" + Integer.valueOf(stck.peek()) + "][" + Integer.valueOf(lstComponent.get(i).token) + "] = " + action);
			if(action > 0) { // desplazamiento
				stck.push(lstComponent.get(i).word);
				stck.push(String.valueOf(action));
			} else if(action < 0) { // reducción
				if(action == -2) {
					stck.pop(); stck.pop(); stck.pop(); stck.pop(); stck.pop(); stck.pop();
					stck.push("E");
					stck.push("1");
				} else if (action == -1) {
					System.out.println("Aceptación");
					break;
				}
			} else {
				System.out.println("error alv");
				break;
			}
			System.out.print("Pila: ");
			for(int p = 0; p < stck.size(); p++)
				System.out.print(stck.elementAt(p));
			System.out.print("\n");
			i++;
		}
	}
	
}
