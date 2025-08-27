public class VacunaPatogenoIntegro extends Vacuna{
    private String patogeno;

    public VacunaPatogenoIntegro(String marca, String origen, String enfermedad, int dosis,  String patogeno) {
        super(marca, origen, enfermedad, dosis);
        this.patogeno = patogeno;
    }

    public String getPatogeno() {
        return patogeno;
    }

    public void setPatogeno(String patogeno) {
        this.patogeno = patogeno;
    }
}
