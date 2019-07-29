package helpdesk;

import java.awt.FontFormatException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Tickets extends JPanel{       
    private MySQL mysql;
    private Funciones f;
    
    public Tickets(String q) throws FontFormatException, IOException, ClassNotFoundException, SQLException{
        super(null);
        
        JPanel listado = new JPanel(null);
        JScrollPane scroll = new JScrollPane(listado);
        scroll.setBounds(0,0,618,452);
        
        mysql = new MySQL();
        f = new Funciones();
        mysql.connect(Config.host, Config.base, Config.user, Config.pass);
        
//        JTicket ticket[] = getTickets();
//        
//        for (int i = 0; i < ticket.length; i++) {
//            listado.add(ticket[i]);
//            ticket[i].setBounds(0, i*70, 618, 70);
//        }
       
        listado.setBounds(0, 0, 618, 452);        
        add(scroll);
    }
    
//    private JTicket[] getTickets() throws ClassNotFoundException, SQLException, FontFormatException, IOException{
//        mysql.query("SELECT * FROM tickets WHERE cliente_id='"+f.getIndex("id", f.usuario())+"'");
//        ResultSet dato = mysql.fetch_assoc();
//        JTicket[] ticket = new JTicket[mysql.num_rows()];
//        for(int i=0; i<ticket.length; i++){
//            ticket[i] = new JTicket(dato.getString("asunto"),dato.getString("fecha"),dato.getString("categoria"),dato.getString("status"));
//        }
//        
//        return ticket;
//    }
    
}