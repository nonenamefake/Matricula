package Modelo;
import Almacenamiento.CursoPersistencia;
import java.io.Serializable;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListaGradoCurso implements Serializable {
    public NodoGradoCurso ini;
    public NodoGradoCurso fin;

    public ListaGradoCurso() { ini = fin = null; }

    public String nombreCurso(int idCurso) {
        ListaCurso lc = CursoPersistencia.RecuperarLista();
        for (NodoCurso aux = lc.ini; aux != null; aux = aux.sig)
            if (aux.c.getId_curso() == idCurso)
                return aux.c.getNombre();
        return "Desconocido";
    }

    public void MostrarTodos(JTable tabla) {
        String titulos[] = {"id", "Grado", "Curso"};
        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        tabla.setModel(mt);
        for (NodoGradoCurso aux = ini; aux != null; aux = aux.sig) {
            Object[] fila = {aux.gc.getId_grado_curso(),
                             GradoCurso.nombreGrado(aux.gc.getId_grado()),
                             nombreCurso(aux.gc.getId_curso())};
            mt.addRow(fila);
        }
    }

    public void MostrarPorGrado(JTable tabla, int idGrado) {
        String titulos[] = {"id", "Grado", "Curso"};
        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        tabla.setModel(mt);
        for (NodoGradoCurso aux = ini; aux != null; aux = aux.sig)
            if (aux.gc.getId_grado() == idGrado) {
                Object[] fila = {aux.gc.getId_grado_curso(),
                                 GradoCurso.nombreGrado(aux.gc.getId_grado()),
                                 nombreCurso(aux.gc.getId_curso())};
                mt.addRow(fila);
            }
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
    

    public NodoGradoCurso BuscarPorId(int id) {
        for (NodoGradoCurso aux = ini; aux != null; aux = aux.sig)
            if (aux.gc.getId_grado_curso()== id) return aux;
        return null;
    }
}
