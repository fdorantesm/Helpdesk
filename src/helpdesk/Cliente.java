package helpdesk;

import helpdesk.Lang.Spanish.Logout;
import helpdesk.Lang.Spanish.Home;
import java.awt.BorderLayout;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public final class Cliente extends JFrame implements ActionListener{
    
   
    //Objetos funcionales
    private static MySQL mysql = new MySQL(); 
    private Funciones f;   

    //Home
    private static JPanel menu, main;    
    private static JButton [] boton;    
    private static JPanel [] panel;
    
    //Login
    private static JLabel logo,usr,pwd;
    private static JPanel cabecera,form;       
    private static JTextField user,pass;
    private static Fuente fuente;
    private static JButton login;
    
    //Instancia de los componentes
    public final void init() throws ClassNotFoundException, SQLException, FontFormatException, IOException{
        main = new JPanel(new BorderLayout());
        menu = new JPanel(new GridLayout(5, 1)); 
       
        boton = new JButton[5];
        panel = new JPanel[4];
        
        boton[0] = new JButton(Home.TICKETS);
        boton[1] = new JButton(Home.NEW_TICKET);
        boton[2] = new JButton(Home.FAQS);
        boton[3] = new JButton(Home.DATA);
        boton[4] = new JButton(Home.LOGOUT);          
        
        panel[0] = new Tickets(""); 
        panel[1] = new NewTicket();
        panel[2] = new FAQ();
        panel[3] = new Datos();
    }
        
    //Constructor alterno, recibe el hash del usuario
    public Cliente() throws ClassNotFoundException, SQLException, FontFormatException, IOException{
        f = new Funciones();
        Config.apariencia();
        setSize(720,480);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(Home.TITLE+" - "+f.getIndex("nombre", f.usuario()));
        init();
        
        mysql.connect(Config.host, Config.base, Config.user, Config.pass);
        
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
    
    @Override
    public void actionPerformed(ActionEvent e){
        switch(e.getActionCommand()){
            case Home.TICKETS: mostrar(panel[0]); break;
            case Home.NEW_TICKET: mostrar(panel[1]); break;
            case Home.FAQS: mostrar(panel[2]); break;
            case Home.DATA: mostrar(panel[3]); break;
            case Home.LOGOUT:  
                try {
                    salir();
                }catch (FontFormatException | IOException | ClassNotFoundException | SQLException ex) {
                
                }
                break;
            //default: ;
        }
    }
    
    //Literalmente un logout, borra el hash, cierra el menu principal e instancia el login
    public void salir() throws FontFormatException, IOException, ClassNotFoundException, SQLException{
        int respuesta = Config.confirm(this, Logout.LOGOUT_CONFIRM, Home.LOGOUT, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(respuesta==0){
            Session.destroy();
            this.dispose();
            new IniciarSesion();
        }
    }
    
    //método para mostrar el panel seleccionado, oculta los demás
    private void mostrar(JPanel panel){
        for(JPanel pan : Cliente.panel){
            pan.setVisible(false);
        }
        panel.setVisible(true);
    }
    
    //El método que no necesita presentación...
    public static void main(String[] args) throws ClassNotFoundException, SQLException, FontFormatException, IOException {
        new Cliente();
    }
}