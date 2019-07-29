package helpdesk;

import helpdesk.Lang.Spanish.*;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public abstract class Config {    
    
    //Nombre del programa
    public static final String cfg_name="Helpdesk";   
    
    //Datos de conexión a la base de datos
    public static final String user ="root";
    public static final String pass ="";
    public static final String base ="helpdesk";
    public static final String host ="localhost";
    public static final MySQL mysql = new MySQL();
    
    public static final Font cfg_logo(int size){ 
        return new Font("Bebas Neue",Font.PLAIN,size);
    }
    
    public static final void conexion() throws ClassNotFoundException, SQLException{
        Connection connect = mysql.connect(host,base,user, pass);
    }
    
    public static final void apariencia(){
        try{
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex){
            
        }
    }
    
    
    
    public static void alert(Component parent, String mensaje, boolean error){
        JOptionPane.showMessageDialog(parent, mensaje, error ? Functions.ERROR : Functions.OK, error ? 0 : 1);
    }
    
    public static int confirm(Component parent,String mensaje, String titulo, int opciones, int tipo){
        return JOptionPane.showConfirmDialog(parent, mensaje, titulo, opciones, tipo, null);
    }
    
}