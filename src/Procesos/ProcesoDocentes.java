package Procesos;

import Modelo.Docentes;
import Modelo.ListaDocentes;
import Modelo.NodoDocentes;
import Vista.Docente;
import javax.swing.JOptionPane;

public class ProcesoDocentes {
    public static void LimpiarEntradas(Docente fa) {
        fa.txtdni.setText("");
        fa.txtnombre.setText("");
        fa.txtapellidopa.setText("");
        fa.txtapellidoma.setText("");
        fa.txtcorreo.setText("");
        fa.txttelefono.setText("");
        fa.cbxespecialidad.setSelectedIndex(-1);
        fa.txtdni.requestFocus();
    }

    public static Docentes LeerDatos(Docente fa, ListaDocentes ld) {
        Docentes d = new Docentes();
        d.setDni(fa.txtdni.getText());
        d.setNombres(fa.txtnombre.getText());
        d.setApellido_pa(fa.txtapellidopa.getText());
        d.setApellido_ma(fa.txtapellidoma.getText());
        if (fa.cbxespecialidad.getSelectedItem() != null)
            d.setEspecialidad(fa.cbxespecialidad.getSelectedItem().toString());
        d.setTelefono(fa.txttelefono.getText());
        d.setCorreo(fa.txtcorreo.getText());
        d.setEstado("Activo");
        if(d.getId_docente()==0){
            d.setId_docente(obtenerSiguienteId(ld));
        }
        return d;
    }

    public static void MostrarDocentes(Docentes d, Docente fa) {
        fa.txtdni.setText(d.getDni());
        fa.txtnombre.setText(d.getNombres());
        fa.txtapellidopa.setText(d.getApellido_pa());
        fa.txtapellidoma.setText(d.getApellido_ma());
        fa.txttelefono.setText(d.getTelefono());
        fa.txtcorreo.setText(d.getCorreo());
        for (int i = 0; i < fa.cbxespecialidad.getItemCount(); i++) {
            if (fa.cbxespecialidad.getItemAt(i).equals(d.getEspecialidad())) {
                fa.cbxespecialidad.setSelectedIndex(i);
                break;
            }
        }
        fa.txtdni.requestFocus();
    }

    public static int obtenerSiguienteId(ListaDocentes ld) {
        if (ld.fin == null) {
            return 1;
        }
        int idMayor = 0;
        NodoDocentes actual = ld.ini;
        while (actual != null) {
            if (actual.dc.getId_docente() > idMayor)
                idMayor = actual.dc.getId_docente();
            actual = actual.sig;
        }
        return idMayor + 1;
    }
}
