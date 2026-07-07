package Almacenamiento;
import javax.swing.JOptionPane;
import Modelo.ListaCurso;
import java.io.*;
public class CursoPersistencia {
    public static String archivo = "ListaCursos.bin";
    public static void GuardarLista(ListaCurso lc) {
        try {
            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lc);
            oos.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar cursos: " + ex);
        }
    }
    public static ListaCurso RecuperarLista() {
        ListaCurso lista = new ListaCurso();
        try {
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            lista = (ListaCurso) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar cursos: " + ex);
        }
        return lista;
    }
}
