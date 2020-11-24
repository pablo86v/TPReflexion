import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Servicios.Consultas;
import Utilidades.UConexion;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String bd = "jdbc:mysql://localhost:3306/test";
			Connection conn = DriverManager.getConnection(bd, "root","");
			
	
			
			
			Persona p ;
			
			p = (Persona) Consultas.obtenerPorId(Persona.class, 1);

			System.out.println(p.toString());

			
			
			conn.close();
					
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
