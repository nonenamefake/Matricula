package Modelo;
import Almacenamiento.EstudiantePersistencia;
import Almacenamiento.AnioPersistencia;
import Almacenamiento.SalonPersistencia;
import java.io.Serializable;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListaMatricula implements Serializable {
    public NodoMatricula ini;
    public NodoMatricula fin;

    public ListaMatricula() { ini = fin = null; }

    private String nombreEstudiante(int idEstudiante) {
        ListaEstudiantes le = EstudiantePersistencia.RecuperarLista();
        for (NodoEstudiantes aux = le.ini; aux != null; aux = aux.sig)
            if (aux.es.getId() == idEstudiante)
                return aux.es.getNombres() + " " + aux.es.getApellido_pa() + " " + aux.es.getApellido_ma();
        return "Desconocido";
    }

    private String anioNombre(int idAnio) {
        ListaAnioAcademico la = AnioPersistencia.RecuperarLista();
        for (NodoAnioAcademico aux = la.ini; aux != null; aux = aux.sig)
            if (aux.aa.getId_anio() == idAnio)
                return aux.aa.getAnio();
        return "Desconocido";
    }

    private String salonCodigo(int idSalon) {
        ListaSalon ls = SalonPersistencia.RecuperarLista();
        for (NodoSalon aux = ls.ini; aux != null; aux = aux.sig)
            if (aux.s.getId_salon() == idSalon)
                return aux.s.getCodigo();
        return "Desconocido";
    }

    public void MostrarTodos(JTable tabla) {
        String titulos[] = {"id", "Estudiante", "Anio", "Grado", "Salon", "Fecha"};
        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        tabla.setModel(mt);
        for (NodoMatricula aux = ini; aux != null; aux = aux.sig) {
            Object[] fila = {aux.m.getId_matricula(),
                             nombreEstudiante(aux.m.getId_estudiante()),
                             anioNombre(aux.m.getId_anio()),
                             GradoCurso.nombreGrado(aux.m.getId_grado()),
                             salonCodigo(aux.m.getId_salon()),
                             aux.m.getFecha_matricula()};
            mt.addRow(fila);
        }
    }

    public void InsertarMatricula(Matricula m) {
        NodoMatricula nuevo = new NodoMatricula(m);
        if (ini == null) { ini = fin = nuevo; }
        else { nuevo.ant = fin; fin.sig = nuevo; }
        fin = nuevo;
        fin.sig = null;
    }

    public void EliminarMatricula(NodoMatricula actual) {
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

    public NodoMatricula BuscarPorId(int id) {
        for (NodoMatricula aux = ini; aux != null; aux = aux.sig)
            if (aux.m.getId_matricula() == id) return aux;
        return null;
    }

    public void MostrarPorAnio(JTable tabla, int idAnio) {
        String titulos[] = {"id", "Estudiante", "Grado", "Salon"};
        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        tabla.setModel(mt);
        for (NodoMatricula aux = ini; aux != null; aux = aux.sig)
            if (aux.m.getId_anio() == idAnio) {
                Object[] fila = {aux.m.getId_matricula(),
                                 nombreEstudiante(aux.m.getId_estudiante()),
                                 GradoCurso.nombreGrado(aux.m.getId_grado()),
                                 salonCodigo(aux.m.getId_salon())};
                mt.addRow(fila);
            }
    }

    public boolean existeMatricula(int idEstudiante, int idAnio) {
        for (NodoMatricula aux = ini; aux != null; aux = aux.sig)
            if (aux.m.getId_estudiante() == idEstudiante && aux.m.getId_anio() == idAnio)
                return true;
        return false;
    }
}
