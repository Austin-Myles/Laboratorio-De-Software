public class VacunaSubunidadAntigenica extends Vacuna{
    private int cantAntigenos;
    private String tipoProceso;

    public VacunaSubunidadAntigenica(String marca, String origen, String enfermedad, int dosis, String tipoProceso, int cantAntigenos) {
        super(marca, origen, enfermedad, dosis);
        this.tipoProceso = tipoProceso;
        this.cantAntigenos = cantAntigenos;
    }

    public String getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(String tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

    public int getCantAntigenos() {
        return cantAntigenos;
    }

    public void setCantAntigenos(int cantAntigenos) {
        this.cantAntigenos = cantAntigenos;
    }
}
