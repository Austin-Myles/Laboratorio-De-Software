package Ejercicio3;

public class VacunaGenetica extends Vacuna{
    private double tempMin;
    private double tempMax;

    public VacunaGenetica(double tempMax, double tempMin) {
        this.tempMax = tempMax;
        this.tempMin = tempMin;
    }

    public VacunaGenetica(String marca, String enfermedad, int dosis, String pais, double tempMax, double tempMin) {
        super(marca, enfermedad, dosis, pais);
        this.tempMax = tempMax;
        this.tempMin = tempMin;
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
