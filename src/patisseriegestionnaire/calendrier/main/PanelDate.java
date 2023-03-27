package patisseriegestionnaire.calendrier.main;

import patisseriegestionnaire.models.Evenement;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import patisseriegestionnaire.interfaces.ICalendar;

public class PanelDate extends javax.swing.JLayeredPane {

    private int mois;
    private int annee;
    private ArrayList<Evenement> arrayEvent;
    private ICalendar icalendar=null; 
    
    public PanelDate(int month, int year) {
        initComponents();
        this.mois = month;
        this.annee = year;
        this.arrayEvent = new ArrayList<Evenement>(31);
        for (int i=0; i<32;i++) this.arrayEvent.add(null);     
        init();
    }
     public PanelDate(int month, int year, ArrayList arrayEvent,ICalendar icalendar) {
        initComponents();
        this.mois = month;
        this.annee = year;
        this.arrayEvent = arrayEvent;
        this.icalendar = icalendar;
        init();
    }

    private void init() {
        lundi.asTitle();
        mardi.asTitle();
        mercredi.asTitle();
        jeudi.asTitle();
        vendredi.asTitle();
        samedi.asTitle();
        dimanche.asTitle();
        setDate();
    }

    private void setDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, annee);
        calendar.set(Calendar.MONTH, mois - 1);  //  le mois de janvier est égal à 0 et commence donc à partir de 0
        calendar.set(Calendar.DATE, 1);
        int startDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;  //  On récupére le jour de la semaine -1 à l'index
        calendar.add(Calendar.DATE, -startDay);
        ToDay toDay = getToDay();
        for (Component com : getComponents()) {
            Cell cell = (Cell) com;
            if (!cell.isTitle()) {
                cell.setText(calendar.get(Calendar.DATE) + "");
                cell.setDate(calendar.getTime());
                cell.currentMonth(calendar.get(Calendar.MONTH) == mois - 1);
                if (toDay.isToDay(new ToDay(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR)))) {
                    cell.setAsToDay();
                }
                if(cell.isCurrentMonth()){
                    if(arrayEvent != null){
                        Evenement e = arrayEvent.get(Integer.parseInt(cell.getText())-1); 
                            if(e != null){
                                if(e.isConge()){
                                    cell.setOpaque(true);
                                    cell.setBackground(Color.red);// On change le fond de la cellule en rouge si l'évenement est un congé
                                
                                }
                                else{
                                    cell.setOpaque(true);
                                    cell.setBackground(Color.green);//On change le fond de la cellule en vert si l'événnement est un événemment
                                }
                            }
                    }
                }
                cell.addActionListener((ActionEvent e) -> {
                    if (cell.isCurrentMonth()){
                        if (arrayEvent.get(Integer.parseInt(cell.getText())-1)==null){
                            Frame panelSlide = null;
                            EvenementJdialog dialog = new EvenementJdialog(panelSlide, true);
                            Evenement r = dialog.showDialog();
                            
                            if (r!=null){
                                if (r.isConge()){
                                    cell.setOpaque(true);
                                    cell.setBackground(Color.red);// cellule rouge  = congé
                                }else{
                                    cell.setOpaque(true);
                                    cell.setBackground(Color.green);// cellule verte = événement 
                                }
                                r.setDateEvt(cell.getDate());
                                if (icalendar!=null) icalendar.ajoutEvenement(r);
                                arrayEvent.set(Integer.parseInt(cell.getText())-1,r);
                            }
                        }else{
                            Evenement ev = arrayEvent.get(Integer.parseInt(cell.getText())-1);
                            if (!ev.isConge()){
                                InfosEvenement jframeInfo = new InfosEvenement(ev);
                                int et = jframeInfo.showDialog();
                                switch (et){
                                    case InfosEvenement.MODIFIER:
                                        Frame panelSlide = null;
                                        EvenementJdialog dialog = new EvenementJdialog(panelSlide, true);
                                        Evenement r = dialog.showDialog();

                                        if (r!=null){
                                            if (r.isConge()){
                                                cell.setOpaque(true);
                                                cell.setBackground(Color.red);
                                            }else{
                                                cell.setOpaque(true);
                                                cell.setBackground(Color.green);
                                            }
                                            r.setDateEvt(cell.getDate());
                                            if (icalendar!=null) icalendar.ajoutModificationEvmt(r);
                                            arrayEvent.set(Integer.parseInt(cell.getText())-1,r);
                                        }
                                        break;
                                    case InfosEvenement.SUPPRIMER:
                                        if (icalendar!=null) icalendar.supprimerEvenement(arrayEvent.get(Integer.parseInt(cell.getText())-1));
                                         arrayEvent.set(Integer.parseInt(cell.getText())-1,null);
                                         cell.setOpaque(false);
                                        break;
                                    
                                }
                            }
                            else {
                                if(JOptionPane.showConfirmDialog(PanelDate.this,"Voulez-vous supprimer le congés ?","Confirmer",JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION){ //vérifier que le client répond par bien par "OUI" avant de supprimer le congés
                                  if (icalendar!=null) icalendar.supprimerEvenement(arrayEvent.get(Integer.parseInt(cell.getText())-1));

                                        
                                  arrayEvent.set(Integer.parseInt(cell.getText())-1,null);
                                  cell.setOpaque(false);// On remet enléve la rouge de la celle
                                }
                                    
                            }
                            
                        }
                    }
                });
                calendar.add(Calendar.DATE, 1); 
            }
        }
    }

    private ToDay getToDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return new ToDay(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dimanche = new Cell();
        lundi = new Cell();
        mardi = new Cell();
        mercredi = new Cell();
        jeudi = new Cell();
        vendredi = new Cell();
        samedi = new Cell();
        cell8 = new Cell();
        cell9 = new Cell();
        cell10 = new Cell();
        cell11 = new Cell();
        cell12 = new Cell();
        cell13 = new Cell();
        cell14 = new Cell();
        cell15 = new Cell();
        cell16 = new Cell();
        cell17 = new Cell();
        cell18 = new Cell();
        cell19 = new Cell();
        cell20 = new Cell();
        cell21 = new Cell();
        cell22 = new Cell();
        cell23 = new Cell();
        cell24 = new Cell();
        cell25 = new Cell();
        cell26 = new Cell();
        cell27 = new Cell();
        cell28 = new Cell();
        cell29 = new Cell();
        cell30 = new Cell();
        cell31 = new Cell();
        cell32 = new Cell();
        cell33 = new Cell();
        cell34 = new Cell();
        cell35 = new Cell();
        cell36 = new Cell();
        cell37 = new Cell();
        cell38 = new Cell();
        cell39 = new Cell();
        cell40 = new Cell();
        cell41 = new Cell();
        cell42 = new Cell();
        cell43 = new Cell();
        cell44 = new Cell();
        cell45 = new Cell();
        cell46 = new Cell();
        cell47 = new Cell();
        cell48 = new Cell();
        cell49 = new Cell();

        setLayout(new java.awt.GridLayout(7, 7));

        dimanche.setForeground(new java.awt.Color(222, 12, 12));
        dimanche.setText("Dim");
        dimanche.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(dimanche);

        lundi.setText("Lun");
        lundi.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(lundi);

        mardi.setText("Mar");
        mardi.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(mardi);

        mercredi.setText("Mer");
        mercredi.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(mercredi);

        jeudi.setText("Jeu");
        jeudi.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(jeudi);

        vendredi.setText("Ven");
        vendredi.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(vendredi);

        samedi.setText("Sam");
        samedi.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(samedi);

        cell8.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell8);

        cell9.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell9);

        cell10.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell10);

        cell11.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell11);

        cell12.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell12);

        cell13.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell13);

        cell14.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell14);

        cell15.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell15);

        cell16.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell16);

        cell17.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell17);

        cell18.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell18);

        cell19.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell19);

        cell20.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell20);

        cell21.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell21);

        cell22.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell22);

        cell23.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell23);

        cell24.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell24);

        cell25.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell25);

        cell26.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell26);

        cell27.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell27);

        cell28.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell28);

        cell29.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell29);

        cell30.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell30);

        cell31.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell31);

        cell32.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell32);

        cell33.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell33);

        cell34.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell34);

        cell35.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell35);

        cell36.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell36);

        cell37.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell37);

        cell38.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell38);

        cell39.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell39);

        cell40.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell40);

        cell41.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell41);

        cell42.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell42);

        cell43.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell43);

        cell44.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell44);

        cell45.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell45);

        cell46.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell46);

        cell47.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell47);

        cell48.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell48);

        cell49.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        add(cell49);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Cell cell10;
    private Cell cell11;
    private Cell cell12;
    private Cell cell13;
    private Cell cell14;
    private Cell cell15;
    private Cell cell16;
    private Cell cell17;
    private Cell cell18;
    private Cell cell19;
    private Cell cell20;
    private Cell cell21;
    private Cell cell22;
    private Cell cell23;
    private Cell cell24;
    private Cell cell25;
    private Cell cell26;
    private Cell cell27;
    private Cell cell28;
    private Cell cell29;
    private Cell cell30;
    private Cell cell31;
    private Cell cell32;
    private Cell cell33;
    private Cell cell34;
    private Cell cell35;
    private Cell cell36;
    private Cell cell37;
    private Cell cell38;
    private Cell cell39;
    private Cell cell40;
    private Cell cell41;
    private Cell cell42;
    private Cell cell43;
    private Cell cell44;
    private Cell cell45;
    private Cell cell46;
    private Cell cell47;
    private Cell cell48;
    private Cell cell49;
    private Cell cell8;
    private Cell cell9;
    private Cell dimanche;
    private Cell jeudi;
    private Cell lundi;
    private Cell mardi;
    private Cell mercredi;
    private Cell samedi;
    private Cell vendredi;
    // End of variables declaration//GEN-END:variables
}
