package Ejercicio3;

public class VacunaPatogenoIntegro extends Vacuna {
    private String patogeno;

    public VacunaPatogenoIntegro(String patogeno) {
        this.patogeno = patogeno;
    }

    public VacunaPatogenoIntegro(String marca, String enfermedad, int dosis, String pais, String patogeno) {
        super(marca, enfermedad, dosis, pais);
        this.patogeno = patogeno;
    }

    public String getPatogeno() {
        return patogeno;
    }

    public void setPatogeno(String patogeno) {
        this.patogeno = patogeno;
    }
}
