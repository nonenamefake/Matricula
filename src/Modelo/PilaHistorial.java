package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class PilaHistorial implements Serializable {
    private ArrayList<String> pila;

    public PilaHistorial() {
        pila = new ArrayList<>();
    }

    public void push(String anio) {
        pila.add(anio);
    }

    public String pop() {
        if (pila.isEmpty()) return null;
        return pila.remove(pila.size() - 1);
    }

    public String peek() {
        if (pila.isEmpty()) return null;
        return pila.get(pila.size() - 1);
    }

    public boolean isEmpty() {
        return pila.isEmpty();
    }
}
