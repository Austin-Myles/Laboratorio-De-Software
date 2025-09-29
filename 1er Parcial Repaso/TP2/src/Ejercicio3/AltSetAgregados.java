package Ejercicio3;

import java.util.Collection;
import java.util.HashSet;

public class AltSetAgregados<E> extends HashSet<E> {
    private int cantidadAgregados = 0;
    private int cantidadEliminados = 0;
    public AltSetAgregados() {
    }
    public AltSetAgregados(int initCap, float loadFactor) {
        super(initCap, loadFactor);
    }
    @Override public boolean add(E e) {
        boolean exito = super.add(e);
        if (exito) {
            cantidadAgregados++;
        }
        return exito;
    }
    @Override public boolean remove(Object o) {
        boolean exito = super.remove(o);
        if (exito) {
            cantidadEliminados++;
        }
        return exito;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return super.addAll(c);
    }
    public int getCantidadAgregados() {
        return cantidadAgregados;
    }

    public int getCantidadEliminados() {return cantidadEliminados;}
}
