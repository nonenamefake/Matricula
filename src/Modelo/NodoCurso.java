package Modelo;

import java.io.Serializable;

public class NodoCurso implements Serializable {
    public Curso c;
    public NodoCurso sig;
    public NodoCurso ant;

    public NodoCurso(Curso c) {
        this.c = c;
        ant = sig = null;
    }
}
