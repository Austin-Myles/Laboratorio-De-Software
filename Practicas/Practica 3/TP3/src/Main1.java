//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main1 {
    public static void main(String[] args) {

        Stack pila = new Stack();
        pila.push(5);
        pila.push(6);
        pila.push(7);
        pila.push(8);

        /**
         * Al no haber definido a StackIterator como una clase estatica
         * Es necesario instanciar Stack para poder construirlo adecuadamente.
         * Debido a que cada StackIterator debe estar anidado a una Stack especifica.
         *
         */
        Stack.StackIterator it = pila.new StackIterator();
    }
}