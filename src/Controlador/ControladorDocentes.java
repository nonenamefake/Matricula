package Controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Almacenamiento.DocentePersistencia;
import Modelo.*;
import Procesos.ProcesoDocentes;
import Vista.Docente;
import javax.swing.JOptionPane;

public class ControladorDocentes implements ActionListener {
    ListaDocentes Lista;
    NodoDocentes actual;
    Docentes dc;
    Docente vista;

    public ControladorDocentes(Docente fa) {
        vista = fa;
        Lista = new ListaDocentes();
        vista.btnguardar.addActionListener(this);
        vista.btneditar.addActionListener(this);
        vista.btnbuscar.addActionListener(this);
        vista.btneliminar.addActionListener(this);
        vista.btnlimpiar.addActionListener(this);
        Lista = DocentePersistencia.RecuperarLista();
        Lista.MostrarDocentes(vista.tbldatos);
        botoneslimpiar();
    }

    private void ActualizarVista() {
        Lista.MostrarDocentes(vista.tbldatos);
        DocentePersistencia.GuardarLista(Lista);
        ProcesoDocentes.LimpiarEntradas(vista);
    }
    private void botoneslimpiar(){
        vista.btnguardar.setEnabled(true);
        vista.btneditar.setEnabled(false);
        vista.btnbuscar.setEnabled(true);
        vista.btneliminar.setEnabled(false);
        vista.btnlimpiar.setEnabled(true);
    }
    private void botonselecion(){
        vista.btnguardar.setEnabled(false);
        vista.btneditar.setEnabled(true);
        vista.btnbuscar.setEnabled(true);
        vista.btneliminar.setEnabled(true);
        vista.btnlimpiar.setEnabled(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnguardar) {
            if (vista.txtdni.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese un DNI");
                return;
            }
            dc = ProcesoDocentes.LeerDatos(vista, Lista);
            Lista.InsertarDocentes(dc);
            ActualizarVista();
            JOptionPane.showMessageDialog(null, "Docente registrado.....");
        }
        if (e.getSource() == vista.btneditar) {
            if (actual == null) {
                JOptionPane.showMessageDialog(null, "Primero busque un docente para editar");
                return;
            }
            actual.dc.setDni(vista.txtdni.getText());
            actual.dc.setNombres(vista.txtnombre.getText());
            actual.dc.setApellido_pa(vista.txtapellidopa.getText());
            actual.dc.setApellido_ma(vista.txtapellidoma.getText());
            if (vista.cbxespecialidad.getSelectedItem() != null)
                actual.dc.setEspecialidad(vista.cbxespecialidad.getSelectedItem().toString());
            actual.dc.setTelefono(vista.txttelefono.getText());
            actual.dc.setCorreo(vista.txtcorreo.getText());
            actual = null;
            botoneslimpiar();
            ActualizarVista();
            JOptionPane.showMessageDialog(null, "Docente actualizado.....");
        }
        if (e.getSource() == vista.btnbuscar) {
            String cb = JOptionPane.showInputDialog("Ingrese DNI a buscar..");
            if (cb == null || cb.trim().isEmpty()) return;
            actual = Lista.BuscarPordni(cb);
            if (actual == null) {
                JOptionPane.showMessageDialog(null, "DNI " + cb + " no existe en la lista");
            } else {
                botonselecion();
                ProcesoDocentes.MostrarDocentes(actual.dc, vista);
            }
        }
        if (e.getSource() == vista.btneliminar) {
            if (actual == null) {
                JOptionPane.showMessageDialog(null, "Primero busque un docente para eliminar");
                return;
            }
            int resp = JOptionPane.showConfirmDialog(
                null, "Desea eliminar a " + actual.dc.getNombres() + "?", "Confirmar!!!", JOptionPane.OK_CANCEL_OPTION);
            if (resp == 0) {
                Lista.EliminarDocentes(actual);
                actual = null;
                ActualizarVista();
                botoneslimpiar();
                JOptionPane.showMessageDialog(null, "Registro eliminado...");
            }
        }
        if (e.getSource() == vista.btnlimpiar) {
            ProcesoDocentes.LimpiarEntradas(vista);
            botoneslimpiar();
            actual = null;
        }
    }
}
