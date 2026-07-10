package Modelo;
import java.io.Serializable;

public class NodoMatricula implements Serializable {
    public Matricula m;
    public NodoMatricula sig;
    public NodoMatricula ant;
    public NodoMatricula(Matricula m) {
        this.m = m;
        ant = sig = null;
    }
}
