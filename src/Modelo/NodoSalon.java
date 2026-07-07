package Modelo;
import java.io.Serializable;

public class NodoSalon implements Serializable {
    public Salon s;
    public NodoSalon sig;
    public NodoSalon ant;
    public NodoSalon(Salon s) {
        this.s = s;
        ant = sig = null;
    }
}
