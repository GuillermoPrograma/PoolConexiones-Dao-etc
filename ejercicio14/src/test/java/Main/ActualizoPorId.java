package Main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Dao.AlumnoDaoimpl;

class ActualizoPorId {

	@Test
	void AcutalizoPorId() {
		
		AlumnoDaoimpl alumnoDao = AlumnoDaoimpl.getInstance();
		
		
		AlumnoEjercicio14 alumno = new AlumnoEjercicio14(9999, "Guillermo", "Santos", 'M', "2004-05-22" , "Dam", "Segundo", "A");
		int filas = alumnoDao.actualizar(alumno);
		 assertEquals(1, filas); //me tiene que decir que ha modificado una fila 
		
		
	}

}
