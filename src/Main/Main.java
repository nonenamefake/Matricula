
package Main;

import javax.swing.JFrame;
import Vista.*;
import Controlador.*;

public class Main {

    public static void main(String[] args) {
        MenuPrincipal fm = new MenuPrincipal();
        ControlMenu cm = new ControlMenu(fm);
        fm.setVisible(true);
        fm.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
}
