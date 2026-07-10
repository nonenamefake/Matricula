package Procesos;

import Modelo.ListaHorario;
import Modelo.NodoHorario;
import Modelo.ListaDocenteCurso;
import Modelo.NodoDocenteCurso;
import Modelo.ListaDocentes;
import Modelo.ListaGradoCurso;
import Modelo.NodoGradoCurso;
import Modelo.ListaCurso;
import Modelo.GradoCurso;
import Vista.Horario;
import javax.swing.JOptionPane;

public class ProcesoHorarios {

    public static class DocenteCursoItem {
        public int id_docente_curso;
        public String texto;
        public DocenteCursoItem(int id, String texto) {
            this.id_docente_curso = id;
            this.texto = texto;
        }
        @Override
        public String toString() {
            return texto;
        }
    }

    public static void LimpiarEntradas(Horario fa) {
        fa.cbxdocentecurso.setSelectedIndex(-1);
        fa.cbxdias.setSelectedIndex(-1);
        fa.ftxtinicio.setText("06:00");
        fa.ftxtfin.setText("07:00");
        fa.cbxdocentecurso.requestFocus();
    }

    public static Modelo.Horario LeerDatos(Horario fa, ListaHorario lh, int idSalon) {
        Modelo.Horario h = new Modelo.Horario();
        h.setId_salon(idSalon);
        if (fa.cbxdocentecurso.getSelectedItem() != null)
            h.setId_docente_curso(((DocenteCursoItem) fa.cbxdocentecurso.getSelectedItem()).id_docente_curso);
        if (fa.cbxdias.getSelectedItem() != null)
            h.setDia_semana(fa.cbxdias.getSelectedItem().toString());
        h.setHora_inicio(fa.ftxtinicio.getText());
        h.setHora_fin(fa.ftxtfin.getText());
        h.setId_horario(obtenerSiguienteId(lh));
        return h;
    }

    public static void CargarDocenteCursos(Horario fa, int idGrado) {
        fa.cbxdocentecurso.removeAllItems();
        ListaDocenteCurso ldc = Almacenamiento.DocenteCursoPersistencia.RecuperarLista();
        ListaDocentes ld = Almacenamiento.DocentePersistencia.RecuperarLista();
        ListaGradoCurso lg = Almacenamiento.GradoCursoPersistencia.RecuperarLista();
        ListaCurso lc = Almacenamiento.CursoPersistencia.RecuperarLista();
        for (NodoDocenteCurso aux = ldc.ini; aux != null; aux = aux.sig) {
            int gcGrado = 0;
            for (NodoGradoCurso g = lg.ini; g != null; g = g.sig)
                if (g.gc.getId_grado_curso() == aux.dc.getId_grado_curso()) {
                    gcGrado = g.gc.getId_grado();
                    break;
                }
            if (gcGrado != idGrado) continue;
            String docente = "", curso = "", grado = "";
            for (NodoGradoCurso g = lg.ini; g != null; g = g.sig)
                if (g.gc.getId_grado_curso() == aux.dc.getId_grado_curso()) {
                    grado = GradoCurso.nombreGrado(g.gc.getId_grado());
                    for (Modelo.NodoCurso c = lc.ini; c != null; c = c.sig)
                        if (c.c.getId_curso() == g.gc.getId_curso())
                            curso = c.c.getNombre();
                }
            for (Modelo.NodoDocentes d = ld.ini; d != null; d = d.sig)
                if (d.dc.getId_docente() == aux.dc.getId_docente())
                    docente = d.dc.getNombres() + " " + d.dc.getApellido_pa();
            String texto = docente + " - " + curso + " - " + grado;
            fa.cbxdocentecurso.addItem(new DocenteCursoItem(aux.dc.getId_docente_curso(), texto));
        }
    }

    public static void MostrarHorario(Modelo.Horario h, Horario fa) {
        for (int i = 0; i < fa.cbxdocentecurso.getItemCount(); i++) {
            DocenteCursoItem item = (DocenteCursoItem) fa.cbxdocentecurso.getItemAt(i);
            if (item.id_docente_curso == h.getId_docente_curso()) {
                fa.cbxdocentecurso.setSelectedIndex(i);
                break;
            }
        }
        for (int i = 0; i < fa.cbxdias.getItemCount(); i++)
            if (fa.cbxdias.getItemAt(i).equals(h.getDia_semana()))
                fa.cbxdias.setSelectedIndex(i);
        fa.ftxtinicio.setText(h.getHora_inicio());
        fa.ftxtfin.setText(h.getHora_fin());
    }

    public static int obtenerSiguienteId(ListaHorario lh) {
        if (lh.fin == null) return 1;
        int mayor = 0;
        for (NodoHorario aux = lh.ini; aux != null; aux = aux.sig)
            if (aux.h.getId_horario() > mayor) mayor = aux.h.getId_horario();
        return mayor + 1;
    }
}
