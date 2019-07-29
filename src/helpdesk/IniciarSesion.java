package helpdesk;

import helpdesk.Lang.Spanish.Login;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class IniciarSesion extends JFrame implements KeyListener{
    
    private static JLabel logo,usr,pwd;
    private static JPanel cabecera,form;       
    private static JTextField user,pass;
    private static Fuente fuente;
    private static JButton login;
    private static MySQL mysql;
    private Funciones f = new Funciones();;
    
    public final void init() throws FontFormatException, IOException{
        logo     = new JLabel(Config.cfg_name);
        usr      = new JLabel(Login.USERNAME+":");
        pwd      = new JLabel(Login.PASSWORD+":");
        cabecera = new JPanel(new FlowLayout(FlowLayout.CENTER));
        form     = new JPanel();
        user     = new JTextField();
        pass     = new JPasswordField();
        fuente   = new Fuente();
        login    = new JButton(Login.LOGIN);
        mysql    = new MySQL();
    }
    
    public IniciarSesion() throws SQLException, ClassNotFoundException, FontFormatException, IOException{
        if(!Session.get().equals("")){
            switch(Integer.parseInt(f.getIndex("rango", f.usuario()))){
                case 2:
                    new Cliente(); 
                    break;
                case 1:
                    new Tecnico();
                    break;
                case 0:
                    new Administrador();
                    break;
            }
        }else{
            new IniciarSesion(true);
        }
    }
    
    public IniciarSesion(boolean a365kjh65jk5ku4nnk4kjnkjmkhbu5gvu5ghb) throws FontFormatException, IOException, ClassNotFoundException, SQLException{
        super(Login.LOGIN);
        Config.apariencia();      
        setSize(480,320);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        init();
        
        mysql.connect(Config.host, Config.base, Config.user, Config.pass);

        logo.setFont(fuente.BebasNeue(48, Font.BOLD));
        
        cabecera.setBounds(0,30,480,60);
        cabecera.add(logo,JPanel.CENTER_ALIGNMENT);
        cabecera.setBackground(new Color(238,238,238));
        
        form.setLayout(null);
        form.setBounds(40,90,400,175);
        form.setBackground(new Color(238,238,238));
        
        //Labels
        usr.setBounds(0,15,400,20);
        pwd.setBounds(0, 70, 400, 20);
        //Inputs
        user.setBounds(0,35,400,30);      
        pass.setBounds(0, 90, 400, 30);
        
        login.setBounds(0, 135, 400, 40);
        login.setFocusPainted(false);
        login.setBorderPainted(false);
        
        form.add(user);
        form.add(usr);
        form.add(pass);
        form.add(pwd);
        form.add(login);

        add(cabecera);
        add(form);    
        repaint();
        revalidate();

        user.addKeyListener(this);
        pass.addKeyListener(this);
        
        login.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==login){
                    try {
                        submit();
                    } catch (FontFormatException | IOException ex) {

                    }
                }
            }
        });
    }
    
    public void submit() throws FontFormatException, IOException{
        if ("".equals(user.getText())){
            Config.alert(IniciarSesion.this,Login.Error.NULLUSERNAME,true);
        }else if(!Validaciones.usuario(user.getText())){
            Config.alert(IniciarSesion.this, Login.Error.ILEGALUSERNAME,true);
        }else if("".equals(pass.getText())){
            Config.alert(IniciarSesion.this, Login.Error.NULLPASSWORD,true);
        }else if(!Validaciones.contrasena(pass.getText())){
            Config.alert(IniciarSesion.this, Login.Error.SHORTPASSWORD, true);
        }else{
            login();
        } 
    }
    
    public void login() throws FontFormatException, IOException{
        try {
            mysql.query("SELECT * FROM usuarios WHERE usuario = '"+user.getText()+"' AND pass='"+(MD5.get(pass.getText()))+"'");
            ResultSet dato = mysql.fetch_assoc();
            if (dato.next()) {
                bye();
                Session.set(dato.getString("hash"));
                switch(Integer.parseInt(f.getIndex("rango", f.usuario()))){
                    case 2 : new Cliente(); break;
                    case 1 : new Tecnico(); break;
                    case 0 : new Administrador(); break;
                }
            }else{
                Config.alert(IniciarSesion.this, "Datos no válidos", true);
            }
        } catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException ex) {

        }
    }
    
    public static void main(String[] args) throws FontFormatException, IOException, ClassNotFoundException, SQLException {
        IniciarSesion l = new IniciarSesion();
        l.validate();
    }
    
    private void bye(){
        dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==10) {
            try {
                submit();
            } catch (FontFormatException | IOException ex) {

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }    
}