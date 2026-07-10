package Almacenamiento;

import javax.swing.JOptionPane;
import Modelo.ColaEstudiantes;
import java.io.*;

public class ColaPersistencia {
    public static String archivo = "ColaAtencion.bin";
    public static void GuardarLista(ColaEstudiantes c) {
        try {
            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(c);
            oos.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar cola" + ex);
        }
    }
    public static ColaEstudiantes RecuperarLista() {
        ColaEstudiantes c = new ColaEstudiantes();
        try {
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            c = (ColaEstudiantes) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar cola" + ex);
        }
        return c;
    }
}
