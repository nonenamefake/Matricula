
package Main;

import javax.swing.JFrame;
import Vista.*;
import Controlador.*;

public class Main {

    public static void main(String[] args) {
        Login fm = new Login();
        ControladorLogin cm = new ControladorLogin(fm);
        fm.setVisible(true);
        fm.setLocationRelativeTo(null);
    }
    
}
