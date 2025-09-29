package Ejercicio8;

public enum FrecuenciasDeLA {
    HZ440("Organización Internacional de Estandarización ISO 16"),
    HZ444("Afinación de cámara"),
    HZ446("Renacimiento"),
    HZ480("Órganos alemanes que tocaba Bach");

    private String descripcion;

    FrecuenciasDeLA(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
