/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpdesk;

import java.awt.FontFormatException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MySQLTest{
   
    
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, FontFormatException, IOException {
        ResultSet dato;
        MySQL mysql = new MySQL();
        mysql.connect("localhost", "helpdesk", "root", "");
        
        Funciones f = new Funciones();
        
        //mysql.query("select * from tickets where cliente_id=3");
        mysql.query("SELECT * FROM tickets WHERE cliente_id='"+f.getIndex("id", f.usuario())+"'");
        dato = mysql.fetch_assoc();
        ArrayList<JTicket> data = new ArrayList<>();
        while(dato.next()) {
            data.add(new JTicket(dato.getString("asunto"),dato.getString("fecha"),dato.getString("categoria"),dato.getString("status")));
        }
        

//System.out.println(mysql.num_rows());
        
//        System.out.println(f.getIndex("id", f.usuario()));
        
        /*mysql.query("SELECT * FROM usuarios WHERE id=1");
        dato = mysql.fetch_assoc();
        
        System.out.println(f.getIndex("hash", dato));
        */
//        while(dato.next()) {
//            for (int i = 1; i <= 12; i++) {
//                System.out.println(dato.getString(i));
//            }
//            
//        }
//        
        
//        Funciones f = new Funciones();
//        
//        Map<String,String> s = (HashMap) f.getArrayList(f.usuario());
//        
//        System.out.println(s.get("usuario"));
        
        
        //System.out.println(f.getIndex("nombre",f.usuario()));
        
        
        
//        mysql.query("SELECT usuario FROM usuarios WHERE hash='"+Session.get()+"'");
//        dato = mysql.fetch_assoc();
//        if(dato.next()){
//            System.out.println(dato.getString("usuario"));
//        }
        

//        String nombre;
//        ArrayList<String> cats = new ArrayList<>();
//        
//        
//        MySQL mysql = new MySQL();
//        mysql.connect("localhost", "helpdesk", "root", "");
//        
//        mysql.query("SELECT * FROM `categorias` ");        
//        dato = mysql.fetch_assoc();
//        
//        while (dato.next()) {            
//            cats.add(dato.getString("nombre"));
//        }
//        
//       
//        
//        Object [] lol = cats.toArray();
//        
//        for (Object string : lol) {
//            System.out.println(string);
//        }
        
        
        
        
//        Funciones f = new Funciones();        
//        
//        mysql.query("SELECT * FROM usuarios WHERE usuario = 'fdorantes' AND pass = '"+f.md5("12345678")+"'");
//        dato = mysql.fetch_assoc();
//
//        if(dato.next()){
//            System.out.println("Bienvenido");
//        }else{
//            System.out.println("Datos no válidos");
//        }
        
        
/*        mysql.query("DELETE FROM nombres",true);
        dato = mysql.fetch_assoc();
        System.out.println(dato);
*/
        
        
//        mysql.query("SELECT * FROM nombres");
//        dato = mysql.fetch_assoc();
//        
//        System.out.println(mysql.num_rows()+" registros");
        
        
        
//        mysql.query("SELECT * FROM nombres");
//        dato = mysql.fetch_assoc();
//       
//        
//        
//        if (!dato.next()) {
//            System.out.println("No hay registros");
//        }else{
//            while(dato.next()){
//                System.out.println(dato.getString("nombre"));
//                
//            }
//        }
        
        
        
       
//        Scanner leer = new Scanner(System.in);
//        System.out.println("Numero de nombres a ingresar:");
//        System.out.println("Ingresar nombres:");
//        int n = leer.nextInt();
//        for(int i=0; i<n; i++){
//            nombre = leer.next();
//            mysql.query("insert into nombres (`nombre`) values ('"+nombre+"')",true);
//        }
//        
        
        
//        mysql.select("SELECT * FROM nombre");        
//        dato = mysql.fetch_assoc();
//        
//        while (dato.next()) {            
//            System.out.println(dato.getString("nombre"));
//        }
        
    }
}
