package helpdesk;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Logout extends JPanel{
    private JLabel label = new JLabel(":(");
    public Logout(){
        super();
        label.setFont(new Font("Arial",Font.PLAIN,48));
        add(label);
    }
}