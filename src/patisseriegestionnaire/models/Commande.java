/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patisseriegestionnaire.models;




    
/**
 *
 * La classe Commande représente les commandes en click and collect
 * 
 * Si une commande contient plusieur type de gateaux alors il y aura plusieurs
 * objet commande avec le même id mais pas les mêmes type de gateaux
 * 
 * @author Romane Malherbe
 */
public class Commande {
   
  // ATTRIBUTS 
    
    /**
     * Id de la commande 
     */
    private int id;
    
    /**
     * Type de gateau de la commande
     */
    private Gateau gateau;
    
    /**
     * Quantité de gateau dans la commande
     */
    private int quantite;
    
    /**
     * Total du prix pour le nombre de ceux type de gatteau
     */
    private float prix;
    
    /**
     * Client qui vas récupérer la commande
     */
    private Client client;
    
    /**
     * Date à laquelle le client vient récupérer ça commande
     */
    private String dtRecup;
    
    /**
     * Etat de la commande général("0" ou "1")
     */
    private String etat;
    
    /**
     * boolean signifiant si les gateau de la commande sont pret 
     */
    private boolean ready;

  // CONSTRUCTEUR
    
    /**
     * Creer un Object Commande
     * 
     * @param id {@link int} - Identifiant de la commande
     * @param id_gat {@link int} - Identifiant du Gateau de la commande
     * @param nomGateau {@link String} - Nom du gateau
     * @param quantite {@link int} - Quantité de gateau commander
     * @param prix {@link float} - Prix de tout les gateaux
     * @param nomClt {@link String} - Nom du client qui a passer la commande
     * @param prenomClt {@link String} - Prenom du client qui a passer la commande
     * @param dtRecup {@link String} - Date à laquelle le client vient récupérer ça commande
     * @param etat {@link String} - L'état général de la commande
     * @param ready {@link boolean} - si les gateau sont pret a être servi
     */
    public Commande(int id, int id_gat, String nomGateau, int quantite, float prix, String nomClt, String prenomClt, String dtRecup, String etat, boolean ready) {
        this.id = id;
        this.gateau = new Gateau(id_gat,nomGateau);
        this.quantite = quantite;
        this.prix = prix;
        this.client = new Client(nomClt,prenomClt);
        this.dtRecup = dtRecup;
        this.etat = etat;
        this.ready = ready;
    }
 

  // GETTERS et SETTERS
    
    public int getGat_id() {
        return gateau.getId();
    }

    public int getId() {
        return id;
    }
 
    public String getNomGateaux() {
        return gateau.getNom();
    }

    public int getQuantite() {
        return quantite;
     }

    public float getPrix() {
        return prix;
    }
    
    public String getNomClient() {
        return client.getNom();
    }

    public String getPrenomClient() {
        return client.getPrenom();
    }

    public String getdtRecuperer() {
        return dtRecup;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean value) {
        ready = value;
    }
    
    public boolean isLivree(){
        return etat.equals("0");
    }
    
    public void setLivree(){
        etat = "0";
    }
    
    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", nomGateau=" + gateau.getNom() + ", quantite=" + quantite + ", prix=" + prix + ", nomClt=" + client.getNom() + ", prenomClt=" + client.getPrenom() + ", dtRecup=" + dtRecup + ", ready=" + ready + '}';
    }
    
    /**
     * Permet de retourné un boolean en fonction d'un entier pour savoir si une commande est prête 
     * 
     * @param e {@link int} 
     * @return {@link boolean}
     */
    public static boolean isReady(int e){
        return e!=0;
    }
    
}
