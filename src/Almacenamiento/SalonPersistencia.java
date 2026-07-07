package Almacenamiento;
import javax.swing.JOptionPane;
import Modelo.ListaSalon;
import java.io.*;

public class SalonPersistencia {
    public static String archivo = "ListaSalones.bin";
    public static void GuardarLista(ListaSalon ls) {
        try {
            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ls);
            oos.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar salones: " + ex);
        }
    }
    public static ListaSalon RecuperarLista() {
        ListaSalon lista = new ListaSalon();
        try {
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            lista = (ListaSalon) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar salones: " + ex);
        }
        return lista;
    }
}
