import Anotaciones.Columna;
import Anotaciones.Tabla;


@Tabla(nombre="Persona")
public class Persona {

	@Columna(nombre="id")
	private int id;
	@Columna(nombre="nombre")
	private String nombre;
	@Columna(nombre="apellido")
	public String apellido;
	
	public Persona() {
		
	}
	
	public Persona(int id, String nombre, String apellido) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre (String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}

	@Override
	public int hashCode() {
		int primo = 31;
	    int resultado =0;
	    resultado += primo * this.nombre.hashCode();
	    resultado += primo * this.apellido.hashCode();
	    return resultado;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o==null) {
			return false;
		}
		
		if(o.getClass().equals(this.getClass())) {
			Persona p = (Persona)o;
			if(p.nombre.equals(this.nombre) && p.apellido.equals(this.apellido)){
				return true;
			}
		}else {
			return false;
		}
		return false;

	}
	
	public void manejoExcepciones() {
		throw new RuntimeException("Lanzo Exception");
	}
	
	
}