/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package patisseriegestionnaire.interfaces;

import patisseriegestionnaire.models.Evenement;
import java.util.ArrayList;

/**
 * Interface avec les méthodes lier au calendrier
 * 
 * @author sadiya
 */
public interface ICalendar {
    
    public ArrayList<Evenement> getListEvenement(int mois, int annee);
    
    public boolean ajoutEvenement(Evenement e);
    
    public boolean ajoutModificationEvmt(Evenement e);
    
    public boolean supprimerEvenement(Evenement e);
    
    
}
