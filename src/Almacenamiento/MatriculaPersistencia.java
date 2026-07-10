package Almacenamiento;
import javax.swing.JOptionPane;
import Modelo.ListaMatricula;
import java.io.*;

public class MatriculaPersistencia {
    public static String archivo = "ListaMatriculas.bin";
    public static void GuardarLista(ListaMatricula lm) {
        try {
            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lm);
            oos.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar" + ex);
        }
    }
    public static ListaMatricula RecuperarLista() {
        ListaMatricula lista = new ListaMatricula();
        try {
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            lista = (ListaMatricula) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar" + ex);
        }
        return lista;
    }
}
