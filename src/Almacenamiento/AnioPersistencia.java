package Almacenamiento;
import javax.swing.JOptionPane;
import Modelo.ListaAnioAcademico;
import java.io.*;

public class AnioPersistencia {
    public static String archivo = "ListaAnios.bin";
    public static void GuardarLista(ListaAnioAcademico la) {
        try {
            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(la);
            oos.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar a\u00f1os: " + ex);
        }
    }
    public static ListaAnioAcademico RecuperarLista() {
        ListaAnioAcademico lista = new ListaAnioAcademico();
        try {
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            lista = (ListaAnioAcademico) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar a\u00f1os: " + ex);
        }
        return lista;
    }
}
