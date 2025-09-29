package Ejercicio6;

public class Estudiante {
    private String apellido;
    private String nombre;
    private int edad;
    private String legajo;
    private String[] materiasAprobadas;

    public Estudiante(String apellido, String nombre, int edad, String legajo, String[] materiasAprobadas) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.edad = edad;
        this.legajo = legajo;
        this.materiasAprobadas = materiasAprobadas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String[] getMateriasAprobadas() {
        return materiasAprobadas;
    }

    public void setMateriasAprobadas(String[] materiasAprobadas) {
        this.materiasAprobadas = materiasAprobadas;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int cantidadMateriasAprobadas() {
        return materiasAprobadas.length;
    }
}
