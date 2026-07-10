
package Controlador;
import Vista.Estudiantesform;
import java.awt.event.ActionListener;
import Vista.Login;
import Vista.MenuPrincipal;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ControladorLogin implements ActionListener{
    Login vista;
    String user,password;
    public ControladorLogin(Login lg){
        vista = lg;
        vista.btniniciar.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== vista.btniniciar){
             user=vista.txtusuario.getText().toString();
             password=vista.txtcontraseña.getText().toString();
            if(user.equals("admin") && password.equals("1234")){
                MenuPrincipal fm = new MenuPrincipal();
                ControlMenu cm = new ControlMenu(fm);
                fm.setVisible(true);
                fm.setExtendedState(JFrame.MAXIMIZED_BOTH);
                vista.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto");
            }
         }
    }
}
