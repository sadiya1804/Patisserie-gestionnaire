/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patisseriegestionnaire.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author sadiy
 */
public class Evenement {
    private int idEvt;
    private String dateEvt;
    private String descr;
    private Client client;
    
    public Evenement( int idEvt, String dateEvt,String descr){
        this.idEvt =idEvt;
        this.dateEvt = dateEvt;
        this.descr = descr;
    }
    public Client getClient(){
        return client;
    }
    
    public String getDate(){
        System.out.println(dateEvt);
        return dateEvt;
    }

    public Evenement() {
    }

    public Evenement(int idEvt) {
        this.idEvt = idEvt;
    }

    public Evenement(int idEvt, String dateEvt) {
        this.idEvt = idEvt;
        this.dateEvt = dateEvt;
    }

    public Evenement(String descr, Client client) {
        this.descr = descr;
        this.client = client;
    }

    public void setDateEvt(Date dateEvt) {
        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        this.dateEvt = formater.format(dateEvt);
    }
    

	/*Fonction qui permet de différencier un congé et un événemment normal*/
   public boolean isConge(){
       return client == null;
   }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
    
    public int getDay(){
       return Integer.parseInt(this.dateEvt.substring(8, 10));
    }

    @Override
    public String toString() {
        return "Evenement{" + "idEvt=" + idEvt + ", dateEvt=" + dateEvt + ", descr=" + descr + ", client=" + client + '}';
    }
}
