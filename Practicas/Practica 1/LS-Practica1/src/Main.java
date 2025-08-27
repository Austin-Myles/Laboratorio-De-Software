import java.lang.reflect.Array;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        //Ejercicio 1

        //Inciso A
        Vacuna vac = new Vacuna("Sputnik","Rusia", "Covid", 2);
        //System.out.printf(vac.toString());
        //Inciso B

        /*
        Vacuna[] vacunas = new Vacuna[5];

        Vacuna v1 = new Vacuna("M1","O1","E1", 1);
        Vacuna v2 = new Vacuna("M2","O2","E2", 2);
        Vacuna v3 = new Vacuna("M3","O3","E3", 3);
        Vacuna v4 = new Vacuna("M4","O4","E4", 4);
        Vacuna v5 = new Vacuna("M5","O5","E5", 5);

        vacunas[0] = v1;
        vacunas[1] = v2;
        vacunas[2] = v3;
        vacunas[3] = v4;
        vacunas[4] = v5;

        for (Vacuna vacuna : vacunas) {
            System.out.println(vacuna);
        }
        */

        //Inciso D
        Vacuna vc = new Vacuna("Sinopharm","China","Covid", 1);
        //System.out.println(vc.equals(vac));

        //Ejercicio 3
        //Inciso c
        VacunaPatogenoIntegro vpi = new VacunaPatogenoIntegro("MP","OP","EP",3,"PP");
        System.out.println(vpi.toString());
        VacunaSubunidadAntigenica vsa = new VacunaSubunidadAntigenica("MSA","OSA","ESA",4,"PSA",4);
        System.out.println(vsa.toString());
        VacunaGenetica vg = new VacunaGenetica("MG","OG","EG",3,10.0,30.0);
        System.out.println(vg.toString());

    }

}