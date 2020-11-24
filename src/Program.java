
import Servicios.Consultas;


public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String createTestTable = "CREATE TABLE IF NOT EXISTS persona ( " +
			    "id INT AUTO_INCREMENT PRIMARY KEY,"  +
			    "nombre VARCHAR(255) NOT NULL," +
			    "apellido VARCHAR(255) NOT NULL)" ; 
		
		Consultas.executeNonQuery(createTestTable);
		
		Persona p = new Persona(1,"pablo", "valenzuela");
		//Consultas.guardar(p);
			
		p.setApellido("Gomez");
		//Consultas.modificar(p);
		
		Consultas.eliminar(p);
		
	}

}
