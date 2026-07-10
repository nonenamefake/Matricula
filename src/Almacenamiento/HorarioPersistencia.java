package Almacenamiento;
import javax.swing.JOptionPane;
import Modelo.ListaHorario;
import java.io.*;

public class HorarioPersistencia {
    public static String archivo = "ListaHorarios.bin";
    public static void GuardarLista(ListaHorario lh) {
        try {
            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lh);
            oos.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar horarios: " + ex);
        }
    }
    public static ListaHorario RecuperarLista() {
        ListaHorario lista = new ListaHorario();
        try {
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            lista = (ListaHorario) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar horarios: " + ex);
        }
        return lista;
    }
}
