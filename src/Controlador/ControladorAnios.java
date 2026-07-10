package Controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Almacenamiento.AnioPersistencia;
import Modelo.*;
import Procesos.ProcesoAnios;
import Vista.Añosform;
import javax.swing.JOptionPane;

public class ControladorAnios implements ActionListener {
    ListaAnioAcademico Lista;
    NodoAnioAcademico actual;
    AnioAcademico aa;
    Añosform vista;

    public ControladorAnios(Añosform fa) {
        vista = fa;
        Lista = new ListaAnioAcademico();
        vista.btnregistrar.addActionListener(this);
        vista.btneditar.addActionListener(this);
        vista.btnbuscar.addActionListener(this);
        vista.btneliminar.addActionListener(this);
        vista.btnlimpiar.addActionListener(this);
        Lista = AnioPersistencia.RecuperarLista();
        Lista.MostrarAnios(vista.tbldatos);
        botoneslimpiar();
    }

    private void ActualizarVista() {
        Lista.MostrarAnios(vista.tbldatos);
        AnioPersistencia.GuardarLista(Lista);
        ProcesoAnios.LimpiarEntradas(vista);
    }

    private void botoneslimpiar() {
        vista.btnregistrar.setEnabled(true);
        vista.btneditar.setEnabled(false);
        vista.btnbuscar.setEnabled(true);
        vista.btneliminar.setEnabled(false);
        vista.btnlimpiar.setEnabled(true);
    }

    private void botonselecion() {
        vista.btnregistrar.setEnabled(false);
        vista.btneditar.setEnabled(true);
        vista.btnbuscar.setEnabled(true);
        vista.btneliminar.setEnabled(true);
        vista.btnlimpiar.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnregistrar) {
            aa = ProcesoAnios.LeerDatos(vista, Lista);
            Lista.InsertarAnio(aa);
            ActualizarVista();
            JOptionPane.showMessageDialog(null, "A\u00f1o acad\u00e9mico registrado.....");
        }
        if (e.getSource() == vista.btneditar) {
            if (actual == null) {
                JOptionPane.showMessageDialog(null, "Primero busque un a\u00f1o para editar");
                return;
            }
            actual.aa.setAnio(vista.txta\u00f1o.getText());
            actual.aa.setFecha_inicio(vista.dateinicio.getDate());
            actual.aa.setFecha_fin(vista.datefin.getDate());
            if (vista.cbxestado.getSelectedItem() != null)
                actual.aa.setActivo(vista.cbxestado.getSelectedItem().toString());
            actual = null;
            ActualizarVista();
            botoneslimpiar();
            JOptionPane.showMessageDialog(null, "A\u00f1o actualizado.....");
        }
        if (e.getSource() == vista.btnbuscar) {
            String cb = JOptionPane.showInputDialog("Ingrese el a\u00f1o a buscar (ej: 2026):");
            if (cb == null || cb.trim().isEmpty()) return;
            actual = Lista.BuscarPorAnio(cb.trim());
            if (actual == null) {
                JOptionPane.showMessageDialog(null, "A\u00f1o " + cb + " no existe en la lista");
            } else {
                botonselecion();
                ProcesoAnios.MostrarAnio(actual.aa, vista);
            }
        }
        if (e.getSource() == vista.btneliminar) {
            int resp = JOptionPane.showConfirmDialog(null,
                "Desea eliminar el a\u00f1o " + actual.aa.getAnio() + "?", "Confirmar!!!", JOptionPane.OK_CANCEL_OPTION);
            if (resp == 0) {
                botoneslimpiar();
                Lista.EliminarAnio(actual);
                actual = null;
                ActualizarVista();
                JOptionPane.showMessageDialog(null, "Registro eliminado...");
            }
        }
        if (e.getSource() == vista.btnlimpiar) {
            ProcesoAnios.LimpiarEntradas(vista);
            actual = null;
            botoneslimpiar();
        }
    }
}
