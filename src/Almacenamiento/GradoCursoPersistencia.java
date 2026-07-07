package Almacenamiento;
import javax.swing.JOptionPane;
import Modelo.ListaGradoCurso;
import java.io.*;

public class GradoCursoPersistencia {
    public static String archivo = "ListaGradoCurso.bin";
    public static void GuardarLista(ListaGradoCurso lg) {
        try {
            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lg);
            oos.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar asignaciones: " + ex);
        }
    }
    public static ListaGradoCurso RecuperarLista() {
        ListaGradoCurso lista = new ListaGradoCurso();
        try {
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            lista = (ListaGradoCurso) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar asignaciones: " + ex);
        }
        return lista;
    }
}
