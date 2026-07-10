package Controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Almacenamiento.HorarioPersistencia;
import Modelo.ListaHorario;
import Modelo.NodoHorario;
import Procesos.ProcesoHorarios;
import Vista.Horario;
import javax.swing.JOptionPane;

public class ControladorHorarios implements ActionListener {
    ListaHorario Lista;
    NodoHorario actual;
    Modelo.Horario h;
    Horario vista;
    int idSalon;
    int idGrado;

    public ControladorHorarios(Horario fa, int idSalon, int idGrado) {
        this.vista = fa;
        this.idSalon = idSalon;
        this.idGrado = idGrado;
        Lista = new ListaHorario();
        vista.jButton3.addActionListener(this);
        vista.jButton4.addActionListener(this);
        vista.jButton5.addActionListener(this);
        vista.jButton6.addActionListener(this);
        vista.jButton1.addActionListener(this);
        Lista = HorarioPersistencia.RecuperarLista();
        ProcesoHorarios.CargarDocenteCursos(vista, idGrado);
        fa.cbxdias.setModel(new javax.swing.DefaultComboBoxModel<>(
            new String[]{"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"}));
        Lista.MostrarPorSalon(vista.tbldatos, idSalon);
    }

    private void ActualizarVista() {
        Lista.MostrarPorSalon(vista.tbldatos, idSalon);
        HorarioPersistencia.GuardarLista(Lista);
        ProcesoHorarios.LimpiarEntradas(vista);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jButton3) {
            if (vista.cbxdocentecurso.getSelectedItem() == null ||
                vista.cbxdias.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione docente y dia");
                return;
            }
            h = ProcesoHorarios.LeerDatos(vista, Lista, idSalon);
            Lista.InsertarHorario(h);
            ActualizarVista();
            JOptionPane.showMessageDialog(null, "Horario registrado.....");
        }
        if (e.getSource() == vista.jButton4) {
            if (actual == null) {
                JOptionPane.showMessageDialog(null, "Primero busque un horario para editar");
                return;
            }
            if (vista.cbxdocentecurso.getSelectedItem() != null)
                actual.h.setId_docente_curso(
                    ((ProcesoHorarios.DocenteCursoItem) vista.cbxdocentecurso.getSelectedItem()).id_docente_curso);
            if (vista.cbxdias.getSelectedItem() != null)
                actual.h.setDia_semana(vista.cbxdias.getSelectedItem().toString());
            actual.h.setHora_inicio(vista.ftxtinicio.getText());
            actual.h.setHora_fin(vista.ftxtfin.getText());
            actual = null;
            ActualizarVista();
            JOptionPane.showMessageDialog(null, "Horario actualizado.....");
        }
        if (e.getSource() == vista.jButton5) {
            if (actual == null) {
                JOptionPane.showMessageDialog(null, "Primero busque un horario para eliminar");
                return;
            }
            int resp = JOptionPane.showConfirmDialog(null,
                "Desea eliminar el horario?", "Confirmar!!!", JOptionPane.OK_CANCEL_OPTION);
            if (resp == 0) {
                Lista.EliminarHorario(actual);
                actual = null;
                ActualizarVista();
                JOptionPane.showMessageDialog(null, "Horario eliminado...");
            }
        }
        if (e.getSource() == vista.jButton6) {
            ProcesoHorarios.LimpiarEntradas(vista);
            actual = null;
            Lista.MostrarPorSalon(vista.tbldatos, idSalon);
        }
        if (e.getSource() == vista.jButton1) {
            String input = JOptionPane.showInputDialog("Ingrese el ID del horario a buscar:");
            if (input == null || input.trim().isEmpty()) return;
            try {
                actual = Lista.BuscarPorId(Integer.parseInt(input.trim()));
                if (actual == null) {
                    JOptionPane.showMessageDialog(null, "Horario no encontrado");
                } else {
                    ProcesoHorarios.MostrarHorario(actual.h, vista);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero valido");
            }
        }
    }
}
