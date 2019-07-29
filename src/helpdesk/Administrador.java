package helpdesk;

import helpdesk.Lang.Spanish.AdminPanel;
import helpdesk.Lang.Spanish.Logout;
import java.awt.BorderLayout;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Administrador extends JFrame implements ActionListener{
    
    private static Funciones f;
    private JPanel main, menu;
    private JButton[] boton;
    private JPanel[] panel;
    
    public Administrador() throws ClassNotFoundException, SQLException, FontFormatException, IOException{
        f = new Funciones();
        setTitle(AdminPanel.TITLE+" - "+f.getIndex("usuario", f.usuario()));
        Config.apariencia();
        setSize(720,480);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();
    }
    
    public final void init() throws ClassNotFoundException, SQLException, FontFormatException, IOException{
        main = new JPanel(new BorderLayout());
        menu = new JPanel(new GridLayout(5, 1)); 
       
        boton = new JButton[5];       
        boton[0] = new JButton(AdminPanel.Menu.TICKETS);
        boton[1] = new JButton(AdminPanel.Menu.USERS);
        boton[2] = new JButton(AdminPanel.Menu.TECHS);
        boton[3] = new JButton(AdminPanel.Menu.DATA);
        boton[4] = new JButton(Lang.Spanish.Home.LOGOUT);          
        
        panel = new JPanel[4];
        panel[0] = new Tickets(""); 
        panel[1] = new Usuarios();
        panel[2] = new FAQ();
        panel[3] = new Datos();
        
        for (JButton button : boton) {
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.addActionListener(this);
            menu.add(button);
        }
        
        //Sidebar a la izquierda
        add(menu,BorderLayout.WEST);
        add(main,BorderLayout.CENTER);        

        for (JPanel i : panel) {
            i.setVisible(false);
            i.setSize(625, 480);
            main.add(i);
        }      
        
        validate();
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, FontFormatException, IOException {
        f = new Funciones();
        new Administrador();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        switch(e.getActionCommand()){
            case Lang.Spanish.Home.TICKETS: mostrar(panel[0]); break;
            case Lang.Spanish.Home.DATA: mostrar(panel[3]); break;
            case Lang.Spanish.Home.LOGOUT:  
                try {
                    salir();
                }catch (FontFormatException | IOException | ClassNotFoundException | SQLException ex) {
                
                }
                break;
            //default: ;
        }
    }
    
    //método para mostrar el panel seleccionado, oculta los demás
    private void mostrar(JPanel panel){
        for(JPanel pan : this.panel){
            pan.setVisible(false);
        }
        panel.setVisible(true);
    }
    
    //Literalmente un logout, borra el hash, cierra el menu principal e instancia el login
    public void salir() throws FontFormatException, IOException, ClassNotFoundException, SQLException{
        int respuesta = JOptionPane.showConfirmDialog(this, Logout.LOGOUT_CONFIRM, AdminPanel.Menu.LOGOUT, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null);
        if(respuesta==0){
            Session.destroy();
            this.dispose();
            new IniciarSesion();
        }
    }
}
