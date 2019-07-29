package helpdesk;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class Funciones {
    
    MySQL mysql = new MySQL();
    
    public Funciones() throws ClassNotFoundException, SQLException{
        mysql.connect(Config.host, Config.base, Config.user, Config.pass);
    }
    
    public ResultSet usuario() throws ClassNotFoundException, SQLException{
        mysql.query("SELECT * FROM usuarios WHERE hash='"+Session.get()+"'");
        return mysql.fetch_assoc();
    }
    
    public String getIndex(String index, ResultSet data) throws SQLException{
        if (data.next()) {
            return data.getString(index);
        }
        return null;
    }
    
    public String status(String data){
        String c = null;
        switch(Integer.parseInt(data)){
            case 0: c="Cerrado"; break;
            case 1: c="Abierto"; break;
            case 2: c="Esperando respuesta del cliente"; break;
            case 3: c="Esperando respuesta del técnico"; break;
        }
        return c;
    }
    
    /*public Map<String,String> getArrayList(ResultSet data) throws SQLException{
        Map<String,String> s = new HashMap();
        if (data.next()) {
            s.put("id", data.getString("id"));
            s.put("usuario",data.getString("usuario"));
        }
        
        return s;
    }*/   
    
    
}