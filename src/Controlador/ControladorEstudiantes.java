package Controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Almacenamiento.EstudiantePersistencia;
import Modelo.*;
import Procesos.ProcesoEstudiantes;
import Vista.Estudiantesform;
import javax.swing.JOptionPane;

public class ControladorEstudiantes implements ActionListener{
    ListaEstudiantes Lista;
    NodoEstudiantes actual;
    Estudiantes es;
    Estudiantesform vista;
    public ControladorEstudiantes(Estudiantesform fa){
        vista=fa;
        Lista =  new ListaEstudiantes();
        vista.btnregistrar.addActionListener(this);
        vista.btneditar.addActionListener(this);
        vista.btnbuscar.addActionListener(this);
        vista.btneliminar.addActionListener(this);
        vista.btnlimpiar.addActionListener(this);
        Lista = EstudiantePersistencia.RecuperarLista();
        Lista.MostrarCliente(vista.tbldatos);
        botoneslimpiar();
    }
    private void ActualizarVista(){
        Lista.MostrarCliente(vista.tbldatos);
        EstudiantePersistencia.GuardarLista(Lista);
        ProcesoEstudiantes.LimpiarEntradas(vista);
    }
    private void botoneslimpiar(){
        vista.btnregistrar.setEnabled(true);
        vista.btneditar.setEnabled(false);
        vista.btnbuscar.setEnabled(true);
        vista.btneliminar.setEnabled(false);
        vista.btnlimpiar.setEnabled(true);
    }
    private void botonselecion(){
        vista.btnregistrar.setEnabled(false);
        vista.btneditar.setEnabled(true);
        vista.btnbuscar.setEnabled(true);
        vista.btneliminar.setEnabled(true);
        vista.btnlimpiar.setEnabled(true);
    }
        @Override
    public void actionPerformed(ActionEvent e) {
           if(e.getSource()== vista.btnregistrar){
               es =  ProcesoEstudiantes.LeerDatos(vista, Lista);
               Lista.InsertarEstudiantes(es);
               ActualizarVista();
               JOptionPane.showMessageDialog(null,"Asistente registrado.....");
           }
           if(e.getSource()==vista.btneditar){
               if(actual==null){
                   JOptionPane.showMessageDialog(null,"Primero busque un estudiante para editar");
                   return;
               }
               actual.es.setDni(Integer.parseInt(vista.txtdni.getText()));
               actual.es.setNombres(vista.txtnombre.getText());
               actual.es.setApellido_pa(vista.txtapellido1.getText());
               actual.es.setApellido_ma(vista.txtapellido2.getText());
               if(vista.cbxsexo.getSelectedItem()!=null)
                   actual.es.setSexo(vista.cbxsexo.getSelectedItem().toString());
               actual.es.setTelefono(Integer.parseInt(vista.txttefefono.getText()));
               actual.es.setDireccion(vista.txtdireccion.getText());
               actual.es.setNacimiento(vista.datenacimiento.getDate());
               actual=null;
               ActualizarVista();
               botoneslimpiar();
               JOptionPane.showMessageDialog(null,"Estudiante actualizado.....");
           }
           if(e.getSource()==vista.btnbuscar){
               String cb = JOptionPane.showInputDialog("Ingrese codigo a buscar..");
               try{
                   actual = Lista.BuscarPordni(Integer.parseInt(cb));
               }catch(NumberFormatException ex){
                   JOptionPane.showMessageDialog(null,"Solo se puede ingresar numeros");
               }
               
               if(actual==null){
                   JOptionPane.showMessageDialog(null,"Código "+cb+" no existe en la lista");
               }else{
                   botonselecion();
                   ProcesoEstudiantes.MostrarEstudiantes(actual.es, vista);
               }
           }
           if(e.getSource() == vista.btneliminar){
               int resp = JOptionPane.showConfirmDialog(
                null,"Desea eliminar a "+actual.es.getId()+"?", "Confirmar!!!",JOptionPane.OK_CANCEL_OPTION);
               if(resp==0){
                   botoneslimpiar();
                   Lista.EliminarEstudiantes(actual);
                   ActualizarVista();
                   JOptionPane.showMessageDialog(null,"Registro eliminado...");
               }
           }
           if(e.getSource()==vista.btnlimpiar){
               ProcesoEstudiantes.LimpiarEntradas(vista);
               actual=null;
               botoneslimpiar();
           }
    }
}
