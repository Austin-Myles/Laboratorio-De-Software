package Ejercicio6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class TestEstudiante {
    public static void main(String[] args) {
        //Arrays.sort()
        Estudiante[] estudiantes = new Estudiante[5];

        Estudiante e1 = new Estudiante("González", "Martín", 21, "A001",
                new String[]{"Matemática"});
        Estudiante e2 = new Estudiante("Fernández", "Lucía", 22, "A002",
                new String[]{"Inglés", "Bases de Datos", "Matemática"});
        Estudiante e3 = new Estudiante("Ramírez", "Carlos", 20, "A003",
                new String[]{"Física", "Algoritmos"});
        Estudiante e4 = new Estudiante("Pérez", "Sofía", 23, "A004",
                new String[]{"Programación II", "Redes", "Matemática", "Ciberseguridad"});
        Estudiante e5 = new Estudiante("López", "Ana", 21, "A005",
                new String[]{"Matemática Discreta", "Estadística"});

        estudiantes[0] = e1;
        estudiantes[1] = e2;
        estudiantes[2] = e3;
        estudiantes[3] = e4;
        estudiantes[4] = e5;


        // Por cantidad de materias aprobadas en forma ascendente.
        /**
        Arrays.sort(estudiantes, new Comparator<Estudiante>(){
            @Override
            public int compare(Estudiante o1, Estudiante o2) {
                return Integer.compare(o1.cantidadMateriasAprobadas(), o2.cantidadMateriasAprobadas());
            }
        });
        System.out.println("Ordenamos por cantidad de materias aprobadas: ");
        **/

        //Por edad en forma descendente.
        /**
        Arrays.sort(estudiantes, new Comparator<Estudiante>() {
            @Override
            public int compare(Estudiante o1, Estudiante o2) {
                return Integer.compare(o2.getEdad(), o1.getEdad());
            }
        });
        **/

        // Por legajo en forma ascendente.
        /**
        Arrays.sort(estudiantes, new Comparator<Estudiante>() {
            @Override
            public int compare(Estudiante o1, Estudiante o2) {
                return o1.getLegajo().compareTo(o2.getLegajo());
            }
        });
        */

        // Por nombre y apellido en forma descendente.

        Arrays.sort(estudiantes, new Comparator<Estudiante>() {
            @Override
            public int compare(Estudiante o1, Estudiante o2) {
                int aux = o2.getApellido().compareTo(o1.getApellido());
                if (aux != 0) {
                    return aux;
                }
                return o2.getNombre().compareTo(o1.getNombre());
            }
        });


        for(Estudiante estudiante : estudiantes){
            System.out.println(estudiante.getApellido());
        }


    }
}
