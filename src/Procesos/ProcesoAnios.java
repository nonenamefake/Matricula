package Procesos;

import Modelo.AnioAcademico;
import Modelo.ListaAnioAcademico;
import Modelo.NodoAnioAcademico;
import Vista.A\u00f1osform;
import java.util.Date;

public class ProcesoAnios {
    public static void LimpiarEntradas(A\u00f1osform fa) {
        fa.txta\u00f1o.setText("");
        fa.cbxestado.setSelectedIndex(-1);
        fa.dateinicio.setDate(new Date());
        fa.datefin.setDate(new Date());
        fa.txta\u00f1o.requestFocus();
    }

    public static AnioAcademico LeerDatos(A\u00f1osform fa, ListaAnioAcademico la) {
        AnioAcademico aa = new AnioAcademico();
        aa.setAnio(fa.txta\u00f1o.getText());
        aa.setFecha_inicio(fa.dateinicio.getDate());
        aa.setFecha_fin(fa.datefin.getDate());
        if (fa.cbxestado.getSelectedItem() != null)
            aa.setActivo(fa.cbxestado.getSelectedItem().toString());
        if (aa.getId_anio() == 0)
            aa.setId_anio(obtenerSiguienteId(la));
        return aa;
    }

    public static void MostrarAnio(AnioAcademico aa, A\u00f1osform fa) {
        fa.txta\u00f1o.setText(aa.getAnio());
        fa.dateinicio.setDate(aa.getFecha_inicio());
        fa.datefin.setDate(aa.getFecha_fin());
        if (aa.getActivo().equals("activo")) fa.cbxestado.setSelectedIndex(0);
        else if (aa.getActivo().equals("no activo")) fa.cbxestado.setSelectedIndex(1);
        fa.txta\u00f1o.requestFocus();
    }

    public static int obtenerSiguienteId(ListaAnioAcademico la) {
        if (la.fin == null) return 1;
        int mayor = 0;
        for (NodoAnioAcademico aux = la.ini; aux != null; aux = aux.sig)
            if (aux.aa.getId_anio() > mayor) mayor = aux.aa.getId_anio();
        return mayor + 1;
    }
}
