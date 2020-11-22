package Servicios;


import java.lang.reflect.Field;
import java.util.ArrayList;

import Anotaciones.Columna;
import Anotaciones.Tabla;
import Utilidades.UBean;

public class Consultas {
	
	public static void guardar(Object obj){
		String consulta = "insert into ";
		ArrayList<Field> listaAtributos;
		
		consulta += obj.getClass().getAnnotation(Tabla.class).nombre() + " (";
		listaAtributos = UBean.obtenerAtributos(obj);
		
		for(Field atributo: listaAtributos) {
			consulta += atributo.getAnnotation(Columna.class).nombre() + ", ";
		}
		
		consulta = consulta.substring(0, consulta.length() - 2);
		
		consulta += ") values (?,?,?)";
		
		//TODO ejecutar la sentencia sql
		System.out.println(consulta);		
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

	}
	
	public static void obtenerPorId(Class c, Object id) {
		
	}
	
}