package Modelo;

import Almacenamiento.EstudiantePersistencia;
import java.io.Serializable;

public class ArbolEstudiantes implements Serializable {
    public NodoArbol raiz;

    public ArbolEstudiantes() {
        raiz = null;
        construirDesdeLista();
    }

    private void construirDesdeLista() {
        ListaEstudiantes le = EstudiantePersistencia.RecuperarLista();
        for (NodoEstudiantes aux = le.ini; aux != null; aux = aux.sig)
            insertar(aux.es);
    }

    public void insertar(Estudiantes e) {
        raiz = insertarRec(raiz, e);
    }

    private NodoArbol insertarRec(NodoArbol r, Estudiantes e) {
        if (r == null) return new NodoArbol(e);
        if (e.getDni() < r.estudiante.getDni())
            r.izquierdo = insertarRec(r.izquierdo, e);
        else if (e.getDni() > r.estudiante.getDni())
            r.derecho = insertarRec(r.derecho, e);
        return r;
    }

    public Estudiantes buscar(int dni) {
        return buscarRec(raiz, dni);
    }

    private Estudiantes buscarRec(NodoArbol r, int dni) {
        if (r == null) return null;
        if (dni == r.estudiante.getDni()) return r.estudiante;
        if (dni < r.estudiante.getDni())
            return buscarRec(r.izquierdo, dni);
        else
            return buscarRec(r.derecho, dni);
    }

    public String recorridoPreOrden() {
        StringBuilder sb = new StringBuilder();
        preOrdenRec(raiz, sb);
        return sb.toString();
    }

    private void preOrdenRec(NodoArbol r, StringBuilder sb) {
        if (r == null) return;
        sb.append(r.estudiante.getDni()).append(" - ").append(r.estudiante.getNombres()).append("\n");
        preOrdenRec(r.izquierdo, sb);
        preOrdenRec(r.derecho, sb);
    }

    public String recorridoInOrden() {
        StringBuilder sb = new StringBuilder();
        inOrdenRec(raiz, sb);
        return sb.toString();
    }

    private void inOrdenRec(NodoArbol r, StringBuilder sb) {
        if (r == null) return;
        inOrdenRec(r.izquierdo, sb);
        sb.append(r.estudiante.getDni()).append(" - ").append(r.estudiante.getNombres()).append("\n");
        inOrdenRec(r.derecho, sb);
    }

    public String recorridoPostOrden() {
        StringBuilder sb = new StringBuilder();
        postOrdenRec(raiz, sb);
        return sb.toString();
    }

    private void postOrdenRec(NodoArbol r, StringBuilder sb) {
        if (r == null) return;
        postOrdenRec(r.izquierdo, sb);
        postOrdenRec(r.derecho, sb);
        sb.append(r.estudiante.getDni()).append(" - ").append(r.estudiante.getNombres()).append("\n");
    }
}
