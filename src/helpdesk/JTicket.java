package helpdesk;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class JTicket extends JPanel{
    
    private JLabel label[];
    private JButton accion[];
    private JPanel seccion[];
    
    public JTicket(String titulo, String fecha, String tipo, String status) throws FontFormatException, IOException{
        super(null);
        
        label = new JLabel[4];
        
        for(int i=0; i<label.length; i++){
            label[i] = new JLabel();
        }
        
        accion = new JButton[3];
        
        for (JButton boton : accion) {
            boton = new JButton();
        }
        
        seccion = new JPanel[3];
        
        for (JPanel pan : seccion) {
            pan = new JPanel();
        }
        
        label[0].setText(titulo);
        label[0].setFont(new Fuente().BebasNeue(30, Font.PLAIN));
        label[1].setText(fecha);
        label[2].setText(tipo);
        //label[3].setIcon(getClass().getResource(""));
        
        label[0].setBounds(10, 5, 540, 30);
        label[1].setBounds(10,35,120,15);
        label[2].setBounds(10,50,120,15);
        
        for (JLabel jLabel : label) {
            add(jLabel);
        }
        
    }
    
   /* public static void main(String[] args) throws FontFormatException, IOException {
        JFrame frame = new JFrame();
        JPanel cont = new JPanel(null);
        JScrollPane scroll = new JScrollPane(cont);
        frame.setSize(720,480);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
        JTicket [] ticket = new JTicket[10];
        for(int i=0; i<ticket.length; i++){
            ticket[i] = new JTicket("hola", "mundo", "1234", "asd");
            ticket[i].setBounds(90, (70*i), 618, 70);
            ticket[i].setBackground(Color.red);
            frame.add(ticket[i]);
            
        }
        frame.repaint();
        
    }*/
}