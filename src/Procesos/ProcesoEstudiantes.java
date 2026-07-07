package Procesos;

import Modelo.Estudiantes;
import Modelo.ListaEstudiantes;
import Modelo.NodoEstudiantes;
import Vista.Estudiantesform;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProcesoEstudiantes {
    public static void LimpiarEntradas(Estudiantesform fa){
        fa.txtdni.setText("");
        fa.txtnombre.setText("");
        fa.cbxsexo.setSelectedIndex(-1);
        fa.txtapellido1.setText("");
        fa.txtapellido2.setText("");
        fa.txttefefono.setText("");
        fa.txtdireccion.setText("");
        fa.datenacimiento.setDate(new Date());
        fa.txtdni.requestFocus();
    }
    
    public static Estudiantes LeerDatos(Estudiantesform fa, ListaEstudiantes le){
        Estudiantes e = new Estudiantes();
        e.setDni(Integer.parseInt(fa.txtdni.getText()));
        e.setNombres(fa.txtnombre.getText());
        e.setApellido_ma(fa.txtapellido2.getText());
        e.setApellido_pa(fa.txtapellido1.getText());
        e.setSexo(fa.cbxsexo.getSelectedItem().toString());
        e.setTelefono(Integer.parseInt(fa.txttefefono.getText()) );
        e.setDireccion(fa.txtdireccion.getText());
        e.setNacimiento(fa.datenacimiento.getDate());
        if(e.getId()==0){
            e.setId(obtenerSiguienteId(le));
        }
        return e;
    }
   public static void MostrarEstudiantes(Estudiantes c,Estudiantesform fa){ 
       fa.txtdni.setText(String.valueOf(c.getDni()));
       fa.txtnombre.setText(c.getNombres());
       fa.txttefefono.setText(String.valueOf(c.getTelefono()));
       if(c.getSexo().equals("Masculino")) fa.cbxsexo.setSelectedIndex(0);
       else if(c.getSexo().equals("Femenino")) fa.cbxsexo.setSelectedIndex(1);
       else  fa.cbxsexo.setSelectedIndex(2);
       fa.txtapellido1.setText(c.getApellido_pa());
       fa.txtapellido2.setText(c.getApellido_ma());
       fa.txtdireccion.setText(c.getDireccion());
       fa.datenacimiento.setDate(c.getNacimiento());
       fa.txtdni.requestFocus();       
   }
   
   
   public static int obtenerSiguienteId(ListaEstudiantes le) {
       if(le.fin==null){
           return 1;
       }
        int idMayor = 0;
        NodoEstudiantes actual = le.ini;
        while(actual != null){
            if(actual.es.getId()> idMayor)
                idMayor = actual.es.getId();
            actual=actual.sig;
        }
        return idMayor + 1;
    }
   
   
}
