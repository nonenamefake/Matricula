package Modelo;

import java.io.Serializable;

public class ColaEstudiantes implements Serializable {
    public NodoCola frente;
    public NodoCola atras;

    public ColaEstudiantes() {
        frente = atras = null;
    }

    public void encolar(int idEstudiante, String nombre) {
        NodoCola nuevo = new NodoCola(idEstudiante, nombre);
        if (atras == null) {
            frente = atras = nuevo;
        } else {
            atras.sig = nuevo;
            atras = nuevo;
        }
    }

    public NodoCola desencolar() {
        if (frente == null) return null;
        NodoCola aux = frente;
        frente = frente.sig;
        if (frente == null) atras = null;
        aux.sig = null;
        return aux;
    }

    public NodoCola obtenerFrente() {
        return frente;
    }

    public boolean estaVacia() {
        return frente == null;
    }
}
