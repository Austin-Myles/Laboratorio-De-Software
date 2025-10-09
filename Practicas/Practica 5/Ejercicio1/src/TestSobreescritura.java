public class TestSobreescritura {
    @Override
    @SuppressWarnings({"deprecation"})
    public String toString() {
        return super.toString() + " Testeando: 'Override'";
    }
}