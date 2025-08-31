package Ejercicio3;

import java.util.Collection;
import java.util.HashSet;

public class HashSetAgregadosFix<E> extends HashSet<E> {
    private int cantidadAgregados = 0;
    private int cantidadEliminados = 0;
    public HashSetAgregadosFix() {
    }
    public HashSetAgregadosFix(int initCap, float loadFactor) {
        super(initCap, loadFactor);
    }
    @Override
    public boolean add(E e) {
        if(super.add(e)) {
            cantidadAgregados++;
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean ret = false;
        for (E e : c) {
            ret |= this.add(e);
        }
        return ret;
    }
    public int getCantidadAgregados() {
        return cantidadAgregados;
    }

    public boolean eliminar(E e) {
        if(super.remove(e)) {
            cantidadEliminados++;
            return true;
        }
        else{
            return false;
        }
    }

    public int getCantidadEliminados() { return cantidadEliminados; }
}