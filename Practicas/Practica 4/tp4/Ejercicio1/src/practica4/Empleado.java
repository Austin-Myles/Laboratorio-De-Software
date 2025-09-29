package practica4;

public class Empleado extends Persona {

    private String tipo;
    private String codigo;

    public Empleado(String nombre, String DNI, String apellido, String tipo, String codigo) {
        super(nombre, DNI, apellido);
        this.tipo = tipo;
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
