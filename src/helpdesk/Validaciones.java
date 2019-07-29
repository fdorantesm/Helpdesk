package helpdesk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Validaciones {
    
    public static boolean usuario(String uname){
        Pattern patron = Pattern.compile("^[a-zA-Z]+$");
        Matcher coincidencia = patron.matcher(uname);
        return coincidencia.find();
    }
    
    public static boolean nombre(String nombre){
        Pattern patron = Pattern.compile("^([a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+)([a-zA-Z áéíóúÁÉÍÓÚñÑüÜ])*([a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+)$");
        Matcher coincidencia = patron.matcher(nombre);
        return coincidencia.find();
    }
    
    public static boolean contrasena(String pass){
        Pattern patron = Pattern.compile("^(.+){8,}$");
        Matcher coincidencia = patron.matcher(pass);
        return coincidencia.find();
    }
    
    public static boolean ip(String ip){
        Pattern patron = Pattern.compile("^$");
        Matcher coincidencia = patron.matcher(ip);
        return coincidencia.find();
    }
    
    public static boolean email(String email){
        Pattern patron = Pattern.compile("^([a-zA-Z0-9](.|_|-)[a-zA-Z0-9]+)*@(([a-zA-Z0-9]+(-)?)*)(\\.([a-zA-Z0-9]+)+)*$");
        Matcher coincidencia = patron.matcher(email);
        return coincidencia.find();
    }
    
    public static boolean asunto(String asunto){
        Pattern patron = Pattern.compile("^([a-z0-9A-ZáéíóúÁÉÍÓÚÑñ .-])+$");
        Matcher coincidencia = patron.matcher(asunto);
        return coincidencia.find();
    }
    
    public static boolean fecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yy-MM-dd",Locale.getDefault());    
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e){
                return false;
            }    
        return true;
    } 
    
}