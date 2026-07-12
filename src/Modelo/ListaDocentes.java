package Modelo;
import java.io.Serializable;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListaDocentes implements Serializable {
    public NodoDocentes ini;
    public NodoDocentes fin;
    public ListaDocentes() {
        ini = fin = null;
    }
    public void MostrarDocentes(JTable tabla) {
        String titulos[] = {"id", "Dni", "Nombres", "Apellido Paterno", "Apellido Materno", "Especialidad", "Telefono", "Correo"};
        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        tabla.setModel(mt);
        for (NodoDocentes aux = ini; aux != null; aux = aux.sig) {
            mt.addRow(aux.dc.Registro());
        }
    }
    public void InsertarDocentes(Docentes dc) {
        NodoDocentes nuevo = new NodoDocentes(dc);
        if (ini == null) {
            ini = fin = nuevo;
        } else {
            nuevo.ant = fin;
            fin.sig = nuevo;
        }
        fin = nuevo;
        fin.sig = null;
    }
    public void EliminarDocentes(NodoDocentes actual) {
        if (actual != null) {
            if (actual == ini) {
                ini = actual.sig;
                if (actual.sig != null)
                    actual.sig.ant = null;
            } else if (actual.sig != null) {
                actual.ant.sig = actual.sig;
                actual.sig.ant = actual.ant;
            } else {
                actual.ant.sig = null;
                fin = actual.ant;
            }
            actual = null;
        }
    }
    public NodoDocentes BuscarPordni(String dnibuscar) {
        NodoDocentes encontrado = ini;
        while (encontrado != null) {
            if (encontrado.dc.getDni().equals(dnibuscar))
                return encontrado;
            encontrado = encontrado.sig;
        }
        return null;
    }
}
