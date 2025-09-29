import java.util.Iterator;

public class Stack {
    //EJERCICIO 1
    private java.util.ArrayList items;

    public Stack() {
        this.items = new java.util.ArrayList();
    }
    public void push(Object item){
        this.items.add(item);
    }
    public Object pop(){
        Object item = this.items.getLast();
        if(item != null){
            this.items.remove(item);
        }
        return item;
    }
    public boolean isEmpty(){
        return this.items.isEmpty();
    }

    public class StackIterator implements Iterator {
        private int currentIndex;

        public StackIterator(){
            this.currentIndex = items.size() - 1;
        }

        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }

        @Override
        public Object next() {
            return items.get(currentIndex--);
        }
    }

    public Iterator iterator(){
        return new StackIterator();
    }
}
