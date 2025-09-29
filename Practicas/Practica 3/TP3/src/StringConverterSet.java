import java.util.*;

public class StringConverterSet<E> extends AbstractSet<String> {
    private Set<E> delegate = new HashSet<>();

    @Override
    public Iterator<String> iterator() {
        return new IteratorStringAdapter(delegate.iterator());
    }

    @Override
    public int size() {
        return delegate.size();
    }

    // EJERCICIO 3

    /**
     * Definir un adapter llamado IteratorStringAdapter como una clase anidada de
     * StringConverterSet.
     * @return
     */

    public class IteratorStringAdapter implements Iterator<String> {
        private Iterator<E> it;

        public IteratorStringAdapter(Iterator<E> it) {
            this.it = it;
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public String next() {
            Object obj = it.next();
            return (obj == null) ? "null" : obj.toString();
        }

        @Override
        public void remove() {
            it.remove();
        }
    }
}
