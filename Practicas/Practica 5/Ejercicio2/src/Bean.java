import annotations.AlmacenarAtributo;
import annotations.Archivo;

@Archivo(name="ArchivoMapeado.txt")
public class Bean {
    @AlmacenarAtributo
    private String valor = "Testeo xd";

    @AlmacenarAtributo
    private Integer valor2 = 14;

    @AlmacenarAtributo
    private Float valor3 = 3.1415F;

    public Bean() {
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Integer getValor2() {
        return valor2;
    }

    public void setValor2(Integer valor2) {
        this.valor2 = valor2;
    }

    public Float getValor3() {
        return valor3;
    }

    public void setValor3(Float valor3) {
        this.valor3 = valor3;
    }

    public static void main(String[] args) {
        Bean b = new Bean();
        ProcesadorMapeo.mapearYAlmacenar(b);
    }
}

