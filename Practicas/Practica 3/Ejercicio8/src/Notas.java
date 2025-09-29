public enum Notas {
    C("Do"), D("Re"), E("Mi"), F("Fa"), G("Sol"), A("La"), B("Si");

    private final String nombre;

    private Notas(String nota) {
        this.nombre = nota;
    }

    public String getNombre() {
        return nombre;
    }
}
