package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

import Dao.AlumnoDao;
import Dao.AlumnoDaoimpl;

public class Menu {

	private KeyboardReader reader;
	private AlumnoDao alumnoDao;

	public void init() {
		int opcion;

		do {
			menu();
			opcion = reader.nextInt();

			switch (opcion) {
			case 1:
				listAll();
				break;
			case 2:
				listById();
				break;
			case 3:
				insert();
				break;
			case 4:
				update();
				break;
			case 5:
				delete();
				break;
			case 0:
				System.out.println("\nSaliendo del programa\n");
				break;
			default:
				System.err.println("\n numero no válido");
			}

		} while (opcion != 0);
	}

	public void menu() {
		reader = new KeyboardReader();
		alumnoDao = AlumnoDaoimpl.getInstance();
		System.out.println("SISTEMA GESTION DE ALUMNOS");
		System.out.println("==========================");
		System.out.println("Introduzca una opcion de las siguientes");
		System.out.println("opcion 0: Salir");
		System.out.println("opcion 1: listar todos los alumno");
		System.out.println("opcion 2: listar alumno por su ID");
		System.out.println("opcion 3: insertar un nuevo alumno");
		System.out.println("opcion 4: Actualizar un alumno");
		System.out.println("opcion 5: Eliminar un alumno");
		System.out.println("Opcion: ");

	}

	public void insert() {
		System.out.println("Inserción de Alumno");
		System.out.println("-------------------");
		System.out.println("Introduzca NIA alumno");
		int nia = reader.nextInt();

		System.out.println("Introduzca Nombre alumno");
		String nombre = reader.nextLine();

		System.out.println("Introduzca los apellidos del empleado :");
		String apellidos = reader.nextLine();

		System.out.println("Introduzca la fecha de nacimiento del alumno (formato yyyy-mm-dd): ");
		String fechaNacimiento = reader.nextLine();

		System.out.println("Genero del Nuevo alumno (M/F/N)");
		char genero = reader.next().toUpperCase().charAt(0);

		System.out.println("Ciclo del nuevo alumno");
		String ciclo = reader.nextLine();

		System.out.println("Curso del nuevo alumno");
		String curso = reader.nextLine();

		System.out.println("Grupo del nuevo alumno");
		String grupo = reader.nextLine();

		alumnoDao.agregar(new AlumnoEjercicio14(nia, nombre, apellidos, genero, fechaNacimiento, ciclo, curso, grupo));
		// la excepcion la manejará la implementacion del dao en un log o algo
		System.out.println("Nuevo empeado registrado" + "\n");
	}

	public void listAll() {
		System.out.println("LISTADO DE TODOS LOS ALUMNOS");
		System.out.println("------------------------------");

		List<AlumnoEjercicio14> alumnos = alumnoDao.cogerTodosAlumnos();
		if (alumnos.isEmpty())
			System.out.println("No hay alumnos registrados en la base de datos");

		else {
			printCabeceraTablaAlumno();
			alumnos.forEach(this::printAlumnos); // ForEach rapido
			System.out.println();
		}

	}

	public void listById() {
		System.out.println("BUSQUEDA DE ALUMNOS POR ID");
		System.out.println("--------------------------");

		System.out.print("Nia del alumno a buscar:");
		int id = reader.nextInt();

		AlumnoEjercicio14 al = alumnoDao.cogerPorNia(id);

		if (al == null) {
			System.out.println("No hay alumnos registrados en la base de datos con ese id ");
		} else {
			printCabeceraTablaAlumno();
			printAlumnos(al);
		}
System.out.println();
	}
	
	public void update() 
	{
		System.out.println("ACTUALIZACION DE UN EMPLEADO");
		System.out.println("----------------------------");
		
		System.out.println("Introduzca el Nia del alumno a buscar");
		
		int id = reader.nextInt();

		AlumnoEjercicio14 al = alumnoDao.cogerPorNia(id);

		if (al == null) {
			System.out.println("No hay empleados registrados en la base de datos con ese id ");
		} else {
			printCabeceraTablaAlumno();
			printAlumnos(al);
			System.out.println();
			
			System.out.printf("Intoduzca el nombre del alumno", al.getNombre());
			String nombre = reader.nextLine();
			nombre = (nombre.isBlank()) ? al.getNombre() : nombre;
			
			System.out.printf("Intoduzca los apellidos del alumno", al.getNombre());
			String apellidos = reader.nextLine();
			apellidos = (apellidos.isBlank()) ? al.getApellidos() : nombre;
			
			System.out.printf("Intoduzca la fecha de nacimiento del alumno (dd/MM/yyyy)",
			al.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			String strfechaNac = reader.nextLine();
			LocalDate fechaNac = (strfechaNac.isBlank()) ? al.getFecha() :
			LocalDate.parse(strfechaNac, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			
			System.out.printf("Intoduzca el genero del alumno ", al.getGenero());
			char genero = reader.next().toUpperCase().charAt(0);
			genero = (genero == ' ') ? al.getGenero() : genero;
			
			System.out.printf("Intoduzca el ciclo del alumno", al.getCiclo());
			String ciclo = reader.nextLine();
			ciclo = (ciclo.isEmpty()) ? al.getCiclo() : ciclo;
			
			System.out.printf("Intoduzca el curso del alumno", al.getCurso());
			String curso = reader.nextLine();
			curso = (curso.isEmpty()) ? al.getCurso() : curso;
			
			System.out.printf("Intoduzca el grupo del alumno", al.getGrupo());
			String grupo = reader.nextLine();
			grupo = (grupo.isEmpty()) ? al.getGrupo() : grupo;
			
			al.setNombre(nombre);
			al.setApellidos(apellidos);
			al.setFecha(fechaNac);
			al.setGenero(genero);
			al.setCiclo(ciclo);
			al.setCurso(curso);
			al.setGrupo(grupo);
			
			alumnoDao.actualizar(al);
			
			System.out.println("");
			System.out.printf("Alumno con id %s actualizado", al.getNia());
			System.out.println("");
		}
	}
	private void delete() 
	{
		System.out.println("BORRADO DE UN EMPLEADO");
		System.out.println("----------------------");
		
		System.out.println("Introduzca el Nia del alumno a borrar: ");
		int id = reader.nextInt();
		
		System.out.printf("¿Estás seguro de eliminar este alumno con Nia=%s? (s/n)", id);
		String borrar = reader.nextLine();
		
		if(borrar.equalsIgnoreCase("s")) 
		{
			alumnoDao.delete(id);
			System.out.printf("alumno con Nia %s se ha elminado", id);
			
		}
		System.out.println();
	}

	private void printCabeceraTablaAlumno() {
		System.out.printf("%-8s %-20s %-25s %-8s %-12s %-15s %-8s %-6s%n", "NIA", "NOMBRE", "APELLIDOS", "GENERO",
				"FECH. NAC.", "CICLO", "CURSO", "GRUPO");
		System.out.println();
		IntStream.range(1, 100).forEach(x -> System.out.print("-"));
		System.out.println();
	}

	private void printAlumnos(AlumnoEjercicio14 al) {
		System.out.printf("%-8s %-20s %-25s %-8s %-12s %-15s %-8s %-6s%n", al.getNia(), al.getNombre(),
				al.getApellidos(), al.getGenero(), al.getFecha(), al.getCiclo(), al.getCurso(), al.getGrupo());
	}

	static class KeyboardReader {

		BufferedReader br;
		StringTokenizer st;

		public KeyboardReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {

			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					System.err.println("Error leyendo del teclado");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		LocalDate nextLocalDate() {
			String formatoFecha = "dd/MM/yyyy";
			return LocalDate.parse(next(), DateTimeFormatter.ofPattern(formatoFecha));
		}

		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreElements()) {
					str = st.nextToken("\n");
				} else

					str = br.readLine();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return str;
		}

	}
}
