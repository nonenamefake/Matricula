package Controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Almacenamiento.GradoCursoPersistencia;
import Almacenamiento.CursoPersistencia;
import Modelo.*;
import Procesos.ProcesoGradoCurso;
import Vista.GradoCursoForm;
import javax.swing.JOptionPane;

public class ControladorGradoCurso implements ActionListener {
    ListaGradoCurso Lista;
    NodoGradoCurso actual;
    GradoCurso gc;
    GradoCursoForm vista;

    public ControladorGradoCurso(GradoCursoForm fa) {
        vista = fa;
        Lista = new ListaGradoCurso();
        vista.btnguardar.addActionListener(this);
        vista.btneliminar.addActionListener(this);
        vista.btnlimpiar.addActionListener(this);
        vista.btnfiltrar.addActionListener(this);
        vista.btnbuscar.addActionListener(this);
        Lista = GradoCursoPersistencia.RecuperarLista();
        ProcesoGradoCurso.CargarCursos(vista, CursoPersistencia.RecuperarLista());
        Lista.MostrarTodos(vista.tbldatos);
        ActualizarVista();
    }

    private void ActualizarVista() {
        Lista.MostrarTodos(vista.tbldatos);
        GradoCursoPersistencia.GuardarLista(Lista);
        ProcesoGradoCurso.LimpiarEntradas(vista);
    }
    private void botoneslimpiar(){
        vista.btnguardar.setEnabled(true);
        vista.btnbuscar.setEnabled(true);
        vista.btneliminar.setEnabled(false);
        vista.btnlimpiar.setEnabled(true);
    }
    private void botonselecion(){
        vista.btnguardar.setEnabled(false);
        vista.btnbuscar.setEnabled(true);
        vista.btneliminar.setEnabled(true);
        vista.btnlimpiar.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnguardar) {
            if (vista.cbxgrado.getSelectedItem() == null || vista.cbxcurso.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione grado y curso");
                return;
            }
            gc = ProcesoGradoCurso.LeerDatos(vista, Lista);
            Lista.InsertarGradoCurso(gc);
            ActualizarVista();
            JOptionPane.showMessageDialog(null, "Curso asignado al grado.....");
        }
        if (e.getSource() == vista.btneliminar) {
            if (actual == null) {
                JOptionPane.showMessageDialog(null, "Primero busque una asignacion para eliminar");
                return;
            }
            int resp = JOptionPane.showConfirmDialog(null,
                "Desea eliminar la asignacion?", "Confirmar!!!", JOptionPane.OK_CANCEL_OPTION);
            if (resp == 0) {
                Lista.EliminarGradoCurso(actual);
                actual = null;
                ActualizarVista();
                JOptionPane.showMessageDialog(null, "Asignacion eliminada...");
            }
        }
        if(e.getSource()==vista.btnbuscar){
               String cb = JOptionPane.showInputDialog("Ingrese ID a buscar..");
               try{
                   actual = Lista.BuscarPorId(Integer.parseInt(cb));
               }catch(NumberFormatException ex){
                   JOptionPane.showMessageDialog(null,"Solo se puede ingresar numeros");
               }
               
               if(actual==null){
                   JOptionPane.showMessageDialog(null,"ID "+cb+" no existe en la lista");
               }else{
                   botonselecion();
                   ProcesoGradoCurso.Mostrarcursogrado(actual.gc, vista);
               }
           }
        if (e.getSource() == vista.btnlimpiar) {
            ProcesoGradoCurso.LimpiarEntradas(vista);
            actual = null;
            Lista.MostrarTodos(vista.tbldatos);
        }
        if (e.getSource() == vista.btnfiltrar) {
            String input = JOptionPane.showInputDialog("Ingrese el numero de grado (1-6) para filtrar:");
            if (input == null || input.trim().isEmpty()) return;
            try {
                int grado = Integer.parseInt(input.trim());
                if (grado < 1 || grado > 6) {
                    JOptionPane.showMessageDialog(null, "Grado invalido. Use 1-6");
                    return;
                }
                Lista.MostrarPorGrado(vista.tbldatos, grado);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero valido");
            }
        }
    }
}
