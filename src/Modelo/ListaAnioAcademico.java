package Modelo;
import java.io.Serializable;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListaAnioAcademico implements Serializable {
    public NodoAnioAcademico ini;
    public NodoAnioAcademico fin;

    public ListaAnioAcademico() { ini = fin = null; }

    public void MostrarAnios(JTable tabla) {
        String titulos[] = {"id", "A\u00f1o", "Inicio", "Fin", "Estado"};
        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        tabla.setModel(mt);
        for (NodoAnioAcademico aux = ini; aux != null; aux = aux.sig)
            mt.addRow(aux.aa.Registro());
    }

    public void InsertarAnio(AnioAcademico aa) {
        NodoAnioAcademico nuevo = new NodoAnioAcademico(aa);
        if (ini == null) { ini = fin = nuevo; }
        else { nuevo.ant = fin; fin.sig = nuevo; }
        fin = nuevo;
        fin.sig = null;
    }

    public void EliminarAnio(NodoAnioAcademico actual) {
        if (actual == null) return;
        if (actual == ini) {
            ini = actual.sig;
            if (actual.sig != null) actual.sig.ant = null;
        } else if (actual.sig != null) {
            actual.ant.sig = actual.sig;
            actual.sig.ant = actual.ant;
        } else {
            actual.ant.sig = null;
            fin = actual.ant;
        }
        actual = null;
    }

    public NodoAnioAcademico BuscarPorAnio(String anio) {
        for (NodoAnioAcademico aux = ini; aux != null; aux = aux.sig)
            if (aux.aa.getAnio().equals(anio)) return aux;
        return null;
    }
}
