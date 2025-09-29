package Ejercicio3;

public class TestVacuna {
    public static void main(String[] args) {
        Vacuna vac1 = new Vacuna("Sputnik","COVID",2,"Rusia Papa");
        Vacuna vac2 = new VacunaPatogenoIntegro("Sputnik","COVID",2,"Rusia Papa","Eeee idk");
        Vacuna vac3 = new VacunaSubunidadAntigenica("Sputnik","COVID",2,"Rusia Papa",2,"Bordeadora");
        Vacuna vac4 = new VacunaGenetica("Sputnik","COVID",2,"Rusia Papa",40.0,0.1);
        Vacuna[] vacunas = new Vacuna[4];
        vacunas[0]=vac1;
        vacunas[1]=vac2;
        vacunas[2]=vac3;
        vacunas[3]=vac4;

        for (Vacuna vacuna : vacunas) {
            System.out.println(vacuna);
        }
    }
}
