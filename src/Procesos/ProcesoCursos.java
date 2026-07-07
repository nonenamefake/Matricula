package Procesos;

import Modelo.Curso;
import Modelo.ListaCurso;
import Modelo.NodoCurso;
import Vista.Cursos;

public class ProcesoCursos {
    public static void LimpiarEntradas(Cursos fa) {
        fa.txtcodigo.setText("");
        fa.txtnombre.setText("");
        fa.spinhoras.setValue(0);
        fa.txtcodigo.requestFocus();
    }

    public static Curso LeerDatos(Cursos fa, ListaCurso lc) {
        Curso c = new Curso();
        c.setCodigo(fa.txtcodigo.getText());
        c.setNombre(fa.txtnombre.getText());
        c.setHoras_semanales((Integer) fa.spinhoras.getValue());
        if(c.getId_curso() == 0){
            c.setId_curso(obtenerSiguienteId(lc));
        }
        return c;
    }

    public static void MostrarCurso(Curso c, Cursos fa) {
        fa.txtcodigo.setText(c.getCodigo());
        fa.txtnombre.setText(c.getNombre());
        fa.spinhoras.setValue(c.getHoras_semanales());
        fa.txtcodigo.requestFocus();
    }

    public static int obtenerSiguienteId(ListaCurso lc) {
        if (lc.fin == null) {
            return 1;
        }
        int idMayor = 0;
        NodoCurso actual = lc.ini;
        while (actual != null) {
            if (actual.c.getId_curso() > idMayor)
                idMayor = actual.c.getId_curso();
            actual = actual.sig;
        }
        return idMayor + 1;
    }
}
