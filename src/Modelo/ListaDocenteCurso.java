package Modelo;
import Almacenamiento.CursoPersistencia;
import Almacenamiento.AnioPersistencia;
import Almacenamiento.DocentePersistencia;
import java.io.Serializable;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListaDocenteCurso implements Serializable {
    public NodoDocenteCurso ini;
    public NodoDocenteCurso fin;

    public ListaDocenteCurso() { ini = fin = null; }

    private String nombreDocente(int idDocente) {
        ListaDocentes ld = DocentePersistencia.RecuperarLista();
        for (NodoDocentes aux = ld.ini; aux != null; aux = aux.sig)
            if (aux.dc.getId_docente() == idDocente)
                return aux.dc.getNombres() + " " + aux.dc.getApellido_pa() + " " + aux.dc.getApellido_ma();
        return "Desconocido";
    }

    private String nombreCurso(int idCurso) {
        ListaCurso lc = CursoPersistencia.RecuperarLista();
        for (NodoCurso aux = lc.ini; aux != null; aux = aux.sig)
            if (aux.c.getId_curso() == idCurso)
                return aux.c.getNombre();
        return "Desconocido";
    }

    private String anioNombre(int idAnio) {
        ListaAnioAcademico la = AnioPersistencia.RecuperarLista();
        for (NodoAnioAcademico aux = la.ini; aux != null; aux = aux.sig)
            if (aux.aa.getId_anio() == idAnio)
                return aux.aa.getAnio();
        return "Desconocido";
    }

    private int[] resolverGradoCurso(int idGradoCurso) {
        ListaGradoCurso lg = Almacenamiento.GradoCursoPersistencia.RecuperarLista();
        for (NodoGradoCurso aux = lg.ini; aux != null; aux = aux.sig)
            if (aux.gc.getId_grado_curso() == idGradoCurso)
                return new int[]{aux.gc.getId_grado(), aux.gc.getId_curso()};
        return new int[]{0, 0};
    }

    public void MostrarPorDocente(JTable tabla, int idDocente) {
        String titulos[] = {"id", "Docente", "Grado", "Curso", "A\u00f1o"};
        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        tabla.setModel(mt);
        for (NodoDocenteCurso aux = ini; aux != null; aux = aux.sig)
            if (aux.dc.getId_docente() == idDocente) {
                int[] gc = resolverGradoCurso(aux.dc.getId_grado_curso());
                Object[] fila = {aux.dc.getId_docente_curso(),
                                 nombreDocente(aux.dc.getId_docente()),
                                 GradoCurso.nombreGrado(gc[0]),
                                 nombreCurso(gc[1]),
                                 anioNombre(aux.dc.getId_anio())};
                mt.addRow(fila);
            }
    }

    public void MostrarTodos(JTable tabla) {
        String titulos[] = {"id", "Docente", "Grado", "Curso", "A\u00f1o"};
        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        tabla.setModel(mt);
        for (NodoDocenteCurso aux = ini; aux != null; aux = aux.sig) {
            int[] gc = resolverGradoCurso(aux.dc.getId_grado_curso());
            Object[] fila = {aux.dc.getId_docente_curso(),
                             nombreDocente(aux.dc.getId_docente()),
                             GradoCurso.nombreGrado(gc[0]),
                             nombreCurso(gc[1]),
                             anioNombre(aux.dc.getId_anio())};
            mt.addRow(fila);
        }
    }

    public void InsertarDocenteCurso(DocenteCurso dc) {
        NodoDocenteCurso nuevo = new NodoDocenteCurso(dc);
        if (ini == null) { ini = fin = nuevo; }
        else { nuevo.ant = fin; fin.sig = nuevo; }
        fin = nuevo;
        fin.sig = null;
    }

    public void EliminarDocenteCurso(NodoDocenteCurso actual) {
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

    public NodoDocenteCurso BuscarPorId(int id) {
        for (NodoDocenteCurso aux = ini; aux != null; aux = aux.sig)
            if (aux.dc.getId_docente_curso() == id) return aux;
        return null;
    }
}
