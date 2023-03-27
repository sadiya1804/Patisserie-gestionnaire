/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patisseriegestionnaire.models;

/**
 * Class client permet de définir les attributs du client
 * 
 * @author sadiy
 * 
 */
public class Client {
    private String nom;
    private String prenom;
    private String mail;
    private String tel;
    private int id;
    
    public Client(String nom,String prenom,String mail,String tel){
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.tel = tel;
    }

    public Client(String nom, String prenom, String mail, String tel, int id) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.tel = tel;
        this.id = id;
    }

    public Client(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Client(String nom, String prenom, int id) {
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
    }
    
   
            
            
    public String getNom(){
        return nom;
    }
     public String getPrenom(){
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getTel() {
        return tel;
    }

    public Client(String prenom) {
        this.prenom = prenom;
    }
}
