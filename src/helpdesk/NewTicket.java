package helpdesk;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

class NewTicket extends JPanel implements ActionListener, FocusListener{
    
    private JTextField asunto;
    private JRadioButton alta;
    private JRadioButton normal;
    private JRadioButton baja;
    private JComboBox categoria;
    private ButtonGroup grupo;
    private JLabel prioridad_label;
    private JLabel mensaje;
    private JTextArea descripcion;
    private JScrollPane scroll;
    private JButton enviar;
    private JButton tip;
    private static MySQL mysql;
    private JPanel bottom;
    private Fuente fuente;

    public NewTicket() throws ClassNotFoundException, SQLException, FontFormatException, IOException{
        Config.apariencia();
        setLayout(null);

        mysql = new MySQL();
        mysql.connect("localhost", "helpdesk", "root", "");
        this.fuente = new Fuente();

        this.baja = new JRadioButton("Baja");
        this.normal = new JRadioButton("Normal");
        this.alta = new JRadioButton("Alta");
        this.categoria = new JComboBox(cats());
        this.grupo = new ButtonGroup();
        this.descripcion = new JTextArea("Escriba el detalle del problema, sea descriptivo y claro por favor.");
        this.scroll = new JScrollPane(this.descripcion);
        this.asunto = new JTextField("Asunto");
        this.prioridad_label = new JLabel("Prioridad:");
        this.tip = new JButton();

        this.tip.setIcon(new ImageIcon(getClass().getResource("images/i.gif")));
        this.tip.setBorderPainted(false);
        this.tip.setContentAreaFilled(false);
        this.tip.setFocusPainted(false);
        this.tip.setBorder(null);
        this.tip.setBounds(595, 47, 18, 18);
        this.tip.setToolTipText("Antes de crear un ticket, te recomendamos leer las FAQ's, posiblemente ahí encuentres una solución más rápido.");

        this.asunto.setBounds(10, 10, 490, 30);
        this.asunto.setForeground(new Color(0, 0, 0, 140));
        this.asunto.setFont(new Font("Verdana", 0, 18));

        this.descripcion.setForeground(new Color(0, 0, 0, 240));
        this.descripcion.setFont(new Font("Verdana", 0, 14));
        this.descripcion.setLineWrap(true);
        this.descripcion.setWrapStyleWord(true);

        this.categoria.setBounds(505, 11, 110, 28);
        this.categoria.setBorder(null);

        this.prioridad_label.setBounds(10, 45, 60, 20);

        this.alta.setBounds(60, 45, 47, 20);
        this.normal.setBounds(120, 45, 67, 20);
        this.baja.setBounds(185, 45, 50, 20);

        this.alta.setFocusPainted(false);
        this.normal.setFocusPainted(false);
        this.baja.setFocusPainted(false);

        this.grupo.add(this.alta);
        this.grupo.add(this.normal);
        this.grupo.add(this.baja);

        this.normal.setSelected(true);
        this.scroll.setBounds(10, 70, 605, 310);

        this.mensaje = new JLabel();
        this.mensaje.setFont(this.fuente.BebasNeue(16, 0));
        this.mensaje.setBounds(5, 10, 598, 32);

        this.enviar = new JButton("Enviar");
        this.enviar.setBounds(516, 400, 100, 35);
        this.enviar.setFocusPainted(false);

        this.bottom = new JPanel(null);
        this.bottom.setBounds(10, 390, 498, 55);
        this.bottom.add(this.mensaje);
        this.bottom.setVisible(false);

        add(this.bottom);
        add(this.prioridad_label);
        add(this.alta);
        add(this.normal);
        add(this.baja);
        add(this.enviar);
        add(this.categoria);
        add(this.asunto);
        add(this.scroll);
        add(this.tip);

        this.asunto.addFocusListener(this);
        this.descripcion.addFocusListener(this);
        this.alta.addActionListener(this);
        this.enviar.addActionListener(this);
        this.tip.addActionListener(this);    
    }

    @Override
    public void actionPerformed(ActionEvent e){
      if (e.getSource() == this.tip) {
        Config.alert(null, "Antes de crear un ticket, te recomendamos leer las FAQ's, posiblemente ahí encuentres una solución más rápido.", false);
      }
      else if (e.getSource() == this.alta) {
        int u = Config.confirm(this, Lang.Spanish.TicketForm.Priority.CONFIRMATION, Lang.Spanish.TicketForm.Priority.CONFIRMATION_TITLE, 0, -1);
        if (u == 0)
          this.alta.setSelected(true);
        else {
          this.normal.setSelected(true);
        }
      }
      else if (e.getSource() == this.enviar) {
        try {
          validar();
        }
        catch (SQLException ex) {
        }
      }
    }

    public final Object[] cats() throws ClassNotFoundException, SQLException {
      mysql.query("SELECT * FROM categorias");
      ResultSet dato = mysql.fetch_assoc();
      ArrayList s = new ArrayList();
      s.add(Lang.Spanish.TicketForm.BLANK_CATEGORY);
      while (dato.next()) {
        s.add(dato.getString("nombre"));
      }
      return s.toArray();
    }

    @Override
    public void focusGained(FocusEvent e)
    {
      if (e.getSource() == this.asunto) {
        if (Lang.Spanish.TicketForm.TOPIC.equals(this.asunto.getText())) {
          this.asunto.setText("");
        }
      }
      else if ((e.getSource() == this.descripcion) && (Lang.Spanish.TicketForm.CONTENT_TIP.equals(this.descripcion.getText())))
        this.descripcion.setText("");
    }

    @Override
    public void focusLost(FocusEvent e){
        if (e.getSource() == this.asunto) {
          if ("".equals(this.asunto.getText()))
            this.asunto.setText(Lang.Spanish.TicketForm.TOPIC);
        }
        else if ((e.getSource() == this.descripcion) && ("".equals(this.descripcion.getText())))
              this.descripcion.setText(Lang.Spanish.TicketForm.CONTENT_TIP);
    }

    public void mensaje(String mensaje,boolean error){
        bottom.setBackground(error?Color.red:Color.green);
        this.mensaje.setText(mensaje);
        bottom.setVisible(true);
    }

    private void validar() throws SQLException {
        if ("".equals(this.asunto.getText()))
          mensaje(Lang.Spanish.TicketForm.Error.NULL_TOPIC, true);
        else if (!Validaciones.asunto(this.asunto.getText()))
          mensaje(Lang.Spanish.TicketForm.Error.INVALID_TOPIC, true);
        else if (("".equals(this.descripcion.getText())) || (Lang.Spanish.TicketForm.CONTENT_TIP.equals(this.descripcion.getText())))
          mensaje(Lang.Spanish.TicketForm.Error.NULL_CONTENT, true);
        else if (this.categoria.getSelectedIndex() == 0)
          mensaje(Lang.Spanish.TicketForm.Error.NULL_CATEGORY, true);
        else
          agregar();
    }

    private void agregar() throws SQLException{
        //mysql.query("INSERT INTO tickets (asunto, categoria, prioridad, descripcion) VALUES ('" + this.asunto.getText() + "', '" + (this.categoria.getSelectedIndex())+ "', '" + getPrio() + "', '" + this.descripcion.getText() + "')", true);
        System.out.println("INSERT INTO tickets (asunto, categoria, prioridad, descripcion) VALUES ('" + this.asunto.getText() + "', '" + (this.categoria.getSelectedIndex())+ "', '" + getPrio() + "', '" + this.descripcion.getText() + "')");
        //mensaje("El ticket ha sido agregado, en breve recibirá una respuesta", false);
        Timer t = new Timer(5000, new ActionListener()
        {
          @Override
          public void actionPerformed(ActionEvent e) {
            NewTicket.this.bottom.setVisible(false);
          }
        });
        t.start();
        reset();
    }

    private void reset() {
        this.asunto.setText(Lang.Spanish.TicketForm.TOPIC);
        this.descripcion.setText(Lang.Spanish.TicketForm.CONTENT_TIP);
        this.categoria.setSelectedIndex(0);
        this.normal.setSelected(true);
    }

    private String getPrio() {
        String c = "";
        if (this.alta.isSelected())
          c = "0";
        else if (this.normal.isSelected())
          c = "1";
        else if (this.baja.isSelected()) {
          c = "2";
        }
        return c;
    }
}