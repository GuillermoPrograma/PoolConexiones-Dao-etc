package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Main.AlumnoEjercicio14;
import ejercicio14.pool.MyDataSource;

public class AlumnoDaoimpl implements AlumnoDao {

	private static AlumnoDaoimpl instance;

	static {
		instance = new AlumnoDaoimpl();
	}

	private AlumnoDaoimpl() {
	}

	public static AlumnoDaoimpl getInstance() {
		return instance;
	}

	@Override
	public int agregar(AlumnoEjercicio14 al) {
		String sql = "Insert Into alumno(Nia,Nombre,Apellidos,Genero,FechaNacimiento,Ciclo,Curso,Grupo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		int result = -1; // me obliga a poner un numero para instanciar se me ha ocurrido el -1

		try (Connection conexion = MyDataSource.getConnection()) {
			PreparedStatement pstm = conexion.prepareStatement(sql);

			pstm.setInt(1, al.getNia());
			pstm.setString(2, al.getNombre());
			pstm.setString(3, al.getApellidos());
			pstm.setString(4, String.valueOf(al.getGenero()));

			pstm.setDate(5, Date.valueOf(al.getFecha()));
			pstm.setString(6, al.getCiclo());
			pstm.setString(7, al.getCurso());
			pstm.setString(8, al.getGrupo());

			result = pstm.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public AlumnoEjercicio14 cogerPorNia(int id) {

		String sql = "select Nia,Nombre,Apellidos,Genero,FechaNacimiento,Ciclo,Curso,Grupo from alumno where Nia = ?";

		try (Connection conexion = MyDataSource.getConnection();
				PreparedStatement pstm = conexion.prepareStatement(sql)) {
			pstm.setInt(1, id);

			try (ResultSet rs = pstm.executeQuery()) {
				if (rs.next()) {
					AlumnoEjercicio14 alumno = new AlumnoEjercicio14(rs.getInt("Nia"), rs.getString("Nombre"),
							rs.getString("Apellidos"), rs.getString("Genero").charAt(0),
							rs.getDate("FechaNacimiento").toString(), rs.getString("Ciclo"), rs.getString("Curso"),
							rs.getString("Grupo"));

					return alumno;
				} else
					return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}

	}

	@Override
	public List<AlumnoEjercicio14> cogerTodosAlumnos() {
		String sql = "select Nia,Nombre,Apellidos,Genero,FechaNacimiento,Ciclo,Curso,Grupo from alumno";

		List<AlumnoEjercicio14> alumnos = new ArrayList<>();

		try (Connection conexion = MyDataSource.getConnection();
				PreparedStatement pstm = conexion.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery()) {
			while (rs.next()) {
				AlumnoEjercicio14 alumno = new AlumnoEjercicio14(rs.getInt("Nia"), rs.getString("Nombre"),
						rs.getString("Apellidos"), rs.getString("Genero").charAt(0),
						rs.getDate("FechaNacimiento").toString(), rs.getString("Ciclo"), rs.getString("Curso"),
						rs.getString("Grupo"));
				alumnos.add(alumno); // el date se podria hacer localdate en vez de string con el toLocalDate

			}
			return alumnos;
		} catch (Exception e) {
			return null;
			// throw new RuntimeException(e); //queria devolver un null pero chatgpt me ha
			// dicho que con esta exception mejor
		}
	}

	@Override
	public int actualizar(AlumnoEjercicio14 al) {
		String sql = "Update alumno set Nombre=?, Apellidos=?,Genero =?, FechaNacimiento=?,Ciclo =?, Curso=?, Grupo=? "
				+ "where Nia=?";

		try (Connection conexion = MyDataSource.getConnection()) {
			PreparedStatement ps = conexion.prepareStatement(sql);

			ps.setString(1, al.getNombre());
			ps.setString(2, al.getApellidos());
			ps.setString(3, Character.toString(al.getGenero()));
			ps.setDate(4, Date.valueOf(al.getFecha()));
			ps.setString(5, al.getCiclo());
			ps.setString(6, al.getCurso());
			ps.setString(7, al.getGrupo());
			ps.setInt(8, al.getNia());

			return ps.executeUpdate();

		} catch (Exception e) {

			return -1;
		}

	}

	@Override
	public int delete(int id) {

		String sql = "Delete from alumno where Nia Like ?";
		int result = -1;
		try (Connection conexion = MyDataSource.getConnection()) {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, id);
			result = ps.executeUpdate();
			return result;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return -1;

		}
	}

}
