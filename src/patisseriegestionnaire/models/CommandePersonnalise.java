/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patisseriegestionnaire.models;



import java.util.Date;

/**
 *
 * Classe représentant les commandes de gateaux personnaliser
 * 
 * @author Romane Malherbe
 */
public class CommandePersonnalise {
    
  // ATTRIBUTS
    
    /**
     * Numéro de la commande
     */
    private int num;
    
    /**
     * Forme du gateau
     */
    private String forme;
    
    /**
     * Gout de la viennoise choisit pour le gateau
     */
    private String gout_viennoise;
    
    /**
     * Gout de la crème choisit pour le gateau
     */
    private String gout_creme;
    
    /**
     * Taille du gateau dimension
     */
    private String taille;
    
    /**
     * Le texte choisit pour personnaliser le gateau
     */
    private String texte_perso;
    
    /**
     * Le lien vers l'image choisit pour mettre sur le gateau
     */
    private String lien_img;
    
    /**
     * La date à laquelle le gateau doit être livrée
     */
    private Date dtLivraison;
    
    /**
     * L'heure à laquelle le gateau doit être livré
     */
    private String heureLivraison;
    
    /**
     * Le client qui a passer la commande
     */
    private Client client;
    
    /**
     * Etat de la commande si elle est prête ou pas à être servi ou si elle est supprimer
     */
    private String etat;

  // CONSTRUCTEURS
    
    /**
     * Créer un object Commande personnaliser
     * 
     * @param num {@link int} - Numéro de la commande
     * @param forme {@link String} - Forme du gateau
     * @param gout_viennoise {@link String} - Gout de la viennoise choisit
     * @param gout_creme {@link String} - Gout de la crême choisit
     * @param taille {@link String} - taille du gateau (dimension)
     * @param texte_perso {@link String} - Texte personnaliser qui vas être sur le gateau
     * @param lien_img {@link String} - Lien vers l'image qui sera sur le gateau si il y en a une 
     * @param dtLivraison {@link Date} de la livraison
     * @param heureLivraison {@link String} - heur de la livraison
     * @param numCli {@link int} - Numéro du client qui passe la commande
     * @param etat {@link String} - etat de la commande
     * @param nomClt {@link String} - Nom du client
     * @param prenomClt {@link String} - Prénom du client
     */
    public CommandePersonnalise(int num, String forme, String gout_viennoise, String gout_creme, String taille, String texte_perso, String lien_img, Date dtLivraison, String heureLivraison, int numCli, String etat, String nomClt, String prenomClt) {
        this.num = num;
        this.forme = forme;
        this.gout_viennoise = gout_viennoise;
        this.gout_creme = gout_creme;
        this.taille = taille;
        this.texte_perso = texte_perso;
        this.lien_img = lien_img;
        this.dtLivraison = dtLivraison;
        this.heureLivraison = heureLivraison;
        this.client = new Client(nomClt,prenomClt,numCli);
        this.etat = etat;
    }

  // GETTERs & SETTERs
    
    public int getNum() {
        return num;
    }
    
    public String getForme() {
        return forme;
    }

    public String getGout() {
        return gout_viennoise;
    }

    public String getLien_img() {
        return lien_img;
    }

    public String getNomClt() {
        return client.getNom();
    }

    public String getPrenomClt() {
        return client.getPrenom();
    }
    
    public String getDtEtHeureRecuperation(){
        return dtLivraison.toString();
    }

    public boolean isPret() {
        return etat.equals("livre");
    }
    
    public boolean isDeleted(){
        return etat.equals("supprimer");
    }

    public void setPret(boolean pret) {
        this.etat = (pret)? "livre":"validee";
    }
    
    public void setDeleted() {
        this.etat = "supprimer";
    }

    public String getGout_viennoise() {
        return gout_viennoise;
    }

    public String getGout_creme() {
        return gout_creme;
    }

    public String getTaille() {
        return taille;
    }

    public String getTexte_perso() {
        return texte_perso;
    }
    
    
    /**
     * Méthode qui permet de déterminer a partir d'un entier l'état d'une commande
     * @param e {@link int}
     * @return {@link boolean} etat que pourait avoir une commande
     */
    public static boolean choixEtat(int e){
        return e!=0;
    }
    
    
    
    
    
}
