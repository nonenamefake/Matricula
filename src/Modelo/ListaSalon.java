package Modelo;
import java.io.Serializable;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListaSalon implements Serializable {
    public NodoSalon ini;
    public NodoSalon fin;

    public ListaSalon() { ini = fin = null; }

    public void MostrarSalones(JTable tabla) {
        String titulos[] = {"id", "Codigo", "Nombre", "Capacidad", "Grado"};
        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        tabla.setModel(mt);
        for (NodoSalon aux = ini; aux != null; aux = aux.sig)
            mt.addRow(aux.s.Registro());
    }

    public void InsertarSalon(Salon s) {
        NodoSalon nuevo = new NodoSalon(s);
        if (ini == null) { ini = fin = nuevo; }
        else { nuevo.ant = fin; fin.sig = nuevo; }
        fin = nuevo;
        fin.sig = null;
    }

    public void EliminarSalon(NodoSalon actual) {
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

    public NodoSalon BuscarPorCodigo(String codigo) {
        for (NodoSalon aux = ini; aux != null; aux = aux.sig)
            if (aux.s.getCodigo().equals(codigo)) return aux;
        return null;
    }
}
