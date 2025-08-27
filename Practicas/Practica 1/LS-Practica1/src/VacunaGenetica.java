public class VacunaGenetica extends Vacuna {
    private double tempMin;
    private double tempMax;

    public VacunaGenetica(String marca, String origen, String enfermedad, int dosis, double tempMin, double tempMax) {
        super(marca, origen, enfermedad, dosis);
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }
}
