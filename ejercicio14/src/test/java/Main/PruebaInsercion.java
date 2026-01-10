package Main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import Dao.AlumnoDaoimpl;

class PruebaInsercion {

	@Test
	void insertarAlumno() {
		 AlumnoEjercicio14 al = new AlumnoEjercicio14(9999, "Juan", "Gomez", 'M', "2004-05-22" , "Dam", "Segundo", "A");
		 AlumnoDaoimpl dao = AlumnoDaoimpl.getInstance();
		 int filas = dao.agregar(al);
		 assertEquals(1, filas); //me tiene que decir que ha modificado una fila 
		}
	
	
}
