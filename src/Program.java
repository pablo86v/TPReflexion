import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Servicios.Consultas;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String bd = "jdbc:mysql://localhost:3306/test";
			Connection conn = DriverManager.getConnection(bd, "root","");
			
			//PreparedStatement st = conn.prepareStatement("insert into persona values (1,32639409,'Pablo','Valenzuela')");
			//st.execute();
			
			
			Persona p = new Persona(1,"n","a");
			
			Consultas.obtenerPorId(Persona.class, 1);
			
			
			//PreparedStatement st2 = conn.prepareStatement("select * from persona");
			//ResultSet rs = st2.executeQuery();
			
			
			//while(rs.next()) {
			//	System.out.println(rs.getString("nombre"));
			//}
			
			
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
