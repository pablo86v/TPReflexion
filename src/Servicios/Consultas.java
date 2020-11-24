package Servicios;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Anotaciones.Columna;
import Anotaciones.Tabla;
import Utilidades.UBean;
import Utilidades.UConexion;

public class Consultas {
	
	
	private static Object executeQuery(String sql, Class clazz) {
		Object obj = null;
		
		try {
			ArrayList<Field> listaAttr;
			listaAttr = UBean.obtenerAtributos(clazz.newInstance());
			obj = clazz.newInstance();
			
			UConexion uConn = new UConexion();
			PreparedStatement pstm = uConn.openConn().prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
	
			while(rs.next()) {				
				for (Field attr : listaAttr) {
					if(attr.getType().equals(String.class)) {
						UBean.ejecutarSet(obj,attr.getName(), rs.getString(attr.getName()));
					}else if(attr.getType().equals(int.class)) {				
						UBean.ejecutarSet(obj,attr.getName(), rs.getInt(attr.getName()));	
					}else if(attr.getType().equals(double.class)) {
						UBean.ejecutarSet(obj,attr.getName(), rs.getDouble(attr.getName()));
					}else {
						UBean.ejecutarSet(obj,attr.getName(), rs.getObject(attr.getName()));
					}
				}
			}
			
			uConn.closeConn();
		} catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return obj;
	}
	
	public static void executeNonQuery(String sql) {
		try {
			UConexion uConn = new UConexion();
			PreparedStatement pstm = uConn.openConn().prepareStatement(sql);
			pstm.execute();
			uConn.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void guardar(Object obj){
		ArrayList<Field> listaAttr;
		String strConsulta = "insert into ";
		strConsulta += obj.getClass().getAnnotation(Tabla.class).nombre() + " (";
		listaAttr = UBean.obtenerAtributos(obj);
		String attrValue;
		String strValues = ") values(";
		for(Field atributo: listaAttr) {
			strConsulta += atributo.getAnnotation(Columna.class).nombre() + ", ";
			attrValue = UBean.ejecutarGet(obj, atributo.getName()).toString();
			if(atributo.getType().equals(int.class))
			{
				strValues += attrValue + ",";
			}else {
				strValues += "'" + attrValue + "',";
			}
		}
		strConsulta = strConsulta.substring(0, strConsulta.length() - 2);
		strValues = strValues.substring(0, strValues.length()-1) + ")";
		strConsulta += strValues;
		
		executeNonQuery(strConsulta);
	}

	public static void modificar(Object obj){
		String strConsulta = "update ";
		String id = "";
		ArrayList<Field> listaAttr;
		String attrValue ;
		strConsulta += obj.getClass().getAnnotation(Tabla.class).nombre() + " set ";
		listaAttr = UBean.obtenerAtributos(obj);
		
		for(Field atributo: listaAttr) {
			attrValue = UBean.ejecutarGet(obj, atributo.getName()).toString();
			if(atributo.getAnnotation(Columna.class).nombre().equalsIgnoreCase("id")) {
				id = attrValue;
			} else {
		    	strConsulta += atributo.getAnnotation(Columna.class).nombre() + "=";
		    	if(atributo.getType().equals(String.class))
		    	{
		    		strConsulta += "'" + attrValue + "', ";
		    	}else {
		    		strConsulta += attrValue ;
		    	}
			}
		}
		strConsulta = strConsulta.substring(0, strConsulta.length() - 2);
		strConsulta += " where id=" + id;
		
		executeNonQuery(strConsulta);
	}

	public static void eliminar(Object obj) {
		ArrayList<Field> listaAttr;
		String strConsulta = "delete from ";
		String id = "";
		strConsulta += obj.getClass().getAnnotation(Tabla.class).nombre() + " ";
		listaAttr = UBean.obtenerAtributos(obj);
		
		for(Field atributo: listaAttr) {
			if(atributo.getAnnotation(Columna.class).nombre().equals("id")) {
				id = UBean.ejecutarGet(obj, atributo.getName()).toString();
				break;
			}
		}
		
		strConsulta += "where id=" + id;
		executeNonQuery(strConsulta);
	}
	
	public static Object obtenerPorId(Class clazz, Object id) {
				
		Object obj = null;
		String strConsulta = "select * from ";
		try {
			obj = clazz.newInstance();
			strConsulta += obj.getClass().getAnnotation(Tabla.class).nombre() + " "; 
			strConsulta += "where id=" + id;

			obj = executeQuery(strConsulta, clazz);
			
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}