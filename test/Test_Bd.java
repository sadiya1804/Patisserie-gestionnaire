/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import patisseriegestionnaire.jdbc.PatisserieBD;
import patisseriegestionnaire.models.Commande;
import patisseriegestionnaire.models.CommandePersonnalise;
import patisseriegestionnaire.models.Gateau;

/**
 *
 * @author roman
 */
public class Test_Bd {
    private static String URL = "jdbc:oracle:thin:@iutdoua-ora.univ-lyon1.fr:1521:cdb1";
    private static String USER = "p2100657";
    private static String MDP= "614017";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
//        PatisserieBD bd = new PatisserieBD();
//        
//        bd.removeGateau(5);
//        Gateau gat = new Gateau("éclaire au chocolat","Chout avec du chocolat","img2.png",3.6f,60);
//        bd.addGateau(gat);
//        for (Gateau g:bd.getAllGateau()){
//            System.out.println(g);
//        }
//        gat.setLienImg("Image_Eclaire.png");
//        gat.setPrix(3.99f);
//        bd.modifyGateau(gat);
//        for (CommandePersonnalise g:bd.getAllCommandesPerso()){
//            System.out.println(g);
//        }
//        
//        for (Commande c:bd.getAllCommandes()){
//            System.out.println(c);
//        }
//        
//        
//        bd.fermeture();
        
       
    }
    
}
