package Almacenamiento;
import javax.swing.JOptionPane;
import Modelo.ListaEstudiantes;
import java.io.*;
public class EstudiantePersistencia {
    public static String archivo="ListaEstudiantes.bin";
    public static void GuardarLista(ListaEstudiantes lc){
        try{
            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos =  new ObjectOutputStream(fos);
            oos.writeObject(lc);
            oos.close();            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error al guardar"+ex);
        }
    }
    public static ListaEstudiantes RecuperarLista(){
        ListaEstudiantes lista=new ListaEstudiantes();
        try{
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            lista = (ListaEstudiantes)ois.readObject();
            ois.close();            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error al cargar"+ex);
         }
        return lista;
    }
}