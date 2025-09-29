import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class Main6 {
    public static void main(String[] args){
        Estudiante[] arreglo = new Estudiante[5];

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

        arreglo[0] = e1;
        arreglo[1] = e2;
        arreglo[2] = e3;
        arreglo[3] = e4;
        arreglo[4] = e5;

        System.out.println("Arreglo sin ordenar por materias aprobadas:\n");
        for (Estudiante estudiante : arreglo) {
            System.out.println(estudiante.getNombre());
        }

        // ORDENAMIENTO POR CANTIDAD DE MATERIAS APROBADAS EN FORMA ASC.
        /**
        Arrays.sort(arreglo, new Comparator<Estudiante>() {
            @Override
            public int compare(Estudiante o1, Estudiante o2) {
                return Integer.compare(o1.cantidadMateriasAprobadas(), o2.cantidadMateriasAprobadas());
            }
        });

        System.out.println("Arreglo ya ordenado por materias aprobadas:\n");
        for (Estudiante estudiante : arreglo) {
            System.out.println(estudiante.getNombre());
        }
         */

        // ORDENAMIENTO POR EDAD EN FORMA DESCENDENTE.
        Arrays.sort(arreglo, new Comparator<Estudiante>() {
            public int compare(Estudiante o1, Estudiante o2) {
                return Integer.compare(o2.getEdad(), o1.getEdad());
            }
        });

        // ORDENAMIENTO POR LEGAJO DE FORMA ASCENDENTE.
        Arrays.sort(arreglo, new Comparator<Estudiante>() {
            public int compare(Estudiante o1, Estudiante o2) {
                return o1.getLegajo().compareTo(o2.getLegajo());
            }
        });

        // ORDENAMIENTO POR NOMBRE Y APELLIDO EN FORMA DESCENDENTE.
        Arrays.sort(arreglo, new Comparator<Estudiante>() {
            @Override
            public int compare(Estudiante o1, Estudiante o2) {
                int cmpApellido = o2.getApellido().compareTo(o1.getApellido());
                if (cmpApellido != 0) {
                    return cmpApellido;
                }
                return o2.getNombre().compareTo(o1.getNombre());
            }
        });
    }
}
