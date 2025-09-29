package practica4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Materia {
    private String info;
    private ArrayList<Persona> nomina;

    public Materia(String info) {
        this.info = info;
        this.nomina = new ArrayList<>();
    }

    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }

    public ArrayList<Persona> getNomina() {
        return nomina;
    }

    public void agregarAlumno(Alumno alumno) {
        this.nomina.add(alumno);
        this.ordenarAlumno();
    }

    public void ordenarAlumno(){
        this.nomina.sort(new Comparator<Persona>() {
            @Override
            public int compare(Persona o1, Persona o2) {
                return o1.getNombre().compareTo(o2.getNombre());
            }
        });
    }
}
