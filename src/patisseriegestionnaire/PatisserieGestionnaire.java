
package patisseriegestionnaire;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import patisseriegestionnaire.ui.mainJFrame;

/**
 * Classe principale qui génére la JFrame et l'ouvre
 * 
 * @author Romane Malherbe
 */
public class PatisserieGestionnaire {

    // Adresse base de données
    private static final String URL = "jdbc:mysql://localhost/sae";
    private static final String USER = "root";
    private static final String MDP = "";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            /** Object qui fait la relation avec la base de donnée il contient toute les méthodes qu'on pourrait appeler pour chercher
             * des information, en ajouter, en supprimer ou en modifier */
            patisseriegestionnaire.jdbc.PatisserieBD bd = patisseriegestionnaire.jdbc.PatisserieBD.getInstance(URL,USER,MDP);
            
            // Création de la fenêtre principale
            (new mainJFrame(bd)).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(PatisserieGestionnaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
