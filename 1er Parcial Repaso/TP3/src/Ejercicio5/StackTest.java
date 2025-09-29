package Ejercicio5;

import java.util.Iterator;

public class StackTest {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push("Test1");
        stack.push("Test2");
        stack.push("Test3");

        System.out.println(stack.isEmpty());

        //Stack.StackIterator it = stack.new StackIterator();
        Iterator<Object> iterador =  stack.iterator();
        while(iterador.hasNext()){
            System.out.println(iterador.next());
        }

    }
}
