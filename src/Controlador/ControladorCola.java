package Controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Almacenamiento.ColaPersistencia;
import Almacenamiento.EstudiantePersistencia;
import Modelo.ColaEstudiantes;
import Modelo.NodoCola;
import Modelo.ListaEstudiantes;
import Modelo.NodoEstudiantes;
import Vista.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControladorCola implements ActionListener {
    ColaEstudiantes Cola;
    VistaCola vista;

    public ControladorCola(VistaCola fa) {
        this.vista = fa;
        vista.btnEncolar.addActionListener(this);
        vista.btnAtender.addActionListener(this);
        Cola = ColaPersistencia.RecuperarLista();
        mostrarCola();
    }

    private void mostrarCola() {
        String titulos[] = {"id", "Estudiante"};
        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        vista.tbldatos.setModel(mt);
        for (NodoCola aux = Cola.frente; aux != null; aux = aux.sig)
            mt.addRow(new Object[]{aux.idEstudiante, aux.nombreEstudiante});
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnEncolar) {
            String dniStr = JOptionPane.showInputDialog("Ingrese DNI del estudiante:");
            if (dniStr == null || dniStr.trim().isEmpty()) return;
            try {
                int dni = Integer.parseInt(dniStr.trim());
                ListaEstudiantes le = EstudiantePersistencia.RecuperarLista();
                NodoEstudiantes ne = le.BuscarPordni(dni);
                if (ne == null) {
                    JOptionPane.showMessageDialog(null, "Estudiante no registrado");
                    return;
                }
                String nombre = ne.es.getNombres() + " " + ne.es.getApellido_pa();
                Cola.encolar(ne.es.getId(), nombre);
                mostrarCola();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "DNI invalido");
            }
        }
        if (e.getSource() == vista.btnAtender) {
            if (Cola.estaVacia()) {
                JOptionPane.showMessageDialog(null, "No hay estudiantes en cola");
                return;
            }
            NodoCola atendido = Cola.desencolar();
            ColaPersistencia.GuardarLista(Cola);
            Matricula mf = new Matricula();
            mf.setTitle("MATRICULA - " + atendido.nombreEstudiante);
            mf.setVisible(true);
            new ControladorMatriculas(mf, atendido.idEstudiante);
            javax.swing.JDesktopPane dp = (javax.swing.JDesktopPane) vista.getParent();
            if (dp != null) {
                dp.removeAll();
                dp.add(mf);
                mf.setLocation(0, 0);
                dp.repaint();
            }
            mostrarCola();
        }
    }
}
