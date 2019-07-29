package helpdesk;

import helpdesk.Lang.Spanish.Home;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main extends JFrame implements ActionListener{
    
    private static JPanel menu, main;    
    private static JButton [] boton;    
    private static JPanel [] panel;
    
    public final void init(){
        main = new JPanel(new BorderLayout());
        menu = new JPanel(new GridLayout(5, 1)); 
        boton = new JButton[5];
        panel = new JPanel[4];
        
        boton[0] = new JButton(Home.TICKETS);
        boton[1] = new JButton(Home.NEW_TICKET);
        boton[2] = new JButton(Home.FAQS);
        boton[3] = new JButton(Home.DATA);
        boton[4] = new JButton(Home.LOGOUT);          
        panel[0] = new Tickets(); 
        panel[1] = new NewTicket();
        panel[2] = new FAQ();
        panel[3] = new Datos();
    }
    
    public Main(){
        
        super(Home.TITLE);
        Config.apariencia();
        setSize(720,480);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        init();
        
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
    
    public void actionPerformed(ActionEvent e){
        switch(e.getActionCommand()){
            case Home.TICKETS: mostrar(panel[0]); break;
            case Home.NEW_TICKET: mostrar(panel[1]); break;
            case Home.FAQS: mostrar(panel[2]); break;
            case Home.DATA: mostrar(panel[3]); break;
            case Home.LOGOUT: salir(); break;
        }
    }
    
    public void salir(){
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro que deseas cerrar sesión?", "Cerrar sesión", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null);
        if(respuesta==0){
            System.exit(0);
        }
    }
    
    //método para mostrar el panel seleccionado, oculta los demás
    private void mostrar(JPanel panel){
        for(JPanel pan : this.panel){
            pan.setVisible(false);
        }
        panel.setVisible(true);
    }
    
    public static void main(String[] args) {
        new Main();
    }
    
}