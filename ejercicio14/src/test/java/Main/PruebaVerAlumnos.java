package Main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import Dao.AlumnoDaoimpl;

class PruebaVerAlumnos {

	@Test
	void VerAlumnos() {

		AlumnoDaoimpl dao = AlumnoDaoimpl.getInstance();

		List<AlumnoEjercicio14> alumnos = dao.cogerTodosAlumnos();

		assertNotNull(alumnos, "la lista no puede ser nula");
		for (AlumnoEjercicio14 alumno : alumnos) {
			System.out.println(alumno.toString()); //aqui veo si la lista está vacia, pero si no me hace la conexion salta el otro error
		}
	}
}
