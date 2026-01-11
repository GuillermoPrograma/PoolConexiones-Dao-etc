package Main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Dao.AlumnoDaoimpl;

class BorroporID {

	@Test
	void BorroPorId() {
		AlumnoDaoimpl dao = AlumnoDaoimpl.getInstance();
		int filas = dao.delete(9999);

	    assertEquals(1, filas);  // se ha borrado 1 alumno
		
		
	}

}
