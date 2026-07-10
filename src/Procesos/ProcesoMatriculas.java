package Procesos;

import Modelo.ListaMatricula;
import Modelo.NodoMatricula;
import Modelo.ListaAnioAcademico;
import Modelo.NodoAnioAcademico;
import Modelo.ListaSalon;
import Modelo.NodoSalon;
import Vista.Matricula;
import javax.swing.JOptionPane;

public class ProcesoMatriculas {

    public static class AnioItem {
        public int id_anio;
        public String texto;
        public AnioItem(int id, String texto) {
            this.id_anio = id;
            this.texto = texto;
        }
        @Override
        public String toString() { return texto; }
    }

    public static class GradoItem {
        public int id_grado;
        public String texto;
        public GradoItem(int id, String texto) {
            this.id_grado = id;
            this.texto = texto;
        }
        @Override
        public String toString() { return texto; }
    }

    public static class SalonItem {
        public int id_salon;
        public String texto;
        public SalonItem(int id, String texto) {
            this.id_salon = id;
            this.texto = texto;
        }
        @Override
        public String toString() { return texto; }
    }

    public static void LimpiarEntradas(Matricula fa) {
        fa.cbxaños.setSelectedIndex(-1);
        fa.cbxgrado.setSelectedIndex(-1);
        fa.cbxsalon.removeAllItems();
        fa.cbxaños.requestFocus();
    }

    public static Modelo.Matricula LeerDatos(Matricula fa, ListaMatricula lm, int idEstudiante) {
        Modelo.Matricula m = new Modelo.Matricula();
        m.setId_estudiante(idEstudiante);
        if (fa.cbxaños.getSelectedItem() != null)
            m.setId_anio(((AnioItem) fa.cbxaños.getSelectedItem()).id_anio);
        if (fa.cbxgrado.getSelectedItem() != null)
            m.setId_grado(((GradoItem) fa.cbxgrado.getSelectedItem()).id_grado);
        if (fa.cbxsalon.getSelectedItem() != null)
            m.setId_salon(((SalonItem) fa.cbxsalon.getSelectedItem()).id_salon);
        m.setFecha_matricula(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        m.setId_matricula(obtenerSiguienteId(lm));
        return m;
    }

    public static void CargarAnios(Matricula fa) {
        fa.cbxaños.removeAllItems();
        ListaAnioAcademico la = Almacenamiento.AnioPersistencia.RecuperarLista();
        for (NodoAnioAcademico aux = la.ini; aux != null; aux = aux.sig)
            fa.cbxaños.addItem(new AnioItem(aux.aa.getId_anio(), aux.aa.getAnio()));
    }

    public static void CargarGrados(Matricula fa) {
        fa.cbxgrado.removeAllItems();
        for (int i = 1; i <= 6; i++)
            fa.cbxgrado.addItem(new GradoItem(i, Modelo.GradoCurso.nombreGrado(i)));
    }

    public static void CargarSalonesPorGrado(Matricula fa, int idGrado) {
        fa.cbxsalon.removeAllItems();
        ListaSalon ls = Almacenamiento.SalonPersistencia.RecuperarLista();
        for (NodoSalon aux = ls.ini; aux != null; aux = aux.sig)
            if (aux.s.getId_grado() == idGrado)
                fa.cbxsalon.addItem(new SalonItem(aux.s.getId_salon(), aux.s.getCodigo()));
    }

    public static int obtenerSiguienteId(ListaMatricula lm) {
        if (lm.fin == null) return 1;
        int mayor = 0;
        for (NodoMatricula aux = lm.ini; aux != null; aux = aux.sig)
            if (aux.m.getId_matricula() > mayor) mayor = aux.m.getId_matricula();
        return mayor + 1;
    }
}
