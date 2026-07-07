package Modelo;
import java.io.Serializable;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListaGradoCurso implements Serializable {
    public NodoGradoCurso ini;
    public NodoGradoCurso fin;

    public ListaGradoCurso() { ini = fin = null; }

    public void MostrarTodos(JTable tabla) {
        String titulos[] = {"id", "Grado", "Curso"};
        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        tabla.setModel(mt);
        for (NodoGradoCurso aux = ini; aux != null; aux = aux.sig)
            mt.addRow(aux.gc.Registro());
    }

    public void MostrarPorGrado(JTable tabla, int idGrado) {
        String titulos[] = {"id", "Grado", "Curso"};
        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        tabla.setModel(mt);
        for (NodoGradoCurso aux = ini; aux != null; aux = aux.sig)
            if (aux.gc.getId_grado() == idGrado)
                mt.addRow(aux.gc.Registro());
    }

    public void InsertarGradoCurso(GradoCurso gc) {
        NodoGradoCurso nuevo = new NodoGradoCurso(gc);
        if (ini == null) { ini = fin = nuevo; }
        else { nuevo.ant = fin; fin.sig = nuevo; }
        fin = nuevo;
        fin.sig = null;
    }

    public void EliminarGradoCurso(NodoGradoCurso actual) {
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

    public NodoGradoCurso BuscarPorIdCurso(int idCurso) {
        for (NodoGradoCurso aux = ini; aux != null; aux = aux.sig)
            if (aux.gc.getId_curso() == idCurso) return aux;
        return null;
    }
}
