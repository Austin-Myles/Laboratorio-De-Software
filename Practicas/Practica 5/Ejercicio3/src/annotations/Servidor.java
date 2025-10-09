package annotations;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
public @interface Servidor {
    String direccion() default "";
    int puerto() default 0;
    String archivo() default "";


}
