/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpdesk;

import javax.swing.JOptionPane;

/**
 *
 * @author Piro
 */
public class NewClass {
    public static void main(String[] args) {
        String [] opciones ={"uno","dos","tres"}; 
        String x = JOptionPane.showInputDialog(null, "Seleccionar una opción", "Título", JOptionPane.PLAIN_MESSAGE,null, opciones, opciones[1]).toString();
        switch(x){
            case "uno": System.out.println("Opción 1"); break;
            case "dos": System.out.println("Opción 2"); break;
            case "tres": System.out.println("Opción 3"); break;            
        }
    }
}
