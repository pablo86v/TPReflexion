package Anotaciones;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface Tabla {
	String nombre() default "Sin nombre";
}
