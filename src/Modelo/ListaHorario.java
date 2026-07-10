package Modelo;
import Almacenamiento.DocenteCursoPersistencia;
import Almacenamiento.SalonPersistencia;
import Almacenamiento.CursoPersistencia;
import Almacenamiento.DocentePersistencia;
import java.io.Serializable;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListaHorario implements Serializable {
    public NodoHorario ini;
    public NodoHorario fin;

    public ListaHorario() { ini = fin = null; }

    private String nombreDocente(int idDocenteCurso) {
        ListaDocenteCurso ldc = DocenteCursoPersistencia.RecuperarLista();
        for (NodoDocenteCurso aux = ldc.ini; aux != null; aux = aux.sig)
            if (aux.dc.getId_docente_curso() == idDocenteCurso) {
                ListaDocentes ld = DocentePersistencia.RecuperarLista();
                for (NodoDocentes d = ld.ini; d != null; d = d.sig)
                    if (d.dc.getId_docente() == aux.dc.getId_docente())
                        return d.dc.getNombres() + " " + d.dc.getApellido_pa();
            }
        return "Desconocido";
    }

    private String nombreCurso(int idDocenteCurso) {
        ListaDocenteCurso ldc = DocenteCursoPersistencia.RecuperarLista();
        for (NodoDocenteCurso aux = ldc.ini; aux != null; aux = aux.sig)
            if (aux.dc.getId_docente_curso() == idDocenteCurso) {
                ListaGradoCurso lg = Almacenamiento.GradoCursoPersistencia.RecuperarLista();
                for (NodoGradoCurso g = lg.ini; g != null; g = g.sig)
                    if (g.gc.getId_grado_curso() == aux.dc.getId_grado_curso()) {
                        ListaCurso lc = CursoPersistencia.RecuperarLista();
                        for (NodoCurso c = lc.ini; c != null; c = c.sig)
                            if (c.c.getId_curso() == g.gc.getId_curso())
                                return c.c.getNombre();
                    }
            }
        return "Desconocido";
    }

    private String nombreGrado(int idDocenteCurso) {
        ListaDocenteCurso ldc = DocenteCursoPersistencia.RecuperarLista();
        for (NodoDocenteCurso aux = ldc.ini; aux != null; aux = aux.sig)
            if (aux.dc.getId_docente_curso() == idDocenteCurso) {
                ListaGradoCurso lg = Almacenamiento.GradoCursoPersistencia.RecuperarLista();
                for (NodoGradoCurso g = lg.ini; g != null; g = g.sig)
                    if (g.gc.getId_grado_curso() == aux.dc.getId_grado_curso())
                        return GradoCurso.nombreGrado(g.gc.getId_grado());
            }
        return "Desconocido";
    }

    private String codigoSalon(int idSalon) {
        ListaSalon ls = SalonPersistencia.RecuperarLista();
        for (NodoSalon aux = ls.ini; aux != null; aux = aux.sig)
            if (aux.s.getId_salon() == idSalon)
                return aux.s.getCodigo();
        return "Desconocido";
    }

    public void MostrarPorSalon(JTable tabla, int idSalon) {
        String titulos[] = {"id", "Docente", "Grado", "Curso", "Salon", "Dia", "Inicio", "Fin"};
        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        tabla.setModel(mt);
        for (NodoHorario aux = ini; aux != null; aux = aux.sig)
            if (aux.h.getId_salon() == idSalon) {
                Object[] fila = {aux.h.getId_horario(),
                                 nombreDocente(aux.h.getId_docente_curso()),
                                 nombreGrado(aux.h.getId_docente_curso()),
                                 nombreCurso(aux.h.getId_docente_curso()),
                                 codigoSalon(aux.h.getId_salon()),
                                 aux.h.getDia_semana(),
                                 aux.h.getHora_inicio(),
                                 aux.h.getHora_fin()};
                mt.addRow(fila);
            }
    }

    public void MostrarTodos(JTable tabla) {
        String titulos[] = {"id", "Docente", "Grado", "Curso", "Salon", "Dia", "Inicio", "Fin"};
        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        tabla.setModel(mt);
        for (NodoHorario aux = ini; aux != null; aux = aux.sig) {
            Object[] fila = {aux.h.getId_horario(),
                             nombreDocente(aux.h.getId_docente_curso()),
                             nombreGrado(aux.h.getId_docente_curso()),
                             nombreCurso(aux.h.getId_docente_curso()),
                             codigoSalon(aux.h.getId_salon()),
                             aux.h.getDia_semana(),
                             aux.h.getHora_inicio(),
                             aux.h.getHora_fin()};
            mt.addRow(fila);
        }
    }

    public void InsertarHorario(Horario h) {
        NodoHorario nuevo = new NodoHorario(h);
        if (ini == null) { ini = fin = nuevo; }
        else { nuevo.ant = fin; fin.sig = nuevo; }
        fin = nuevo;
        fin.sig = null;
    }

    public void EliminarHorario(NodoHorario actual) {
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

    public NodoHorario BuscarPorId(int id) {
        for (NodoHorario aux = ini; aux != null; aux = aux.sig)
            if (aux.h.getId_horario() == id) return aux;
        return null;
    }
}
