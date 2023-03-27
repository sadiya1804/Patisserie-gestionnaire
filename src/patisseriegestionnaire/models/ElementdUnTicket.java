/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patisseriegestionnaire.models;

import java.util.Objects;

/**
 * Classe correspondant à l'élement de Ticket donc un gateau et la quantité acheter
 * 
 * @author Romane Malherbe
 */
public class ElementdUnTicket {
  
  // ATTRIBUTS
    
    /**
     * Le gateau qui vas être acheter
     */
    private Gateau gateau;
    
    /**
     * La quantité de gateau qui vas être acheter (>0)
     */
    private int quantite;
    
  // CONSTRUCTEUR
    
    /**
     * Créer un objet ElementdUnTicket
     * 
     * @param gateau {@link Gateau} qu'on veut acheter
     */
    public ElementdUnTicket(Gateau gateau){
        this.gateau = gateau;
        this.quantite = 1;
    }
    
  // GETTERS & Setter
    
    public int getQuantite(){
        return this.quantite;
    }
    
    /**
     * Calcule et renvoie le prix que coute les gateaux de ce ticket
     * @return {@link float} le prix d'un gateau fois le nombre de gateau
     */
    public float getPrix(){
        return this.gateau.getPrix() * this.quantite;
    }

    public Gateau getGateau() {
        return gateau;
    }

     public void setQuantite(int newQt) {
        this.quantite = newQt;
    }
  
    /**
     * Ajout d'un gateau en plus (on augmente la quantité de gateau acheter)
     */
    public void add(){
        this.quantite++;
    }
    
    /**
     * Méthode pour diminuer la quantiter de gateau de 1
     */
    public void sub(){
        this.quantite--;
    }
    
    
    
    @Override
    public String toString() {
        return "x"+this.quantite+"   "+gateau.getNom();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.gateau);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ElementdUnTicket other = (ElementdUnTicket) obj;
        if (!Objects.equals(this.gateau, other.gateau)) {
            return false;
        }
        return true;
    }

   
    
    
    
}
