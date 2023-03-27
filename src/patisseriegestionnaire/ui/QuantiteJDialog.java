/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patisseriegestionnaire.ui;

import java.awt.Color;
import java.awt.Font;
import patisseriegestionnaire.models.Gateau;

/**
 * Classe permettant de générer des JDialog pour selectionner une quantité
 * 
 * @author Romane Malherbe
 */
public class QuantiteJDialog extends javax.swing.JDialog {

    /**
     * quantité maximum que peut prendre la valeur qu'on renvoie
     */
    private int qtMax;
    
    /**
     * Création du nouvelle jDialog
     * 
     * @param parent {@link java.awt.Frame} parent
     * @param modal {@link boolean} si le jDialog doit être modal 
     */
    public QuantiteJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    
    /**
     * Méthode qui affiche une QuantiteJDialog et qui renvoie la valeur saisie pour changer la quantiter
     * d'un gateau dans un ticket.
     * 
     * @param parent {@link java.awt.Frame} parent
     * @param g {@link Gateau} dont on veut modifier la quantiter dans le ticket
     * @param quantiteTicket {@link int} - quantité actuelle
     * @param bg {@link Color} de fond de la JDialog
     * @param bg_bt {@link Color} de fond des bouttons de la JDialog
     * @param ft {@link Font} utiliser pour les textes
     * 
     * @return {@link int} la nouvelle quantité, -1 si on annule l'action
     */
    public static int show(java.awt.Frame parent, Gateau g,int quantiteTicket, Color bg, Color bg_bt, Font ft){
       
        // création d'une QuantiteJDialog
        QuantiteJDialog dialog = new QuantiteJDialog(parent, true);
        
        
        dialog.jPanel1.setBackground(bg);
        dialog.jButtonMoin.setBackground(bg_bt);
        dialog.jButtonPlus.setBackground(bg_bt);
        dialog.jButtonAnnuler.setBackground(bg_bt);
        dialog.jButtonValider.setBackground(bg_bt);
        dialog.jButtonSupprimer.setBackground(bg_bt);
        
     
        dialog.jButtonMoin.setFont(ft);
        dialog.jButtonPlus.setFont(ft);
        dialog.jButtonAnnuler.setFont(ft);
        dialog.jButtonValider.setFont(ft);
        dialog.jButtonSupprimer.setFont(ft);
        dialog.jLabel1.setFont(ft);
        dialog.jSpinner1.setFont(ft);
        
        dialog.pack();
        
        
        
        dialog.setLocationRelativeTo(parent);
        
        // on initialise la quantité max ( dans ce cas le nombre de gateau disponible en stock)
        dialog.qtMax = g.getQuantite();
        
        // On écrit le nom du gateau dans le  jLabel 
        dialog.jLabel1.setText("Quantité de "+g.getNom()+" :");
        
        // On créé un model pour le jSpinner pour pas qu'il est une valeur inférieur à 0 et au dessus de la quantite max
        dialog.jSpinner1.setModel(new javax.swing.SpinnerNumberModel(quantiteTicket,0,g.getQuantite(), 1));
        
        // si la quantité actuelle est egal a la quantiter maximal on désactive le boutton pour
        //incrémenter la quantité renvoyé
        if (quantiteTicket == g.getQuantite()){
            dialog.jButtonPlus.setEnabled(false);
        }
        
        // on affiche la jdialog
        dialog.setVisible(true);
        
        // on retourne la nouvelle valeur
        return (int)dialog.jSpinner1.getValue();
        
    }

    
    /**
     * 
     * Méthode qui affiche une QuantiteJDialog et qui renvoie la valeur saisie pour changer la quantiter
     * d'un gateau dans le stock du magasin
     * 
     * @param parent {@link java.awt.Frame} parent
     * @param g {@link Gateau} dont on veut modifier la quantiter dans le ticket
     * @param bg {@link Color} de fond de la JDialog
     * @param bg_bt {@link Color} de fond des bouttons de la JDialog
     * @param ft {@link Font} utiliser pour les textes
     * 
     * @return {@link int} la nouvelle quantité, -1 si on annule l'action
     */
    public static int show(java.awt.Frame parent,Gateau g,Color bg, Color bg_bt, Font ft){
        
        // création d'une QuantiteJDialog
        QuantiteJDialog dialog = new QuantiteJDialog(parent, true);
        
        
        dialog.jPanel1.setBackground(bg);
        dialog.jButtonMoin.setBackground(bg_bt);
        dialog.jButtonPlus.setBackground(bg_bt);
        dialog.jButtonAnnuler.setBackground(bg_bt);
        dialog.jButtonValider.setBackground(bg_bt);
        
     
        dialog.jButtonMoin.setFont(ft);
        dialog.jButtonPlus.setFont(ft);
        dialog.jButtonAnnuler.setFont(ft);
        dialog.jButtonValider.setFont(ft);
        dialog.jLabel1.setFont(ft);
        dialog.jSpinner1.setFont(ft);
        
        // On désactive le bouton supprimer comme il n'y en a pas besoin
        dialog.jButtonSupprimer.setVisible(false);
        
        dialog.pack();
        
        // On écrit le nom du gateau dans le  jLabel 
        dialog.jLabel1.setText("Saisir nouvelle quantité de "+g.getNom()+" :");
        
        // On créé un model pour le jSpinner pour pas qu'il est une valeur inférieur à 0
        // la valeur initiale est à la quantité actuelle de gateau en stock
        dialog.jSpinner1.setModel(new javax.swing.SpinnerNumberModel(g.getQuantite(),0,null, 1));
        
        dialog.setLocationRelativeTo(parent);
        
        // on affiche la jdialog
        dialog.setVisible(true);
        
        // on retourne la nouvelle valeur
        return (int) dialog.jSpinner1.getValue();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonMoin = new javax.swing.JButton();
        jButtonPlus = new javax.swing.JButton();
        jButtonAnnuler = new javax.swing.JButton();
        jButtonValider = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        jButtonSupprimer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(" ");
        setBackground(new java.awt.Color(255, 0, 0));

        jLabel1.setText("Quantité de \"Gateau\" :");

        jButtonMoin.setForeground(new java.awt.Color(255, 255, 255));
        jButtonMoin.setText("-");
        jButtonMoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMoinActionPerformed(evt);
            }
        });

        jButtonPlus.setForeground(new java.awt.Color(255, 255, 255));
        jButtonPlus.setText("+");
        jButtonPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlusActionPerformed(evt);
            }
        });

        jButtonAnnuler.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAnnuler.setText("Annuler");
        jButtonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnnulerActionPerformed(evt);
            }
        });

        jButtonValider.setForeground(new java.awt.Color(255, 255, 255));
        jButtonValider.setText("Valider");
        jButtonValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValiderActionPerformed(evt);
            }
        });

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 6, 1));

        jButtonSupprimer.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSupprimer.setText("Supprimer");
        jButtonSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprimerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonAnnuler)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSupprimer)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonValider))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonMoin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPlus))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPlus)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonMoin))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonValider)
                    .addComponent(jButtonSupprimer)
                    .addComponent(jButtonAnnuler))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonMoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMoinActionPerformed
        // on diminue 1a valeur du jspinner 
        this.jSpinner1.setValue((int)this.jSpinner1.getValue() - 1 );
        
        // On réactive le bouton Plus si il n'était plus activé
        if (jButtonPlus.isEnabled())
            jButtonPlus.setEnabled(true);
        
        // Si la valeur tombe a 0 on désactive le boutton moins
        if ((int)this.jSpinner1.getValue() == 0){
            jButtonMoin.setEnabled(false);
        }
    }//GEN-LAST:event_jButtonMoinActionPerformed

    private void jButtonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnnulerActionPerformed
        // On met a -1 la valeur du Spinner (la valeur renvoyé sera -1, ça signifie que l'action a été annuler) 
        this.jSpinner1.setValue(-1);
        
        // on ferme la fenetre
        dispose();
    }//GEN-LAST:event_jButtonAnnulerActionPerformed

    private void jButtonPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlusActionPerformed
        // on augmente 1a valeur du jspinner 
        this.jSpinner1.setValue((int)this.jSpinner1.getValue() + 1 );
        
        // On réactive le bouton Moin si il n'était plus activé
        if (jButtonMoin.isEnabled())
            jButtonMoin.setEnabled(true);
        
        // Si la valeur depasse le max on désactive le bouton plus
        if ((int)this.jSpinner1.getValue() == qtMax){
            jButtonPlus.setEnabled(false);
        }
    }//GEN-LAST:event_jButtonPlusActionPerformed

    private void jButtonValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValiderActionPerformed
        // on ferme la fenêtre
        dispose();
    }//GEN-LAST:event_jButtonValiderActionPerformed

    private void jButtonSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprimerActionPerformed
        // pour supprimer il suffit de mettre la valeur à 0 
        this.jSpinner1.setValue(0);
        
        // on ferme la fenêtre
        dispose();
    }//GEN-LAST:event_jButtonSupprimerActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnnuler;
    private javax.swing.JButton jButtonMoin;
    private javax.swing.JButton jButtonPlus;
    private javax.swing.JButton jButtonSupprimer;
    private javax.swing.JButton jButtonValider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinner1;
    // End of variables declaration//GEN-END:variables
}
