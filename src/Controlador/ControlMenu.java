
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vista.*;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
public class ControlMenu implements ActionListener{
     MenuPrincipal vista;
       public ControlMenu(MenuPrincipal fm){
            vista=fm;
            vista.itmestudiante.addActionListener(this);
            vista.itmdocente.addActionListener(this);
            vista.itmcurso.addActionListener(this);
            vista.itmsalon.addActionListener(this);
            vista.itmgradocurso.addActionListener(this);
            vista.itmanios.addActionListener(this);
            vista.itmdocentecurso.addActionListener(this);
            vista.itmmatricula.addActionListener(this);
            vista.itmcolaatencion.addActionListener(this);
            vista.itmañomatricula.addActionListener(this);
        }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== vista.itmestudiante){
             Estudiantesform ef = new Estudiantesform();
             ef.setTitle("REGISTRO DE ESTUDIANTES....");
             ef.setVisible(true);
             ControladorEstudiantes ce = new ControladorEstudiantes(ef);
             MostrarInternalFrame(ef);
         }
        if(e.getSource()== vista.itmdocente){
             Docente df = new Docente();
             df.setTitle("REGISTRO DE DOCENTES....");
             df.setVisible(true);
             ControladorDocentes cd = new ControladorDocentes(df);
             MostrarInternalFrame(df);
         }
         if(e.getSource()== vista.itmcurso){
              Cursos cf = new Cursos();
              cf.setTitle("REGISTRO DE CURSOS....");
              cf.setVisible(true);
              ControladorCursos cc = new ControladorCursos(cf);
              MostrarInternalFrame(cf);
          }
         if(e.getSource()== vista.itmsalon){
              Salones sf = new Salones();
              sf.setTitle("REGISTRO DE SALONES....");
              sf.setVisible(true);
              ControladorSalones cs = new ControladorSalones(sf);
              MostrarInternalFrame(sf);
          }
          if(e.getSource()== vista.itmgradocurso){
               GradoCursoForm gf = new GradoCursoForm();
               gf.setTitle("ASIGNAR CURSOS A GRADOS....");
               gf.setVisible(true);
               ControladorGradoCurso cg = new ControladorGradoCurso(gf);
               MostrarInternalFrame(gf);
           }
          if(e.getSource()== vista.itmanios){
               A\u00f1osform af = new A\u00f1osform();
               af.setTitle("REGISTRO DE A\u00d1OS ACAD\u00c9MICOS....");
               af.setVisible(true);
               ControladorAnios ca = new ControladorAnios(af);
               MostrarInternalFrame(af);
           }
            if(e.getSource()== vista.itmdocentecurso){
                 String input = javax.swing.JOptionPane.showInputDialog("Ingrese ID del docente:");
                 if (input == null || input.trim().isEmpty()) return;
                 try {
                     int idDocente = Integer.parseInt(input.trim());
                     DocenteCursoform df = new DocenteCursoform();
                     df.setTitle("ASIGNAR CURSOS - Docente ID: " + idDocente);
                     df.setVisible(true);
                     ControladorDocenteCurso cd = new ControladorDocenteCurso(df, idDocente);
                     MostrarInternalFrame(df);
                 } catch (NumberFormatException ex) {
                     javax.swing.JOptionPane.showMessageDialog(null, "Ingrese un ID valido");
                 }
             }
            if(e.getSource()== vista.itmañomatricula){
                 Añomatricula af = new Añomatricula();
                 af.setTitle("MATRICULAS POR A\u00d1O");
                 af.setVisible(true);
                 ControladorA\u00f1oMatricula ca = new ControladorA\u00f1oMatricula(af);
                 MostrarInternalFrame(af);
             }
            if(e.getSource()== vista.itmmatricula){
                 String dniInput = JOptionPane.showInputDialog("Ingrese DNI del estudiante:");
                 if (dniInput == null || dniInput.trim().isEmpty()) return;
                 try {
                     int dni = Integer.parseInt(dniInput.trim());
                     Modelo.ListaEstudiantes le = Almacenamiento.EstudiantePersistencia.RecuperarLista();
                     Modelo.NodoEstudiantes ne = le.BuscarPordni(dni);
                     if (ne == null) {
                         JOptionPane.showMessageDialog(null, "Estudiante no registrado. Registrelo primero.");
                         return;
                     }
                     Matricula mf = new Matricula();
                     mf.setTitle("MATRICULA - " + ne.es.getNombres() + " " + ne.es.getApellido_pa());
                     mf.setVisible(true);
                     ControladorMatriculas cm = new ControladorMatriculas(mf, ne.es.getId());
                     MostrarInternalFrame(mf);
                 } catch (NumberFormatException ex) {
                     JOptionPane.showMessageDialog(null, "Ingrese un DNI valido (solo numeros)"+ex);
                     System.out.println(ex);
                 }
             }
             if (e.getSource() == vista.itmcolaatencion) {
                 VistaCola vc = new VistaCola();
                 vc.setTitle("COLA DE ATENCION");
                 vc.setVisible(true);
                 ControladorCola cc = new ControladorCola(vc);
                 MostrarInternalFrame(vc);
             }

    }//fin del action
    
    private void MostrarInternalFrame(JInternalFrame jif){
       
        vista.dktpanel.removeAll();
        vista.dktpanel.add(jif);   
        int x = (vista.dktpanel.getWidth() / 2) - (jif.getWidth() / 2);
        int y = (vista.dktpanel.getHeight() / 2) - (jif.getHeight() / 2);
        jif.setLocation(x, y);        
        vista.dktpanel.repaint();
        
    }  
}