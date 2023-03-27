/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package patisseriegestionnaire.calendrier.main;

import patisseriegestionnaire.models.Client;
import patisseriegestionnaire.models.Abonne;
import patisseriegestionnaire.models.Evenement;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author sadiy
 */
public class EvenementJdialog extends javax.swing.JDialog {

    public static final int CONGE = 0;
    public static final int EVENEMENT = 1;
    public static final int ANNULER = -1;
    
    int typeEvenment;
    private String Description;
    private String numAbo;
    private String client;
    private Evenement evmt;
    
    /**
     * Creates new form EvenementJdialog
     * 
     * @param panelSlide {@link java.awt.Frame} parent du jDialog
     * @param modal {@link boolean}
     */
    public EvenementJdialog(java.awt.Frame panelSlide, boolean modal) {
        super(panelSlide, modal);
        initComponents();
        this.jPanelGrandConteneur.setVisible(false);
        this.pack();
        this.setLocationRelativeTo(null);
        ((CardLayout) this.jPanel4.getLayout()).show(jPanel4, "card3");
    }
   
       
    /*Récupération des éléments rentrés par l'utilisateur pour un événnement*/
    public Evenement showDialog(){
        this.typeEvenment = ANNULER;
        this.setVisible(true);
        switch (this.typeEvenment){
            
            case CONGE:
                
                return new Evenement();
            case EVENEMENT:
                if(jCheckBoxAbonne.isSelected()){
                   return new Evenement(this.Descrip.getText(),new Abonne(this.numClient.getText()));
                }
                else{
                return new Evenement(this.Descrip.getText(),new Client(this.nomClient.getText(),this.prenomClient.getText(), 
                 this.mailClient.getText(), this.telClient.getText())
                );}
            
            case ANNULER:
                
            default:
                
                return null;
            
        } 
    }
    
    public String getNumAbo() {
        return numAbo;
    }

    public String getClient() {
        return client;
    }

    public JPanel getjPanelClientPasAbonne() {
        return jPanelClientPasAbonne;
    }

    @Override
    public void dispose() {
        super.dispose();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jCheckBoxConges = new javax.swing.JCheckBox();
        jCheckBoxEvenement = new javax.swing.JCheckBox();
        jPanelGrandConteneur = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Descrip = new javax.swing.JTextArea();
        jCheckBoxAbonne = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jPanelClientAbonne = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        numClient = new javax.swing.JTextField();
        jPanelClientPasAbonne = new javax.swing.JPanel();
        jLabelPrenom = new javax.swing.JLabel();
        jLabel4Nom = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        prenomClient = new javax.swing.JTextField();
        nomClient = new javax.swing.JTextField();
        telClient = new javax.swing.JTextField();
        mailClient = new javax.swing.JTextField();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jButtonAnnuler = new javax.swing.JButton();
        jButtonValider = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        buttonGroup.add(jCheckBoxConges);
        jCheckBoxConges.setText("Marquer ce jour comme congé");
        jCheckBoxConges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxCongesActionPerformed(evt);
            }
        });

        buttonGroup.add(jCheckBoxEvenement);
        jCheckBoxEvenement.setText("entrez un événement");
        jCheckBoxEvenement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxEvenementActionPerformed(evt);
            }
        });

        jLabel8.setText("Description");

        Descrip.setColumns(20);
        Descrip.setRows(5);
        jScrollPane1.setViewportView(Descrip);

        jCheckBoxAbonne.setText("Le client est-il un abonné?");
        jCheckBoxAbonne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAbonneActionPerformed(evt);
            }
        });

        jPanel4.setLayout(new java.awt.CardLayout());

        jPanelClientAbonne.setRequestFocusEnabled(false);

        jLabel7.setText("Numéro");

        javax.swing.GroupLayout jPanelClientAbonneLayout = new javax.swing.GroupLayout(jPanelClientAbonne);
        jPanelClientAbonne.setLayout(jPanelClientAbonneLayout);
        jPanelClientAbonneLayout.setHorizontalGroup(
            jPanelClientAbonneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClientAbonneLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(numClient, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanelClientAbonneLayout.setVerticalGroup(
            jPanelClientAbonneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClientAbonneLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanelClientAbonneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(numClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(114, Short.MAX_VALUE))
        );

        jPanel4.add(jPanelClientAbonne, "card2");

        jLabelPrenom.setText("Prenom client");

        jLabel4Nom.setText("Nom client");

        jLabel5.setText("Téléphone");

        jLabel6.setText("Mail");

        javax.swing.GroupLayout jPanelClientPasAbonneLayout = new javax.swing.GroupLayout(jPanelClientPasAbonne);
        jPanelClientPasAbonne.setLayout(jPanelClientPasAbonneLayout);
        jPanelClientPasAbonneLayout.setHorizontalGroup(
            jPanelClientPasAbonneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClientPasAbonneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelClientPasAbonneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelClientPasAbonneLayout.createSequentialGroup()
                        .addGroup(jPanelClientPasAbonneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4Nom, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelClientPasAbonneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mailClient, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelClientPasAbonneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(prenomClient, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                                .addComponent(telClient, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(nomClient, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelClientPasAbonneLayout.setVerticalGroup(
            jPanelClientPasAbonneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClientPasAbonneLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanelClientPasAbonneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPrenom)
                    .addComponent(prenomClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanelClientPasAbonneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4Nom)
                    .addComponent(nomClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelClientPasAbonneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(telClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelClientPasAbonneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(mailClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(jPanelClientPasAbonne, "card3");

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelGrandConteneurLayout = new javax.swing.GroupLayout(jPanelGrandConteneur);
        jPanelGrandConteneur.setLayout(jPanelGrandConteneurLayout);
        jPanelGrandConteneurLayout.setHorizontalGroup(
            jPanelGrandConteneurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGrandConteneurLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanelGrandConteneurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxAbonne, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGrandConteneurLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelGrandConteneurLayout.setVerticalGroup(
            jPanelGrandConteneurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGrandConteneurLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxAbonne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonAnnuler.setText("Annuler");
        jButtonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnnulerActionPerformed(evt);
            }
        });

        jButtonValider.setText("Valider");
        jButtonValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValiderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCheckBoxEvenement, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxConges, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelGrandConteneur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jButtonAnnuler)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonValider)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBoxConges, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxEvenement)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelGrandConteneur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonValider, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonAnnuler))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  
    @SuppressWarnings("empty-statement")
    
    /*Si le bouton congés est sélectionné, on enléve le panel qui est affiché (pour afficher le panel qui contient le numéro abonné) */
    private void jCheckBoxCongesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxCongesActionPerformed
        // TODO add your handling code here:
        
          if (this.jCheckBoxConges.isSelected()) {
                this.typeEvenment = CONGE;
                this.jPanelGrandConteneur.setVisible(false);
          }
          this.pack();
          this.setLocationRelativeTo(null);
          
         
    }//GEN-LAST:event_jCheckBoxCongesActionPerformed
    /*Si le bouton "entrez un événemment" est sélectionné, on enléve le panel qui est affiché */
    private void jCheckBoxEvenementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxEvenementActionPerformed
        if (this.jCheckBoxEvenement.isSelected()){
            this.typeEvenment = EVENEMENT;
            this.jPanelGrandConteneur.setVisible(true);
        }
        this.pack();
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_jCheckBoxEvenementActionPerformed

  /*On affiche le panel qui contient le numéro du client si l'utilisateur sélectionne le "client est un abonné"
    Sinon on affiche le panel dans lequel est affiché les nom, prenom et tel du client*/
    private void jCheckBoxAbonneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAbonneActionPerformed
        if (this.jCheckBoxAbonne.isSelected()){
            ((CardLayout) this.jPanel4.getLayout()).show(jPanel4, "card2");
        }else 
            ((CardLayout) this.jPanel4.getLayout()).show(jPanel4, "card3");
    }//GEN-LAST:event_jCheckBoxAbonneActionPerformed

    
    private void jButtonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnnulerActionPerformed
        this.typeEvenment = ANNULER;
        dispose();
    }//GEN-LAST:event_jButtonAnnulerActionPerformed

    private void jButtonValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValiderActionPerformed
        if (this.typeEvenment == EVENEMENT){
            if ("".equals(this.Descrip.getText())){
                JOptionPane.showMessageDialog(this, "Veuillez saisir une description !", "ATTENTION", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (this.jCheckBoxAbonne.isSelected()){
                // Ce qui veut dire que le client est un abonné
                if ("".equals(this.numClient.getText())){ // on vérifie que le champ numero est bien renseigner
                    JOptionPane.showMessageDialog(this, "Veuillez saisir un numéro d'abonné !", "ATTENTION", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                this.numAbo = numClient.getText();   
                this.client = "";
                
            }
            else { //Ce qui veut dire que c'est un nouveau client et donc qu'il n'a pas de numéro abonné
                if ("".equals(this.prenomClient.getText())){ // on vérifie que le prenom est bien renseigner sinon l'utilisateur ne pourra pas valider
                    JOptionPane.showMessageDialog(this, "Veuillez saisir un Prenom !", "ATTENTION", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if ("".equals(this.nomClient.getText())){ // on vérifie que le nom est bien renseigner
                    JOptionPane.showMessageDialog(this, "Veuillez saisir un Nom !", "ATTENTION", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if ("".equals(this.telClient.getText())){ // on vérifie que le tel est bien renseigner
                    JOptionPane.showMessageDialog(this, "Veuillez saisir un numéro de téléphone !", "ATTENTION", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if ("".equals(this.mailClient.getText())){ // on vérifie que l' adresse mail est bien renseigner
                    JOptionPane.showMessageDialog(this, "Veuillez saisir une adresse mail !", "ATTENTION", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
               
                this.client = this.prenomClient.getText()+','+this.nomClient.getText()+','+this.telClient.getText()+','+this.mailClient.getText();
                this.numAbo = "";
                

            }
           

            
                
            
        }
        this.dispose();
        
    }//GEN-LAST:event_jButtonValiderActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EvenementJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EvenementJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EvenementJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EvenementJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EvenementJdialog dialog = new EvenementJdialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Descrip;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JButton jButtonAnnuler;
    private javax.swing.JButton jButtonValider;
    private javax.swing.JCheckBox jCheckBoxAbonne;
    private javax.swing.JCheckBox jCheckBoxConges;
    private javax.swing.JCheckBox jCheckBoxEvenement;
    private javax.swing.JLabel jLabel4Nom;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelPrenom;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelClientAbonne;
    private javax.swing.JPanel jPanelClientPasAbonne;
    private javax.swing.JPanel jPanelGrandConteneur;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField mailClient;
    private javax.swing.JTextField nomClient;
    private javax.swing.JTextField numClient;
    private javax.swing.JTextField prenomClient;
    private javax.swing.JTextField telClient;
    // End of variables declaration//GEN-END:variables

    private void isSelected() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    int showDialog2(int EVENEMENT) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}
