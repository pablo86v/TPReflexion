package Utilidades;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class UBean {

	/**
	 * Devuelve un ArrayList<Field> con todos los atributos que posee el parámetro Object.
	 * @return ArrayList<Field>
	 */
	public static ArrayList<Field> obtenerAtributos(Object obj){
		
		ArrayList<Field> res = new ArrayList<>();
		Class<? extends Object> c = obj.getClass();
		Field [] attrs = c.getDeclaredFields();
		
		for(Field f :attrs) {
		 res.add(f);
		}
		return res;
			
	}
	
	/**
	 * Ejecuta el método Setter del String dentro del Object recibido por parámetro.
	 * @param att Nombre del atributo a invocar en el setter
	 * @param valor Nuevo valor para el atributo
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static void ejecutarSet(Object obj, String att, Object valor) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<? extends Object> c = obj.getClass();
		Field [] attrs = c.getDeclaredFields();
				
		for(Field f :attrs) {
			String nombreSetter = "set"+ att;
			Method[] metodos = c.getDeclaredMethods();

			for(Method m :metodos) {
				if(m.getName().equalsIgnoreCase(nombreSetter)) {
					Object[] params = new Object[1];
					if(f.getType().equals(String.class)) {
						params[0]="String";	
					}else if(f.getType().equals(int.class)) {
						params[0]=32639409;	
					}else {
						params[0]=null;
					}
                    m.invoke(obj, params);
				}
			}
		}
	}	
	
	/**
	 * Devolverá el valor del atributo pasado por parámetro, ejecutando el getter dentro del objeto.
	 * @param obj
	 * @param att
	 * @return
	 */
	public static Object ejecutarGet(Object obj, String att) {
		Class<? extends Object> oClass = obj.getClass();
		String nombreGetter = "get" + att;
		Object valor = null;
		Method[] metodos = oClass.getDeclaredMethods();
		
		for(Method m:metodos) {
			if(m.getName().equalsIgnoreCase(nombreGetter)) {
	
					try {
						valor = m.invoke(obj, null);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				break;
			}
		}
		
		return valor;
	}
}
