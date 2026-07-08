package Procesos;

import Modelo.DocenteCurso;
import Modelo.ListaDocenteCurso;
import Modelo.NodoDocenteCurso;
import Modelo.ListaGradoCurso;
import Modelo.ListaAnioAcademico;
import Modelo.NodoGradoCurso;
import Modelo.ListaCurso;
import Vista.DocenteCursoform;
import javax.swing.JOptionPane;

public class ProcesoDocenteCurso {

    public static class GradoCursoItem {
        public int id_grado_curso;
        public String nombreCurso;
        public GradoCursoItem(int id, String nombre) {
            this.id_grado_curso = id;
            this.nombreCurso = nombre;
        }
        @Override
        public String toString() {
            return nombreCurso;
        }
    }

    public static void LimpiarEntradas(DocenteCursoform fa) {
        fa.cbxgrado.setSelectedIndex(-1);
        fa.cbxcurso.removeAllItems();
        fa.cbxcurso.setEnabled(false);
        fa.cbxa\u00f1os.setSelectedIndex(-1);
        fa.cbxgrado.requestFocus();
    }

    public static DocenteCurso LeerDatos(DocenteCursoform fa, ListaDocenteCurso ldc, int idDocente) {
        DocenteCurso dc = new DocenteCurso();
        dc.setId_docente(idDocente);

        if (fa.cbxcurso.getSelectedItem() != null)
            dc.setId_grado_curso(((GradoCursoItem) fa.cbxcurso.getSelectedItem()).id_grado_curso);

        if (fa.cbxa\u00f1os.getSelectedItem() != null)
            dc.setId_anio(obtenerIdAnioPorNombre(fa.cbxa\u00f1os.getSelectedItem().toString()));

        dc.setId_docente_curso(obtenerSiguienteId(ldc));
        return dc;
    }

    public static void CargarCursosPorGrado(DocenteCursoform fa, int idGrado) {
        fa.cbxcurso.removeAllItems();
        fa.cbxcurso.setEnabled(true);
        ListaGradoCurso lg = Almacenamiento.GradoCursoPersistencia.RecuperarLista();
        ListaCurso lc = Almacenamiento.CursoPersistencia.RecuperarLista();
        for (NodoGradoCurso aux = lg.ini; aux != null; aux = aux.sig) {
            if (aux.gc.getId_grado() == idGrado) {
                String nombreCurso = "";
                for (Modelo.NodoCurso c = lc.ini; c != null; c = c.sig)
                    if (c.c.getId_curso() == aux.gc.getId_curso())
                        nombreCurso = c.c.getNombre();
                fa.cbxcurso.addItem(new GradoCursoItem(aux.gc.getId_grado_curso(), nombreCurso));
            }
        }
    }

    public static void CargarAnios(DocenteCursoform fa, ListaAnioAcademico la) {
        fa.cbxa\u00f1os.removeAllItems();
        for (Modelo.NodoAnioAcademico aux = la.ini; aux != null; aux = aux.sig)
            fa.cbxa\u00f1os.addItem(aux.aa.getAnio());
    }

    private static int obtenerIdAnioPorNombre(String anio) {
        ListaAnioAcademico la = Almacenamiento.AnioPersistencia.RecuperarLista();
        for (Modelo.NodoAnioAcademico aux = la.ini; aux != null; aux = aux.sig)
            if (aux.aa.getAnio().equals(anio))
                return aux.aa.getId_anio();
        return 0;
    }

    public static int obtenerSiguienteId(ListaDocenteCurso ldc) {
        if (ldc.fin == null) return 1;
        int mayor = 0;
        for (NodoDocenteCurso aux = ldc.ini; aux != null; aux = aux.sig)
            if (aux.dc.getId_docente_curso() > mayor) mayor = aux.dc.getId_docente_curso();
        return mayor + 1;
    }
}
