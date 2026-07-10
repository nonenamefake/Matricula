package ModeloArrayList;

import Modelo.Matricula;
import Almacenamiento.EstudiantePersistencia;
import Almacenamiento.AnioPersistencia;
import Almacenamiento.SalonPersistencia;
import Modelo.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListaReportes {

    public static void mostrarOrdenado(JTable tabla, int idAnio) {
        Modelo.ListaMatricula lm = Almacenamiento.MatriculaPersistencia.RecuperarLista();
        ArrayList<Matricula> lista = new ArrayList<>();
        for (NodoMatricula aux = lm.ini; aux != null; aux = aux.sig)
            if (aux.m.getId_anio() == idAnio)
                lista.add(aux.m);

        Collections.sort(lista, new Comparator<Matricula>() {
            public int compare(Matricula a, Matricula b) {
                String nomA = resolverNombre(a.getId_estudiante());
                String nomB = resolverNombre(b.getId_estudiante());
                return nomA.compareToIgnoreCase(nomB);
            }
        });

        String titulos[] = {"id", "Estudiante", "Grado", "Salon"};
        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        tabla.setModel(mt);
        for (Matricula m : lista) {
            Object[] fila = {m.getId_matricula(),
                             resolverNombre(m.getId_estudiante()),
                             GradoCurso.nombreGrado(m.getId_grado()),
                             resolverSalon(m.getId_salon())};
            mt.addRow(fila);
        }
    }

    private static String resolverNombre(int idEstudiante) {
        ListaEstudiantes le = EstudiantePersistencia.RecuperarLista();
        for (NodoEstudiantes aux = le.ini; aux != null; aux = aux.sig)
            if (aux.es.getId() == idEstudiante)
                return aux.es.getNombres() + " " + aux.es.getApellido_pa() + " " + aux.es.getApellido_ma();
        return "Desconocido";
    }

    private static String resolverSalon(int idSalon) {
        ListaSalon ls = SalonPersistencia.RecuperarLista();
        for (NodoSalon aux = ls.ini; aux != null; aux = aux.sig)
            if (aux.s.getId_salon() == idSalon)
                return aux.s.getCodigo();
        return "Desconocido";
    }
}
