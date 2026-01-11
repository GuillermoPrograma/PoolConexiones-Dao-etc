package Dao;

import java.util.List;

import Main.AlumnoEjercicio14;

public interface AlumnoDao {

	int agregar(AlumnoEjercicio14 al);
	
	AlumnoEjercicio14 cogerPorNia(int id);

	List<AlumnoEjercicio14> cogerTodosAlumnos();

	int actualizar(AlumnoEjercicio14 al);
	
	void delete(int id);
	
}
