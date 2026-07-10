package Controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Almacenamiento.MatriculaPersistencia;
import Almacenamiento.EstudiantePersistencia;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import Modelo.*;
import Procesos.ProcesoMatriculas;
import Vista.Matricula;
import javax.swing.JOptionPane;

public class ControladorMatriculas implements ActionListener, ItemListener {
    ListaMatricula Lista;
    NodoMatricula actual;
    Modelo.Matricula m;
    Matricula vista;
    int idEstudiante;
    String nombreEstudiante;
    ArbolEstudiantes arbol;

    public ControladorMatriculas(Matricula fa, int idEstudiante) {
        this.vista = fa;
        this.idEstudiante = idEstudiante;
        Lista = new ListaMatricula();
        vista.jButton3.addActionListener(this);
        vista.jButton6.addActionListener(this);
        vista.cbxgrado.addItemListener(this);
        Lista = MatriculaPersistencia.RecuperarLista();
        arbol = new ArbolEstudiantes();
        ListaEstudiantes le = EstudiantePersistencia.RecuperarLista();
        for (NodoEstudiantes aux = le.ini; aux != null; aux = aux.sig)
            if (aux.es.getId() == idEstudiante) {
                nombreEstudiante = aux.es.getNombres() + " " + aux.es.getApellido_pa() + " " + aux.es.getApellido_ma();
                vista.lbestudiantes.setText(nombreEstudiante);
                break;
            }
        ProcesoMatriculas.CargarAnios(vista);
        ProcesoMatriculas.CargarGrados(vista);
        ProcesoMatriculas.LimpiarEntradas(vista);
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == vista.cbxgrado && e.getStateChange() == ItemEvent.SELECTED) {
            ProcesoMatriculas.GradoItem gi = (ProcesoMatriculas.GradoItem) vista.cbxgrado.getSelectedItem();
            if (gi != null)
                ProcesoMatriculas.CargarSalonesPorGrado(vista, gi.id_grado);
        }
    }
    private void ActualizarVista() {
        MatriculaPersistencia.GuardarLista(Lista);
        ProcesoMatriculas.LimpiarEntradas(vista);
    }

    public String buscarEnArbol(int dni) {
        Estudiantes e = arbol.buscar(dni);
        if (e == null) return null;
        return e.getNombres() + " " + e.getApellido_pa();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jButton3) {
            if (vista.cbxaños.getSelectedItem() == null ||
                vista.cbxgrado.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione año y grado");
                return;
            }
            if (Lista.existeMatricula(idEstudiante,
                ((ProcesoMatriculas.AnioItem) vista.cbxaños.getSelectedItem()).id_anio)) {
                JOptionPane.showMessageDialog(null, "El estudiante ya esta matriculado en este año");
                return;
            }
            m = ProcesoMatriculas.LeerDatos(vista, Lista, idEstudiante);
            Lista.InsertarMatricula(m);
            ActualizarVista();
            JOptionPane.showMessageDialog(null, "Matricula registrada.....");
        }
        if (e.getSource() == vista.jButton6) {
            ProcesoMatriculas.LimpiarEntradas(vista);
            actual = null;
        }
    }
}
