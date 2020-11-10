package Anotaciones;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface Columna {
	String nombre() default "Sin nombre";
}
