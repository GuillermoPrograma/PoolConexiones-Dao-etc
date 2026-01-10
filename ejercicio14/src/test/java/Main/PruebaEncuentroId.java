package Main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import Dao.AlumnoDaoimpl;

class PruebaEncuentroId {

	@Test
	void EncuentroId() {
		AlumnoDaoimpl dao = AlumnoDaoimpl.getInstance();

		AlumnoEjercicio14 alumno = dao.cogerPorNia(9999);

		 assertNotNull(alumno, "No se ha encontrado ningún alumno con NIA 9999");

		System.out.println(alumno.toString());
	}

}
