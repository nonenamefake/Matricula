package Modelo;

import java.io.Serializable;

public class NodoCola implements Serializable {
    public int idEstudiante;
    public String nombreEstudiante;
    public NodoCola sig;

    public NodoCola(int id, String nombre) {
        this.idEstudiante = id;
        this.nombreEstudiante = nombre;
        this.sig = null;
    }
}
