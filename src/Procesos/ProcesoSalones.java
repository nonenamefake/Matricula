package Procesos;

import Modelo.Salon;
import Modelo.ListaSalon;
import Modelo.NodoSalon;
import Vista.Salones;
import javax.swing.JOptionPane;

public class ProcesoSalones {
    public static void LimpiarEntradas(Salones fa) {
        fa.txtcodigo.setText("");
        fa.txtnombre.setText("");
        fa.spncapacidad.setValue(0);
        fa.cbxgrado.setSelectedItem(-1);
        fa.txtnombre.requestFocus();
    }

    public static Salon LeerDatos(Salones fa, ListaSalon ls) {
        Salon s = new Salon();
        s.setCodigo(fa.txtcodigo.getText());
        s.setNombre(fa.txtnombre.getText());
        try {
            s.setId_grado(Integer.parseInt(fa.cbxgrado.getSelectedItem().toString()));
        } catch (NumberFormatException e) {
            s.setId_grado(1);
        }
        s.setCapacidad((Integer) fa.spncapacidad.getValue());
        s.setId_salon(obtenerSiguienteId(ls));
        return s;
    }

    public static void MostrarSalon(Salon s, Salones fa) {
        fa.txtcodigo.setText(s.getCodigo());
        fa.txtnombre.setText(s.getNombre());
        fa.cbxgrado.setSelectedItem(s.getId_grado());
        fa.spncapacidad.setValue(s.getCapacidad());
        fa.txtcodigo.requestFocus();
    }

    public static int obtenerSiguienteId(ListaSalon ls) {
        if (ls.fin == null) return 1;
        int mayor = 0;
        for (NodoSalon aux = ls.ini; aux != null; aux = aux.sig)
            if (aux.s.getId_salon() > mayor) mayor = aux.s.getId_salon();
        return mayor + 1;
    }
}
