package Modelo;
import java.io.Serializable;

public class NodoHorario implements Serializable {
    public Horario h;
    public NodoHorario sig;
    public NodoHorario ant;
    public NodoHorario(Horario h) {
        this.h = h;
        ant = sig = null;
    }
}
