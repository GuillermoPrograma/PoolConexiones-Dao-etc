package Main;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import ejercicio14.pool.MyDataSource;

public class App {
public static void main(String[] args) {
	try(Connection conexion = MyDataSource.getConnection())
	{
		DatabaseMetaData metaData = conexion.getMetaData();
		String[] types = {"TABLE"};
		ResultSet tables = metaData.getTables(null, null, "%", types);
		while(tables.next()) 
		{
			System.out.println(tables.getString("TABLE_NAME"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
