import java.util.*;
import java.util.stream.Collectors;

public class Facultad {
    private String nombre;
    private String identificador;
    private List<Alumno> alumnos;

    public Facultad(String nombre, String identificador, List<Alumno> alumnos) {
        this.nombre = nombre;
        this.identificador = identificador;
        this.alumnos = alumnos;
    }

    public Facultad(String nombre, String identificador) {
        this.nombre = nombre;
        this.identificador = identificador;
        this.alumnos = new ArrayList<>();
    }

    public Optional<Alumno> getAlumnoConMejorNota(){
        return this.alumnos.stream().max(Comparator.comparing(Alumno::getMayorNota));
    }

    public void imprimirEstudiantes(){
        alumnos.stream().limit(2).forEach(alumno -> System.out.println(alumno.getNombre()));
    }

    public Alumno getAlumnoLaboratorio(){
        return this.alumnos.stream().filter(alumno -> alumno.getMateriasAprobadas()
                .stream()
                .anyMatch(materia -> materia.equals("Laboratorio de Software"))).findFirst().orElse(null);
    }

    public List<Alumno> getAlumnosConNombrePMenorA6(){
        return this.alumnos.stream().filter(alumno -> alumno.getNombre().startsWith("P")|| alumno.getNombre().length()<=6)
                .collect(Collectors.toList());
    }

    //ITEM B
    static class ordenarPorNotaComparator implements Comparator<Alumno>{
        @Override
        public int compare(Alumno o1, Alumno o2) {
            return Double.compare(o2.getMayorNota(), o1.getMayorNota());
        }
    }

    //ITEM B - 1
    public void ordenarPorNota(){
        alumnos.sort((o1,o2) -> Double.compare(o2.getMayorNota(), o1.getMayorNota()));
    }

    //ITEM B - 2
    public void ordenarPorNotaB(){
        alumnos.sort(Comparator.comparingDouble(Alumno::getMayorNota).reversed());
    }

}
