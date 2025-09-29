package practica4;

public class Alumno extends Persona{
    private String info;
    private String legajo;

    public Alumno(String info, String legajo, String apellido, String nombre, String DNI) {
        super(apellido, nombre, DNI);
        this.info = info;
        this.legajo = legajo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

}
