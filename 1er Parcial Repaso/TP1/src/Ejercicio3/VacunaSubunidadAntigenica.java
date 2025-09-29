package Ejercicio3;

public class VacunaSubunidadAntigenica extends Vacuna {
    private int antigenos;
    private String proceso;

    public VacunaSubunidadAntigenica(int antigenos, String proceso) {
        this.antigenos = antigenos;
        this.proceso = proceso;
    }

    public VacunaSubunidadAntigenica(String marca, String enfermedad, int dosis, String pais, int antigenos, String proceso) {
        super(marca, enfermedad, dosis, pais);
        this.antigenos = antigenos;
        this.proceso = proceso;
    }

    public int getAntigenos() {
        return antigenos;
    }

    public void setAntigenos(int antigenos) {
        this.antigenos = antigenos;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }
}
