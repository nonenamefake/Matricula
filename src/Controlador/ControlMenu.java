
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vista.*;
import javax.swing.JInternalFrame;
public class ControlMenu implements ActionListener{
     MenuPrincipal vista;
      public ControlMenu(MenuPrincipal fm){
          vista=fm;
          vista.itmestudiante.addActionListener(this);
          vista.itmdocente.addActionListener(this);
          vista.itmcurso.addActionListener(this);
          vista.itmsalon.addActionListener(this);
          vista.itmgradocurso.addActionListener(this);
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