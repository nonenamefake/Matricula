package Modelo;
import java.io.Serializable;

public class NodoDocenteCurso implements Serializable {
    public DocenteCurso dc;
    public NodoDocenteCurso sig;
    public NodoDocenteCurso ant;
    public NodoDocenteCurso(DocenteCurso dc) {
        this.dc = dc;
        ant = sig = null;
    }
}
