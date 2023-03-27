/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patisseriegestionnaire.models;

/**
 * 
 * Classe représentant les gateau ou plus généralement les patisseries vendu
 * 
 * @author Romane Malherbe
 */
public class Gateau {
    
    /**
     * Identifiant du gateau
     */
    private int id;
    
    /**
     * Nom du gateau
     */
    private String nom;
    
    /**
     * Description du gateau (au niveau du gout)
     */
    private String description;
    
    /**
     * Description du gateau (général)
     */
    private String descriptionGeneral;
    
    
    /**
     * Lien vers l'image du gateau sur le serveur
     */
    private String lienImg;
    
    /**
     * Prix d'un gateau 
     */
    private float prix;
    
    /**
     * Quantité de gateau actuellement stocké
     */
    private int quantite;
    
    /**
     * Date de début de mise en vente (concerne les gateau spéciaux - Edition Limité)
     */
    private String dateDebut="";
    
     /**
     * Date de fin de mise en vente (concerne les gateau spéciaux - Edition Limité)
     */
    private String dateFin="";

    
  // CONSTRUCTEURS
    
    /**
     * Constructeur par defaut
     * 
     * Créer un objet gateau avec aucune valeur dans ses attributs
     * 
     */
    public Gateau(){
    }
    
    /**
     * Créer un object Gateau 
     * 
     * @param id {@link int} - Identifiant du gateau
     * @param nom {@link String} - Nom du gateau
     * @param description {@link String} - description du gateau (au niveau des gouts)
     * @param lienImg {@link String} - lien vers l'image du gateau sur le serveur
     * @param prix {@link float} - prix d'un gateau 
     * @param quantite {@link int} - quantité de gateau actuellement stocké
     * @param dateDebut {@link String} - Date de début de mise en vente (concerne les gateau spéciaux - Edition Limité)
     * @param dateFin {@link String} - Date de fin de mise en vente (concerne les gateau spéciaux - Edition Limité)
     */
    public Gateau(int id, String nom, String description, String lienImg, float prix, int quantite, String dateDebut, String dateFin) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.lienImg = lienImg;
        this.prix = prix;
        this.quantite = quantite;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    /**
     * Créer un object Gateau 
     * 
     * @param id {@link int} - Identifiant du gateau
     * @param nom {@link String} - Nom du gateau
     * @param description {@link String} - description du gateau (au niveau des gouts)
     * @param descriptionGeneral {@link String} - description du gateau (générale)
     * @param lienImg {@link String} - lien vers l'image du gateau sur le serveur
     * @param prix {@link float} - prix d'un gateau 
     * @param quantite {@link int} - quantité de gateau actuellement stocké
     */
    public Gateau(int id, String nom, String description, String descriptionGeneral, String lienImg, float prix, int quantite) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.descriptionGeneral = descriptionGeneral;
        this.lienImg = lienImg;
        this.prix = prix;
        this.quantite = quantite;
    }
    
    
    
    /**
     * 
     * Création d'un object Gateau 
     * 
     * @param id {@link int} - Identifiant du gateau
     * @param nom {@link String} - Nom du gateau
     */
    public Gateau(int id,String nom){
        this.nom = nom;
        this.id = id;
    }

  // GETTERs & SETTERs
    
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setLienImg(String lienImg) {
        this.lienImg = lienImg;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getLienImg() {
        return lienImg;
    }

    public float getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public int getId() {
        return id;
    }

    public void setDescriptionGeneral(String descriptionGeneral) {
        this.descriptionGeneral = descriptionGeneral;
    }

    public String getDescriptionGeneral() {
        return descriptionGeneral;
    }

    /**
     * Permet de transformer l'attribut en une valeur que l'on êut entrer en base de données
     * 
     * @return {@link String} attribut dateDebut du gateau a un format qui génère pas d'erreur en base de données
     */
    public String getDateDebutSQL() {
        if (dateDebut=="") return "NULL";
        return "'"+dateDebut+"'";
    }

    /**
     * Permet de transformer l'attribut en une valeur que l'on êut entrer en base de données
     * 
     * @return {@link String} attribut dateFin du gateau a un format qui génère pas d'erreur en base de données
     */
    public String getDateFinSQL() {
        if (dateFin=="") return "NULL";
        return "'"+dateFin+"'";
    }
    
    @Override
    public String toString() {
        return id+") Gateau : " + "nom=" + nom + ", description=" + description + ", lienImg=" + lienImg + ", prix=" + prix + ", quantite=" + quantite;
    }
    
}
