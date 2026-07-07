package Modelo;

import java.io.Serializable;

public class NodoDocentes implements Serializable {
    public Docentes dc;
    public NodoDocentes sig;
    public NodoDocentes ant;

    public NodoDocentes(Docentes d) {
        this.dc = d;
        ant = sig = null;
    }
}
