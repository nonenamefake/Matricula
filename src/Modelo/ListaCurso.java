package Modelo;
import java.io.Serializable;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListaCurso implements Serializable {
    public NodoCurso ini;
    public NodoCurso fin;

    public ListaCurso() {
        ini = fin = null;
    }

    public void MostrarCursos(JTable tabla) {
        String titulos[] = {"id", "Codigo", "Nombre", "Horas Semanales"};
        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        tabla.setModel(mt);
        for (NodoCurso aux = ini; aux != null; aux = aux.sig) {
            mt.addRow(aux.c.Registro());
        }
    }

    public void InsertarCursos(Curso c) {
        NodoCurso nuevo = new NodoCurso(c);
        if (ini == null) {
            ini = fin = nuevo;
        } else {
            nuevo.ant = fin;
            fin.sig = nuevo;
        }
        fin = nuevo;
        fin.sig = null;
    }

    public void EliminarCursos(NodoCurso actual) {
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

    public NodoCurso BuscarPorCodigo(String codigo) {
        NodoCurso encontrado = ini;
        while (encontrado != null) {
            if (encontrado.c.getCodigo().equals(codigo))
                return encontrado;
            encontrado = encontrado.sig;
        }
        return null;
    }
}
