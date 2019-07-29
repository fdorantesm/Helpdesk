package helpdesk;

import helpdesk.Lang.Spanish.Data;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
public class Datos extends JPanel implements ActionListener, ItemListener{
    
    private JTextField [] campo;
    private JLabel [] etiqueta;
    private JComboBox[] bday;
    private JRadioButton [] sexo;
    private ButtonGroup opciones = new ButtonGroup();
    private JPanel bottom;
    private JLabel mensaje;
    private Fuente fuente;
    private JButton guardar;
    private MySQL mysql;
    private Funciones f;
    private String genero;
    
    public Datos() throws FontFormatException, IOException, ClassNotFoundException, SQLException{
        super();
        setLayout(null);
        mysql = new MySQL();
        mysql.connect(Config.host, Config.base, Config.user, Config.pass);
        f = new Funciones();
        campo = new JTextField[5];
        etiqueta = new JLabel[7];
        bday = new JComboBox[3];
        sexo = new JRadioButton[2];
        bottom = new JPanel();
        mensaje = new JLabel();
        sexo[0] = new JRadioButton(Data.MEN);
        sexo[1] = new JRadioButton(Data.WOMEN);
        fuente = new Fuente();
        guardar = new JButton(Data.SAVE);
        genero = f.getIndex("sexo", f.usuario());
       
        for (JRadioButton sexo1 : sexo) {
            opciones.add(sexo1);
            add(sexo1);
        }
        
        for(int i=0; i<campo.length; i++) {
            campo[i] = new JTextField();            
        }
        
        for(int i=0; i<etiqueta.length; i++){
            etiqueta[i] = new JLabel();
        }
        
        for(int i = 0; i<bday.length; i++) {
           bday[i] = new JComboBox();
        }
        
        etiqueta[0].setText(Data.NAME+":");
        etiqueta[0].setBounds(10, 10, 598, 20);
        add(etiqueta[0]);
        
        campo[0].setText(f.getIndex("nombre", f.usuario()));
        campo[0].setBounds(10, 30, 598, 35);
        add(campo[0]);
        
        etiqueta[1].setText(Data.USER+":");
        etiqueta[1].setBounds(10, 65, 598, 20);
        add(etiqueta[1]);
        
        campo[1].setEnabled(false);
        campo[1].setText(f.getIndex("usuario", f.usuario()));
        campo[1].setBounds(10, 85, 598, 35);
        add(campo[1]);
        
        etiqueta[2].setText(Data.EMAIL+":");
        etiqueta[2].setBounds(10, 120, 598, 20);
        add(etiqueta[2]);
        
        campo[2].setText(f.getIndex("email", f.usuario()));
        campo[2].setBounds(10, 140, 598, 35);
        add(campo[2]);
        
        etiqueta[3].setText(Data.BIRTHDAY);
        etiqueta[3].setBounds(10, 175, 598, 20);
        add(etiqueta[3]);
        
        bday[0].addItem(Data.DAY);
        bday[0].setBounds(10, 195, 50, 35);
        add(bday[0]);
        for (int i = 1; i < 32; i++) {
            bday[0].addItem(i);
        }
        
        String mes [] ={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
        
        bday[1].addItem(Data.MONTH);
        bday[1].setBounds(70, 195, 80, 35);
        add(bday[1]);
        for (int i=0; i < 12; i++) {
            bday[1].addItem(mes[i]);
        }
        
        bday[2].addItem(Data.YEAR);
        bday[2].setBounds(160, 195, 60, 35);
        add(bday[2]);
        for (int i = 1996; i >= 1900; i--) {
            bday[2].addItem(i);
        }
        
        String nacimiento = f.getIndex("nacimiento", f.usuario());
        
        
        bday[1].setSelectedIndex(Integer.parseInt(nacimiento.substring(5, 7)));
        bday[0].setSelectedIndex(Integer.parseInt(nacimiento.substring(8,10)));
        bday[2].setSelectedItem(Integer.parseInt(nacimiento.substring(0,4)));
        
        etiqueta[4] = new JLabel(Data.GENDER+":");
        etiqueta[4].setBounds(10, 245, 50, 20);
        
        sexo[0].setBounds(60, 245, 80, 20);
        sexo[0].setBorderPainted(false);
        sexo[0].setFocusable(false);
        sexo[1].setFocusable(false);
        sexo[1].setBorderPainted(false);
        sexo[1].setBounds(140, 245, 80, 20);
                
        if(null != f.getIndex("sexo", f.usuario()))
            switch (f.getIndex("sexo", f.usuario())) {
            case "m":
                sexo[0].setSelected(true);
                break;
            case "f":
                sexo[1].setSelected(true);
                break;
        }
        
        add(etiqueta[4]);
        
        etiqueta[5].setText(Data.ADDRESS+":");
        etiqueta[5].setBounds(10, 270, 598, 20);
        add(etiqueta[5]);
        
        campo[3].setText(getAddr());
        campo[3].setBounds(10, 290, 598, 35);
        add(campo[3]);
        
        etiqueta[6].setText("RFC:");        
        etiqueta[6].setBounds(10, 325, 598, 20);
        add(etiqueta[6]);
        
        campo[4].setText(f.getIndex("RFC", f.usuario()));
        campo[4].setEnabled(false);
        campo[4].setBounds(10, 345, 598, 35);
        add(campo[4]);
        
        bottom.setLayout(null);
        mensaje.setFont(fuente.BebasNeue(16,Font.PLAIN));
        mensaje.setBounds(5, 10, 598, 32);
        bottom.setBounds(10, 390, 498, 55);
        bottom.setVisible(false);
        
        guardar.setBounds(520, 400, 90, 35);
        guardar.setFocusPainted(false);
        guardar.setFocusable(false);
        guardar.setBorderPainted(false);
        
        bottom.add(mensaje);
        add(guardar);
        add(bottom);
        
        guardar.addActionListener(this);
        sexo[0].addItemListener(this);
        sexo[1].addItemListener(this);
        validate();
        
    }
    
    public void mensaje(String mensaje,boolean error){
        bottom.setBackground(error?Color.red:Color.green);
        this.mensaje.setText(mensaje);
        bottom.setVisible(true);
    }
    
    public final String getAddr() throws SQLException, ClassNotFoundException{
        return ("".equals(f.getIndex("domicilio", f.usuario())))?Data.ADDR_TIP:f.getIndex("domicilio", f.usuario());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==guardar) {
            try {
                validar();
            } catch (SQLException | ClassNotFoundException | InterruptedException ex) {

            }
        }
    }
    
    public void validar() throws SQLException, ClassNotFoundException, InterruptedException{
        if ("".equals(campo[0].getText())){
            mensaje(Data.Error.NULL_NAME,true);
        }else if(!Validaciones.nombre(campo[0].getText())){
            mensaje(Data.Error.INVALID_NAME,true);
        }else if("".equals(campo[2].getText())){
            mensaje(Data.Error.NULL_EMAIL,true);
        }else if(!Validaciones.email(campo[2].getText())){
            mensaje(Data.Error.INVALID_EMAIL,true);
        }else if("".equals(campo[3].getText()) || campo[3].getText().equals(Data.ADDR_TIP)){
            mensaje(Data.Error.NULL_ADDRESS,true);
        }else if(!Validaciones.fecha(""+bday[2].getSelectedItem()+"-"+bday[1].getSelectedIndex()+"-"+bday[0].getSelectedIndex()+"")){
            mensaje(Data.Error.INVALID_DATE,true);
        }else if(!sexo[0].isSelected() && !sexo[1].isSelected()){
            mensaje(Data.Error.NULL_GENDER,true);
        }else{
            actualizar();
        } 
    }
    

    private void actualizar() throws SQLException, ClassNotFoundException, InterruptedException {
        mysql.query("UPDATE usuarios SET nombre='"+campo[0].getText()+"', email='"+campo[2].getText()+"', nacimiento='"+bday[2].getSelectedItem()+"-"+bday[1].getSelectedIndex()+"-"+bday[0].getSelectedIndex()+"', sexo='"+genero+"', domicilio='"+campo[3].getText()+"' WHERE id='"+f.getIndex("id", f.usuario())+"'",true);
        mensaje("Los cambios fueron guardados correctamente.",false);
        Timer t = new Timer(5000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                bottom.setVisible(false);
            }            
        });
        t.start();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource()==sexo[0]){
            genero = "m";
        }else if(e.getSource()==sexo[1]){
            genero = "f";
        }
    }
}