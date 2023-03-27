/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patisseriegestionnaire.ui;

import patisseriegestionnaire.models.Gateau;
import patisseriegestionnaire.models.Ticket;

/**
 *  
 *  Model pour une JList pour afficher un ticket
 * 
 * @author Romane Malherbe
 */
public class JlistModelTicket extends javax.swing.AbstractListModel<String> {

    /**
     * Ticket qui sera afficher dans la JList
     */
    private Ticket ticket;
    
  // CONSTRUCTEUR
    
    /**
     * Créer un object JlistModelTicket en initialise un nouveau Ticket en attribut
     * 
     */
    public JlistModelTicket() {
        this.ticket = new Ticket();
    }

    /**
     * Méthode qui retourne le contenue de l'attribut ticket
     * 
     * @return {@link Ticket} 
     */
    public Ticket getTicket() {
        return ticket;
    }

    
    /**
     * Méthode qui retourne le nombre d'élément dans la liste
     * 
     * @return {@link int} nombre d'élément dans le ticket
     */
    @Override
    public int getSize() {
        return this.ticket.size();
    }

    /**
     * Méthode qui renvoie l'element qui se trouve à l'index en paramètre
     * 
     * @param index {@link int} - position de l'element à retourné
     * @return {@link String} l'element du ticket qui se trouve à l'emplacement de l'index
     */
    @Override
    public String getElementAt(int index) {
        return this.ticket.get(index).toString();
    }
    
    
    /**
     * Méthode qui ajoute un gateau au ticket
     * 
     * @param g {@link Gateau} qu'on veut ajouter
     * @return {@link boolean} - true si l'ajout a bien lieu sinon false
     */
    public boolean ajoutGateau(Gateau g){
        return this.ticket.addGateau(g);
    }
    
    /**
     * Méthode qui retourne le prix total du ticket
     * 
     * @return {@link float} le prix total du tickets
     */
    public float getPrixTotal(){
        return this.ticket.getPrixTotal();
    }
    
    
}
