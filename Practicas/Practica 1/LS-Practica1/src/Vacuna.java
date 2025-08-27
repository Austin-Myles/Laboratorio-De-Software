public class Vacuna {
    private String marca;
    private String origen;
    private String enfermedad;
    private int dosis;

    public Vacuna(String marca, String origen, String enfermedad, int dosis) {
        this.marca = marca;
        this.origen = origen;
        this.enfermedad = enfermedad;
        this.dosis = dosis;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public int getDosis() {
        return dosis;
    }

    public void setDosis(int dosis) {
        this.dosis = dosis;
    }

    //Inciso A definimos el toString()

    //Inciso C comentamos el toString()

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Marca: ");
        sb.append(marca);
        sb.append(", Origen: ");
        sb.append(origen);
        sb.append(", Enfermedad: ");
        sb.append(enfermedad);
        sb.append(", Dosis: ");
        sb.append(dosis);
        return sb.toString();
    }

}
