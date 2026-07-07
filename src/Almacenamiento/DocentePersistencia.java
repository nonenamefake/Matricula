package Almacenamiento;
import javax.swing.JOptionPane;
import Modelo.ListaDocentes;
import java.io.*;
public class DocentePersistencia {
    public static String archivo = "ListaDocentes.bin";
    public static void GuardarLista(ListaDocentes ld) {
        try {
            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ld);
            oos.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar docentes: " + ex);
        }
    }
    public static ListaDocentes RecuperarLista() {
        ListaDocentes lista = new ListaDocentes();
        try {
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            lista = (ListaDocentes) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar docentes: " + ex);
        }
        return lista;
    }
}
