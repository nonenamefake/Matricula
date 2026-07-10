package Modelo;

import java.io.Serializable;

public class NodoArbol implements Serializable {
    public Estudiantes estudiante;
    public NodoArbol izquierdo;
    public NodoArbol derecho;

    public NodoArbol(Estudiantes e) {
        this.estudiante = e;
        izquierdo = derecho = null;
    }
}
