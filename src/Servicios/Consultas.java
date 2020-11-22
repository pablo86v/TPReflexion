package Servicios;


import java.lang.reflect.Field;
import java.util.ArrayList;

import Anotaciones.Columna;
import Anotaciones.Tabla;
import Utilidades.UBean;

public class Consultas {
	
	public static void guardar(Object obj){
		ArrayList<Field> listaAttr;
		String strConsulta = "insert into ";
		strConsulta += obj.getClass().getAnnotation(Tabla.class).nombre() + " (";
		listaAttr = UBean.obtenerAtributos(obj);
		
		for(Field atributo: listaAttr) {
			strConsulta += atributo.getAnnotation(Columna.class).nombre() + ", ";
		}
		
		strConsulta = strConsulta.substring(0, strConsulta.length() - 2);
		
		strConsulta += ") values (?,?,?)";
		
		//TODO ejecutar la sentencia sql
		System.out.println(strConsulta);		
	}

	public static void modificar(Object obj){
		String strConsulta = "update ";
		String id = "";
		ArrayList<Field> listaAttr;
		strConsulta += obj.getClass().getAnnotation(Tabla.class).nombre() + " set ";
		listaAttr = UBean.obtenerAtributos(obj);

		for(Field atributo: listaAttr) {
			if(atributo.getAnnotation(Columna.class).nombre().equalsIgnoreCase("id")) {
				id = UBean.ejecutarGet(obj, atributo.getName()).toString();
			} else {
		    	strConsulta += atributo.getAnnotation(Columna.class).nombre() + "=";
				strConsulta += "'" + UBean.ejecutarGet(obj, atributo.getName()).toString() + "' ";	
			}
		}
		
		strConsulta += "where id=" + id;
		
		//TODO ejecutar
		System.out.println(strConsulta);
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
		System.out.println(strConsulta);
	}
	
	public static void obtenerPorId(Class c, Object id) {
		
	}
	
}