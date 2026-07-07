package Controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Almacenamiento.CursoPersistencia;
import Modelo.*;
import Procesos.ProcesoCursos;
import Vista.Cursos;
import javax.swing.JOptionPane;

public class ControladorCursos implements ActionListener {
    ListaCurso Lista;
    NodoCurso actual;
    Curso c;
    Cursos vista;

    public ControladorCursos(Cursos fa) {
        vista = fa;
        Lista = new ListaCurso();
        vista.btnguardar.addActionListener(this);
        vista.btneditar.addActionListener(this);
        vista.btnbuscar.addActionListener(this);
        vista.btneliminar.addActionListener(this);
        vista.btnlimpiar.addActionListener(this);
        Lista = CursoPersistencia.RecuperarLista();
        Lista.MostrarCursos(vista.tbldatos);
        botoneslimpiar();
    }

    private void ActualizarVista() {
        Lista.MostrarCursos(vista.tbldatos);
        CursoPersistencia.GuardarLista(Lista);
        ProcesoCursos.LimpiarEntradas(vista);
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
            if (vista.txtcodigo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese un codigo");
                return;
            }
            c = ProcesoCursos.LeerDatos(vista, Lista);
            Lista.InsertarCursos(c);
            ActualizarVista();
            JOptionPane.showMessageDialog(null, "Curso registrado.....");
        }
        if (e.getSource() == vista.btneditar) {
            if (actual == null) {
                JOptionPane.showMessageDialog(null, "Primero busque un curso para editar");
                return;
            }
            actual.c.setCodigo(vista.txtcodigo.getText());
            actual.c.setNombre(vista.txtnombre.getText());
            actual.c.setHoras_semanales((Integer) vista.spinhoras.getValue());
            actual = null;
            ActualizarVista();
            botoneslimpiar();
            JOptionPane.showMessageDialog(null, "Curso actualizado.....");
        }
        if (e.getSource() == vista.btnbuscar) {
            String cb = JOptionPane.showInputDialog("Ingrese codigo del curso a buscar..");
            if (cb == null || cb.trim().isEmpty()) return;
            actual = Lista.BuscarPorCodigo(cb);
            if (actual == null) {
                JOptionPane.showMessageDialog(null, "Codigo " + cb + " no existe en la lista");
            } else {
                botonselecion();
                ProcesoCursos.MostrarCurso(actual.c, vista);
            }
        }
        if (e.getSource() == vista.btneliminar) {
            if (actual == null) {
                JOptionPane.showMessageDialog(null, "Primero busque un curso para eliminar");
                return;
            }
            int resp = JOptionPane.showConfirmDialog(
                null, "Desea eliminar el curso " + actual.c.getNombre() + "?", "Confirmar!!!", JOptionPane.OK_CANCEL_OPTION);
            if (resp == 0) {
                Lista.EliminarCursos(actual);
                actual = null;
                ActualizarVista();
                botoneslimpiar();
                JOptionPane.showMessageDialog(null, "Registro eliminado...");
            }
        }
        if (e.getSource() == vista.btnlimpiar) {
            ProcesoCursos.LimpiarEntradas(vista);
            actual = null;
            botoneslimpiar();
        }
    }
}
