public enum FrecuenciasDeLA {
    LA440(440, "Organización Internacional de Estandarización ISO 16."),
    LA444(444, "Afinación de cámara."),
    LA446(446, "Renacimiento."),
    LA480(480, "Órganos alemanes que tocaba Bach.");

    private final String descripcion;
    private final int frecuencia;

    FrecuenciasDeLA(int frecuencia, String descripcion) {
        this.descripcion = descripcion;
        this.frecuencia = frecuencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getFrecuencia() {
        return frecuencia;
    }
}
