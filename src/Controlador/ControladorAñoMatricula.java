package Controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Almacenamiento.MatriculaPersistencia;
import Almacenamiento.AnioPersistencia;
import Modelo.*;
import ModeloArrayList.ListaReportes;
import Vista.A\u00f1omatricula;

public class ControladorA\u00f1oMatricula implements ActionListener {
    ListaMatricula Lista;
    A\u00f1omatricula vista;
    PilaHistorial pila;
    ArbolEstudiantes arbol;
    int ultimoIdAnio;

    public ControladorA\u00f1oMatricula(A\u00f1omatricula fa) {
        this.vista = fa;
        vista.btnbuscar.addActionListener(this);
        vista.btnAtras.addActionListener(this);
        vista.btnRecorridos.addActionListener(this);
        Lista = MatriculaPersistencia.RecuperarLista();
        pila = new PilaHistorial();
        arbol = new ArbolEstudiantes();
        ultimoIdAnio = 0;
        cargarAnios();
    }
    private void cargarAnios() {
        ListaAnioAcademico la = AnioPersistencia.RecuperarLista();
        for (NodoAnioAcademico aux = la.ini; aux != null; aux = aux.sig)
            vista.cbxanio.addItem(aux.aa.getAnio());
    }
    public void retrocederAnio() {
        String anioAnt = pila.pop();
        if (anioAnt == null) return;
        int idAnio = resolverIdAnio(anioAnt);
        if (idAnio > 0) {
            ultimoIdAnio = idAnio;
            ListaReportes.mostrarOrdenado(vista.tbldatos, idAnio);
        }
    }
    public String obtenerRecorridos() {
        String pre = "PRE-ORDEN:\n" + arbol.recorridoPreOrden() + "\n";
        String in = "IN-ORDEN:\n" + arbol.recorridoInOrden() + "\n";
        String post = "POST-ORDEN:\n" + arbol.recorridoPostOrden();
        return pre + in + post;
    }

    private int resolverIdAnio(String anioText) {
        ListaAnioAcademico la = AnioPersistencia.RecuperarLista();
        for (NodoAnioAcademico aux = la.ini; aux != null; aux = aux.sig)
            if (aux.aa.getAnio().equals(anioText))
                return aux.aa.getId_anio();
        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnbuscar) {
            if (vista.cbxanio.getSelectedItem() == null) return;
            String anioText = vista.cbxanio.getSelectedItem().toString();
            if (ultimoIdAnio > 0)
                pila.push(resolverAnioTexto(ultimoIdAnio));
            int idAnio = resolverIdAnio(anioText);
            if (idAnio > 0) {
                ultimoIdAnio = idAnio;
                ListaReportes.mostrarOrdenado(vista.tbldatos, idAnio);
            }
        }
        if (e.getSource() == vista.btnAtras) { retrocederAnio(); }
        if (e.getSource() == vista.btnRecorridos) {
            vista.jTextArea1.setText(obtenerRecorridos());
        }
    }
    
    private String resolverAnioTexto(int idAnio) {
        ListaAnioAcademico la = AnioPersistencia.RecuperarLista();
        for (NodoAnioAcademico aux = la.ini; aux != null; aux = aux.sig)
            if (aux.aa.getId_anio() == idAnio)
                return aux.aa.getAnio();
        return "";
    }
}
