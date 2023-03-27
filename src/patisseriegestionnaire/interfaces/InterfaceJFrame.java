/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patisseriegestionnaire.interfaces;

import java.util.ArrayList;
import patisseriegestionnaire.models.Commande;
import patisseriegestionnaire.models.CommandePersonnalise;
import patisseriegestionnaire.models.Evenement;
import patisseriegestionnaire.models.Gateau;
import patisseriegestionnaire.models.Ticket;

/**
 * Tout les méthodes que la {@link patisseriegestionnaire.ui.mainJFrame} peut appeler pour charger, modifier et supprimer des données
 * 
 * @author Romane MAlherbe
 */
public interface InterfaceJFrame {
    
    public boolean addGateau(patisseriegestionnaire.models.Gateau g);

    public boolean removeGateau(Gateau gateau);

    public boolean removeCommandePerso(CommandePersonnalise commande);

    public boolean validerCommandePerso(CommandePersonnalise commande);
    
    public ArrayList<Commande> getAllCommandes();
    
    public ArrayList<Gateau> getAllGateau();
    
    public ArrayList<CommandePersonnalise> getAllCommandesPerso();
    
    public void fermeture();

    public boolean modifyGateau(Gateau g);


    public boolean restockerGateau(Gateau gateau, int reponse);

    public boolean validerCommande(Commande commande);
    
    public boolean validerGateauCommande(Commande commande);

    public boolean ajouterCommandeComptoire(Ticket ticket);
    
    

    public ArrayList<Evenement> getListEvenement(int mois, int annee);

    public boolean ajoutEvenement(Evenement e);

    public boolean ajoutModificationEvmt(Evenement e);

    public boolean supprimerEvenement(Evenement e);
    
   
}
