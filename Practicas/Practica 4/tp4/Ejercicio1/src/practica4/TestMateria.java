package practica4;

public class TestMateria
{
    public static void main(String[] args) {
        Materia testMateria = new Materia("Quimica");

        Alumno a1 = new Alumno("Regular", "1001", "García", "Martín", "45123987");
        Alumno a2 = new Alumno("Libre", "1002", "Pérez", "Lucía", "39874521");
        Alumno a3 = new Alumno("Regular", "1003", "Fernández", "Sofía", "41236598");
        Alumno a4 = new Alumno("Condicional", "1004", "López", "Julián", "38945612");
        Alumno a5 = new Alumno("Regular", "1005", "Martínez", "Camila", "40231547");
        Alumno a6 = new Alumno("Libre", "1006", "Díaz", "Mateo", "39547821");
        Alumno a7 = new Alumno("Regular", "1007", "Romero", "Valentina", "42319876");
        Alumno a8 = new Alumno("Condicional", "1008", "Sánchez", "Tomás", "38745123");
        Alumno a9 = new Alumno("Regular", "1009", "Torres", "Agustina", "41987654");
        Alumno a10 = new Alumno("Expulsado","1010","Hopkins","James","0000001");

        testMateria.agregarAlumno(a1);
        testMateria.agregarAlumno(a2);
        testMateria.agregarAlumno(a3);
        testMateria.agregarAlumno(a4);
        testMateria.agregarAlumno(a5);
        testMateria.agregarAlumno(a6);
        testMateria.agregarAlumno(a7);
        testMateria.agregarAlumno(a8);
        testMateria.agregarAlumno(a9);
        testMateria.agregarAlumno(a10);

        for(int i=0; i < testMateria.getNomina().size(); i++){
            System.out.println(testMateria.getNomina().get(i).getApellido());
        }

    }
}
