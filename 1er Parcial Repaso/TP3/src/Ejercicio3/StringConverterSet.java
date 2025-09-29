package Ejercicio3;

import java.util.*;

public class StringConverterSet extends AbstractSet<Object> {
    private Set<Object> set = new HashSet<>();

    @Override
    public Iterator<Object> iterator() {
        return new IteratorStringAdapter(set.iterator());
    }

    @Override
    public int size() {
        return set.size();
    }

    public class IteratorStringAdapter implements Iterator {

        private Iterator iterador;

        public IteratorStringAdapter(Iterator iterador) {
            this.iterador = iterador;
        }
        @Override
        public boolean hasNext() {
            return this.iterador.hasNext();
        }

        @Override
        public Object next() {
            return this.iterador.next();
        }
    }
}
