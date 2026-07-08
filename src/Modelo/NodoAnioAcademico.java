package Modelo;
import java.io.Serializable;

public class NodoAnioAcademico implements Serializable {
    public AnioAcademico aa;
    public NodoAnioAcademico sig;
    public NodoAnioAcademico ant;
    public NodoAnioAcademico(AnioAcademico aa) {
        this.aa = aa;
        ant = sig = null;
    }
}
