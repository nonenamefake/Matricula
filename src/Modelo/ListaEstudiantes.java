package Modelo;
import java.io.Serializable;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListaEstudiantes implements Serializable {
   public NodoEstudiantes ini;
   public NodoEstudiantes fin;
   public ListaEstudiantes(){
       ini=fin=null;
   }
   public void MostrarCliente(JTable tabla) {
       String titulos[]= {"id","Dni","Nombre","Apellido Paterno","Apellido Materno","sexo", "direccion", "telefono","nacimiento"};
       DefaultTableModel mt =  new DefaultTableModel(null,titulos);
       tabla.setModel(mt);     
       for( NodoEstudiantes aux=ini;aux!=null;aux=aux.sig){
           mt.addRow(aux.es.Registro());
       }
   }
    public void InsertarEstudiantes(Estudiantes es){
       NodoEstudiantes nuevo = new NodoEstudiantes(es);
       if(ini==null){
           ini=fin=nuevo;
       }else{
           nuevo.ant=fin;
           fin.sig=nuevo;
       }
       fin=nuevo;
       fin.sig=null;
   }
    public void EliminarEstudiantes(NodoEstudiantes actual){
       if(actual!=null){
           if(actual==ini){
               ini=actual.sig;
               if(actual.sig!=null)
                   actual.sig.ant=null;
           }else if(actual.sig!=null){
             actual.ant.sig = actual.sig;
             actual.sig.ant = actual.ant;
           }else{
               actual.ant.sig=null;
               fin=actual.ant;
           } //fin if
           actual=null;
       }
   }
       public NodoEstudiantes BuscarPordni(int dnibuscar){
       NodoEstudiantes encontrado=ini;
       while(encontrado!=null){
           if(encontrado.es.getDni()==dnibuscar)
               return encontrado;
           encontrado=encontrado.sig;
       }//fin while
       return null;
   }
}