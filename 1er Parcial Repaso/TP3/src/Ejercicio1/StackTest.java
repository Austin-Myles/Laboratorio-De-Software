package Ejercicio1;

public class StackTest {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push("Test1");
        stack.push("Test2");
        stack.push("Test3");

        System.out.println(stack.isEmpty());

        //Stack.StackIterator it = stack.new StackIterator();
        Stack.StackIterator iterator = stack.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
