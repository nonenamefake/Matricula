package Procesos;

import Modelo.Curso;
import Modelo.ListaCurso;
import Modelo.GradoCurso;
import Modelo.ListaGradoCurso;
import Modelo.NodoGradoCurso;
import Vista.GradoCursoForm;
import javax.swing.JOptionPane;

public class ProcesoGradoCurso {
    public static void LimpiarEntradas(GradoCursoForm fa) {
        fa.cbxgrado.setSelectedIndex(-1);
        fa.cbxcurso.setSelectedIndex(-1);
        fa.cbxgrado.requestFocus();
    }

    public static GradoCurso LeerDatos(GradoCursoForm fa, ListaGradoCurso lg) {
        GradoCurso gc = new GradoCurso();
        if (fa.cbxgrado.getSelectedItem() != null)
            gc.setId_grado(Integer.parseInt(fa.cbxgrado.getSelectedItem().toString()) );
        int idCurso = 0;
        if (fa.cbxcurso.getSelectedItem() != null) {
            String nombreCurso = fa.cbxcurso.getSelectedItem().toString();
            idCurso = obtenerIdCursoPorNombre(nombreCurso);
        }
        gc.setId_curso(idCurso);
        gc.setId_grado_curso(obtenerSiguienteId(lg));
        return gc;
    }

    public static void CargarCursos(GradoCursoForm fa, ListaCurso lc) {
        fa.cbxcurso.removeAllItems();
        for (Modelo.NodoCurso aux = lc.ini; aux != null; aux = aux.sig)
            fa.cbxcurso.addItem(aux.c.getNombre());
    }

    private static int obtenerIdCursoPorNombre(String nombre) {
        ListaCurso lc = Almacenamiento.CursoPersistencia.RecuperarLista();
        for (Modelo.NodoCurso aux = lc.ini; aux != null; aux = aux.sig)
            if (aux.c.getNombre().equals(nombre))
                return aux.c.getId_curso();
        return 0;
    }

    public static String obtenerNombreCurso(int idCurso) {
        ListaCurso lc = Almacenamiento.CursoPersistencia.RecuperarLista();
        for (Modelo.NodoCurso aux = lc.ini; aux != null; aux = aux.sig)
            if (aux.c.getId_curso() == idCurso)
                return aux.c.getNombre();
        return "Desconocido";
    }

    public static int obtenerSiguienteId(ListaGradoCurso lg) {
        if (lg.fin == null) return 1;
        int mayor = 0;
        for (NodoGradoCurso aux = lg.ini; aux != null; aux = aux.sig)
            if (aux.gc.getId_grado_curso() > mayor) mayor = aux.gc.getId_grado_curso();
        return mayor + 1;
    }
}
