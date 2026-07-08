package Controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import Almacenamiento.DocenteCursoPersistencia;
import Almacenamiento.AnioPersistencia;
import Modelo.*;
import Procesos.ProcesoDocenteCurso;
import Vista.DocenteCursoform;
import javax.swing.JOptionPane;

public class ControladorDocenteCurso implements ActionListener, ItemListener {
    ListaDocenteCurso Lista;
    NodoDocenteCurso actual;
    DocenteCurso dc;
    DocenteCursoform vista;
    int idDocente;

    public ControladorDocenteCurso(DocenteCursoform fa, int idDocente) {
        this.vista = fa;
        this.idDocente = idDocente;
        Lista = new ListaDocenteCurso();
        vista.btnguardar.addActionListener(this);
        vista.btneliminar.addActionListener(this);
        vista.btnlimpiar.addActionListener(this);
        vista.btnbuscar.addActionListener(this);
        vista.cbxgrado.addItemListener(this);
        Lista = DocenteCursoPersistencia.RecuperarLista();
        ProcesoDocenteCurso.CargarAnios(vista, AnioPersistencia.RecuperarLista());
        vista.cbxcurso.setEnabled(false);
        Lista.MostrarPorDocente(vista.tbldatos, idDocente);
    }

    private void ActualizarVista() {
        Lista.MostrarPorDocente(vista.tbldatos, idDocente);
        DocenteCursoPersistencia.GuardarLista(Lista);
        ProcesoDocenteCurso.LimpiarEntradas(vista);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == vista.cbxgrado && e.getStateChange() == ItemEvent.SELECTED) {
            int idGrado = Integer.parseInt(vista.cbxgrado.getSelectedItem().toString());
            ProcesoDocenteCurso.CargarCursosPorGrado(vista, idGrado);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnguardar) {
            if (vista.cbxgrado.getSelectedItem() == null ||
                vista.cbxcurso.getSelectedItem() == null ||
                vista.cbxa\u00f1os.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione grado, curso y a\u00f1o");
                return;
            }
            dc = ProcesoDocenteCurso.LeerDatos(vista, Lista, idDocente);
            Lista.InsertarDocenteCurso(dc);
            ActualizarVista();
            JOptionPane.showMessageDialog(null, "Curso asignado al docente.....");
        }
        if (e.getSource() == vista.btneliminar) {
            if (actual == null) {
                JOptionPane.showMessageDialog(null, "Primero busque una asignacion para eliminar");
                return;
            }
            int resp = JOptionPane.showConfirmDialog(null,
                "Desea eliminar la asignacion?", "Confirmar!!!", JOptionPane.OK_CANCEL_OPTION);
            if (resp == 0) {
                Lista.EliminarDocenteCurso(actual);
                actual = null;
                ActualizarVista();
                JOptionPane.showMessageDialog(null, "Asignacion eliminada...");
            }
        }
        if (e.getSource() == vista.btnlimpiar) {
            ProcesoDocenteCurso.LimpiarEntradas(vista);
            actual = null;
            Lista.MostrarPorDocente(vista.tbldatos, idDocente);
        }
        if (e.getSource() == vista.btnbuscar) {
            String input = JOptionPane.showInputDialog("Ingrese el ID de la asignacion a eliminar:");
            if (input == null || input.trim().isEmpty()) return;
            try {
                actual = Lista.BuscarPorId(Integer.parseInt(input.trim()));
                if (actual == null) {
                    JOptionPane.showMessageDialog(null, "Asignacion no encontrada");
                } else {
                    JOptionPane.showMessageDialog(null, "Asignacion encontrada. Use Eliminar para quitarla.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero valido");
            }
        }
    }
}
