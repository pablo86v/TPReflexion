package Utilidades;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class UConexion {

	private static final String newLine = System.getProperty("line.separator");
	private static final String driverParam = "driver";
	private static final String defaultDriver = "com.mysql.jdbc.Driver";
	private static final String paramsFileName = "framework.properties";
	private static final String dbParam = "dataBase";
	private static final String defaultDB = "jdbc:mysql://localhost:3306/test";
	private static final String dbUserParam = "dbUser";
	private static final String defaultDbUser = "root";
	private static final String dbPasswordParam = "password";
	private static UConexion uConexion;
	private Connection conn = null;
	
	public static UConexion getInstance() {
		if(uConexion == null) {
			uConexion = new UConexion();	
		}
		return uConexion;
	}
	
	
	
	private static void createDefaultParams(File paramsFile) {
		try {
		      if (paramsFile.createNewFile()) {
		        FileWriter myWriter = new FileWriter(paramsFile);
		        myWriter.write( driverParam + "=" + defaultDriver + newLine);
		        myWriter.write( dbParam + "=" + defaultDB + newLine);
		        myWriter.write( dbUserParam + "=" + defaultDbUser + newLine);
		        myWriter.write( dbPasswordParam + "= " +  newLine);
			    myWriter.close();  
		      } 
		      
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	private static String getParam(String paramName)
	{
		Properties prop = new Properties();
		try {
			File f = new File(paramsFileName);
			if(!f.exists()) {
				createDefaultParams(f);
			}
			prop.load(new FileReader(f));	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty(paramName);
	}
	
	public Connection openConn() {
		
		try {
			Class.forName(getParam(driverParam));
			this.conn = DriverManager.getConnection(getParam(dbParam), getParam(dbUserParam),getParam(dbPasswordParam));
					
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.conn;
	}
	
	public void closeConn() {
		if(this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
