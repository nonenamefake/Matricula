package Almacenamiento;
import javax.swing.JOptionPane;
import Modelo.ListaDocenteCurso;
import java.io.*;

public class DocenteCursoPersistencia {
    public static String archivo = "ListaDocenteCurso.bin";
    public static void GuardarLista(ListaDocenteCurso lc) {
        try {
            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lc);
            oos.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar docente-curso: " + ex);
        }
    }
    public static ListaDocenteCurso RecuperarLista() {
        ListaDocenteCurso lista = new ListaDocenteCurso();
        try {
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            lista = (ListaDocenteCurso) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar docente-curso: " + ex);
        }
        return lista;
    }
}
