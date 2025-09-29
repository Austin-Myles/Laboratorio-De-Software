package Ejercicio1;

public class TestVacuna {
    public static void main(String[] args) {
        Vacuna vac1 = new Vacuna("Sputnik","COVID",2,"Rusia Papa");
        Vacuna[] vacunas = new Vacuna[5];
        vacunas[0]=vac1;
        vacunas[1]=vac1;
        vacunas[2]=vac1;
        vacunas[3]=vac1;
        vacunas[4]=vac1;

        for (Vacuna vacuna : vacunas) {
            System.out.println(vacuna);
        }
    }
}
