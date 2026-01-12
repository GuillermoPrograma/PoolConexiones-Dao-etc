package Main;

public class Menu {

	private KeyBoardReader reader;

	public void init() {
		Menu();
	}

	public Menu() {
		
		reader = new KeyBoardReader();
	}

	public void Menu() {
		System.out.println("SISTEMA GESTION ALUMNOS");
		System.out.println("=======================");
		System.out.println("-> Introduccion una opcion");
		System.out.println("0: Salir : ");
		System.out.println("1: Listar todos los empleados");
		System.out.println("2: Listar un empleado por su ID");
		System.out.println("3: Instentar un nuevo empleado");
		System.out.println("4: Actualizar un empleado");
		System.out.print("/n" + "opcion: ");

	}

	static class KeyBoardReader {

	}

}
