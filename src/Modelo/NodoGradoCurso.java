package Modelo;
import java.io.Serializable;

public class NodoGradoCurso implements Serializable {
    public GradoCurso gc;
    public NodoGradoCurso sig;
    public NodoGradoCurso ant;
    public NodoGradoCurso(GradoCurso gc) {
        this.gc = gc;
        ant = sig = null;
    }
}
