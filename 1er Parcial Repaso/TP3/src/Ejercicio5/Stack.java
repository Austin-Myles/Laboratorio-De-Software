package Ejercicio5;

import java.util.Iterator;

public class Stack {
    private java.util.ArrayList items;

    public Stack(){
        this.items = new java.util.ArrayList();
    }
    public void push(Object obj){
        this.items.add(obj);
    }
    public Object pop(){
        Object aux = null;
        if(!this.items.isEmpty()){
            aux = this.items.getLast();
            this.items.remove(this.items.getLast());
        }
        return aux;
    }
    public boolean isEmpty(){
        return this.items.isEmpty();
    }

    public Iterator<Object> iterator(){
        return new Iterator<Object>(){
            private int indice = items.size() - 1;

            @Override
            public boolean hasNext() {
                return (indice >= 0);
            }

            @Override
            public Object next() {
                return items.get(indice--);
            }
        };
    }


}
