package Ejercicio3;

public class Vacuna {
    private String marca;
    private String enfermedad;
    private int dosis;
    private String pais;

    public Vacuna() {
    }

    public Vacuna(String marca, String enfermedad, int dosis, String pais) {
        this.marca = marca;
        this.enfermedad = enfermedad;
        this.dosis = dosis;
        this.pais = pais;
    }

    public String getMarca() {
        return marca;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public int getDosis() {
        return dosis;
    }

    public String getPais() {
        return pais;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setDosis(int dosis) {
        this.dosis = dosis;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    /**
     *
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Marca: ").append(marca).append("\n");
        sb.append("Enfermedad: ").append(enfermedad).append("\n");
        sb.append("Dosis: ").append(dosis).append("\n");
        sb.append("Pais: ").append(pais).append("\n");
        return sb.toString();
    }
     * @return
     */
}
