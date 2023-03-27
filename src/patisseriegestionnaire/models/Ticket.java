/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patisseriegestionnaire.models;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * Classe représentant un ticket de caisse pour les ventes comptant 
 * sous la forme d'une liste d'element contenant des gateaux et la quantité acheter
 * 
 * @author Romane MALHERBE
 */
public class Ticket extends  ArrayList<ElementdUnTicket>{
  
  // ATTRIBUTS
    
    /**
     * représente le prochain numéro de ticket, il est incrémenter à chaque nouveau ticket
     */
    private static int numTicket =0;
    
    /**
     * le prix de la totalité des article, la somme du prix de chaque elements
     */
    private float prixTotal;
    
  // CONSTRUCTEURS
    
    /**
     * Créer un object ticket avec un prixTotal initial de 0
     */
    public Ticket(){
        super();
        prixTotal = 0;
    }

    /**
     * Méthode qui renvoie le prix total du ticket
     * 
     * @return {@link float} le prix Total du ticket
     */
    public float getPrixTotal() {
        return prixTotal;
    }


    /**
     * Méthode qui met à jour le prix total du ticket en le recalculant à partir de tot les éléments de la liste
     */
    private void majPrixTotal() {
        prixTotal = 0;
        this.forEach((ElementdUnTicket e) -> { // parcour de la liste
            prixTotal+= e.getPrix();
        });
        
    }

    /**
     * Ajout d'un gateau acheter au ticket
     * 
     * @param g {@link Gateau} qu'on veut ajouter 
     * @return {@link boolean} vrai si l'opération c'est bien passé sinon false
     */
    public boolean addGateau(Gateau g) {
        
        // on créé un nouvelle element de ticket à partir du gateau qu'on veut ajouter
        ElementdUnTicket newElmt = new ElementdUnTicket(g); 
        
        // Si il reste bien de ce type de gateau en stocke
        if (g.getQuantite()>0){
            
            // On test si il n'y a pas déjà ce type de gateau dans le ticket
            if (super.contains(newElmt)){
                
                // si il y en a déja on récupérer l'elment déjà présent dans la liste
                ElementdUnTicket elmt = super.get(super.indexOf(newElmt));
                
                // si la quantité acheter ne depasse pas le stocke on ajoute un a la quantité de gateau acheter
                if (elmt.getQuantite()+1<=g.getQuantite()){
                    elmt.add();  
                }else{
                    // sinon on renvoie false comme on a pas pu augmenter le nombre de gateau
                    return false;
                }
            }else{
                // si il n'y avait pas déjà de ce type de gateau dans le ticket on ajoute au ticket un nouvelle element
                super.add(super.size(), newElmt);
            }
            
            // on met a jour le prix total
            majPrixTotal();
            
            // on peut renvoyé true comme l'opération c'est bien passée
            return true;
        } 
        // si il n'y a pas de gateaux en stocke on renvoie false
        return false;
    }
    
    
    /**
     * Méthode permettant de modifier directement une quantité de gateau du ticket
     * 
     * @param index {@link int} emplacement dans la liste de l'élément à modifier
     * @param newQt {@link int} nouvelle quantité
     */
     public void modifierQt(int index,int newQt) {
        
        if (newQt>0){
            
            // si la nouvelle quantité est supérieur à 0 on modifie l'élément du ticket
            this.get(index).setQuantite(newQt);
            
        }else{
            
            // sinon ça veut dire qu'il n'y a plus de ce type de gateau donc on supprime l'element du ticket
            this.remove(index);
        }
        
        // comme une quantité de gateau a changer on met à jour les quantité
        majPrixTotal();
    }
 
 
     /**
      * La méthode imprimer, correspond à l'impression du ticket lors de l'achat
      * 
      * pour le moment elle convertit juste le ticket en un élément de type {@link String}*
      * qui est placé dans un fichier txt
      * 
      */
     public void imprimer(){
        
        String result; // Variable qui contiendra la chaine de caractères final
        
        // Définission d'un format de date
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        Calendar c = Calendar.getInstance(); // On récupère la date et heure actuelle
        
        // Au debut de la chaine de caractère on met l'en tête avec la date et le lieu
        result = "DATE : "+ format.format(c.getTime());
        result += "\nLIEU : Patisserie - La cour aux délices";
        result += "\n\n ARTICLE(S) :";
        
        // on parcours la liste et pour chaque element on écrit le nom du gateau la quantité et le prix au quelle ça revient
        for (ElementdUnTicket e : this){
            result+= "\n - "+e.getGateau().getNom();
            result+= "\n   "+e.getGateau().getPrix()+ " x "+e.getQuantite()+" = "+e.getPrix();
        }
        
        // A la fin on indique le prix total de tout les article (ce qui vas être payer)
        result += "\n\n===============\nTOTAL : "+this.prixTotal+"\n===============";
        
        // On créer un fichier avec pour nom la date du jour et le numéro de ticket
        format = new SimpleDateFormat("yy-MM-dd");
        String nomFichier = format.format(c.getTime())+"_"+"Ticket-"+numTicket+".txt";
        
        // comme un nouveau ticket vient d'être créer un incrément la variable static numTicket
        numTicket++;
        File f = new File(nomFichier);
        
        try {
            // on écrit dans le fichier la chaine de caractère qu'on a créer
            try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter(f))) {
                bw.write(result);
                bw.flush();
                bw.close();
            }
        } catch (java.io.IOException ex) {
        
                JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement dans le fichier"+f.getName(), "Erreur", JOptionPane.ERROR_MESSAGE);
            
        }
     }
     
    
    
}
