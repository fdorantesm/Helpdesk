package helpdesk;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public abstract class Session {
       
    public static boolean set(String hash){
        String roaming = System.getenv("appdata").replace('\\', '/');
        File directorio = new File(roaming+"/HelpDesk");
        directorio.mkdir();       
        try{
            FileWriter config = new FileWriter(directorio+"/helpdesk.dll", false);
            PrintWriter pluma = new PrintWriter(config);
            pluma.println(hash);
            config.close();
            return true;
        }        
        catch (IOException e){

        }
        return false;
    }
    
    public static String get(){
        String roaming = System.getenv("appdata").replace('\\', '/');     
        Scanner leer = null;
        String pass="";
        try {
            leer = new Scanner(new FileReader(roaming+"/HelpDesk/helpdesk.dll"));
            while (leer.hasNextLine()) {
                pass += leer.nextLine();
            }
        
            return pass;
        }catch (Exception e){
            return "";
        }
    
        finally {
            if (leer != null){
                leer.close();
            }
        }
    }
    
    public static boolean destroy(){
       String roaming = System.getenv("appdata").replace('\\', '/');
        File directorio = new File(roaming+"/HelpDesk");
        directorio.mkdir();       
        try{
            FileWriter config = new FileWriter(directorio+"/helpdesk.dll", false);
            PrintWriter pluma = new PrintWriter(config);
            pluma.println();
            config.close();
            return true;
        }        
        catch (IOException e){

        }
        return false;
        
    }
    
}