/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patisseriegestionnaire.models;

/**
 * Cette class permet de différencier le client normal et le client abonné 
 * 
 * @author sadiy
 * 
 */
public class Abonne extends Client{
    
    private String numAbo;
    

    
    public Abonne(String numAbo, String nom, String prenom, String mail, String tel) {
        super(nom, prenom, mail, tel);
        this.numAbo = numAbo;
    }
     public Abonne (String numAbo) {
        super("", "", "","");
        this.numAbo = numAbo;
    }

    public String getNumAbo() {
        return numAbo;
    }
    
    
}
