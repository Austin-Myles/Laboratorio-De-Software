
import java.util.*;

public class Alumno{
    private String nombre;
    private String apellido;
    private int edad;
    private String legajo;
    private List<String> materiasAprobadas;
    private List<Double> notasAprobadas;

    public Alumno(String nombre, String apellido, int edad, String legajo, List<String> materiasAprobadas, List<Double> notasAprobadas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.legajo = legajo;
        this.materiasAprobadas = materiasAprobadas;
        this.notasAprobadas = notasAprobadas;
    }

    public Alumno(String nombre, String apellido, int edad, String legajo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.legajo = legajo;
    }

    public double getMayorNota(){
        return this.notasAprobadas.stream().max(Double::compare).get();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public List<String> getMateriasAprobadas() {
        return materiasAprobadas;
    }

    public void setMateriasAprobadas(List<String> materiasAprobadas) {
        this.materiasAprobadas = materiasAprobadas;
    }

    public List<Double> getNotasAprobadas() {
        return notasAprobadas;
    }

    public void setNotasAprobadas(List<Double> notasAprobadas) {
        this.notasAprobadas = notasAprobadas;
    }
}
