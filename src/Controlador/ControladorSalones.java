package Controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Almacenamiento.SalonPersistencia;
import Modelo.*;
import Procesos.ProcesoSalones;
import Vista.Salones;
import javax.swing.JOptionPane;

public class ControladorSalones implements ActionListener {
    ListaSalon Lista;
    NodoSalon actual;
    Salon s;
    Salones vista;

    public ControladorSalones(Salones fa) {
        vista = fa;
        Lista = new ListaSalon();
        vista.btnguardar.addActionListener(this);
        vista.btneditar.addActionListener(this);
        vista.btnbuscar.addActionListener(this);
        vista.btneliminar.addActionListener(this);
        vista.btnlimpiar.addActionListener(this);
        Lista = SalonPersistencia.RecuperarLista();
        Lista.MostrarSalones(vista.tbldato);
    }

    private void ActualizarVista() {
        Lista.MostrarSalones(vista.tbldato);
        SalonPersistencia.GuardarLista(Lista);
        ProcesoSalones.LimpiarEntradas(vista);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnlimpiar) {
            ProcesoSalones.LimpiarEntradas(vista);
            actual = null;
        }
        if (e.getSource() == vista.btnguardar) {
            if (vista.txtcodigo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese un codigo de salon");
                return;
            }
            s = ProcesoSalones.LeerDatos(vista, Lista);
            Lista.InsertarSalon(s);
            ActualizarVista();
            JOptionPane.showMessageDialog(null, "Salon registrado.....");
        }
        if (e.getSource() == vista.btneditar) {
            if (actual == null) {
                JOptionPane.showMessageDialog(null, "Primero busque un salon para editar");
                return;
            }
            actual.s.setCodigo(vista.txtcodigo.getText());
            actual.s.setNombre(vista.txtnombre.getText());
            try {
                actual.s.setId_grado(Integer.parseInt(vista.cbxgrado.getSelectedItem().toString()));
            } catch (NumberFormatException ex) {}
            actual.s.setCapacidad((Integer) vista.spncapacidad.getValue());
            actual = null;
            ActualizarVista();
            JOptionPane.showMessageDialog(null, "Salon actualizado.....");
        }
        if (e.getSource() == vista.btnbuscar) {
            String cb = JOptionPane.showInputDialog("Ingrese codigo del salon a buscar..");
            if (cb == null || cb.trim().isEmpty()) return;
            actual = Lista.BuscarPorCodigo(cb);
            if (actual == null)
                JOptionPane.showMessageDialog(null, "Codigo " + cb + " no existe");
            else
                ProcesoSalones.MostrarSalon(actual.s, vista);
        }
        if (e.getSource() == vista.btneliminar) {
            if (actual == null) {
                JOptionPane.showMessageDialog(null, "Primero busque un salon para eliminar");
                return;
            }
            int resp = JOptionPane.showConfirmDialog(null,
                "Desea eliminar " + actual.s.getNombre() + "?",
                "Confirmar!!!", JOptionPane.OK_CANCEL_OPTION);
            if (resp == 0) {
                Lista.EliminarSalon(actual);
                actual = null;
                ActualizarVista();
                JOptionPane.showMessageDialog(null, "Registro eliminado...");
            }
        }
    }
}
