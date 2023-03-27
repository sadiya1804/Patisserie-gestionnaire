/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patisseriegestionnaire.ui;

import java.awt.CardLayout;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import patisseriegestionnaire.interfaces.ICalendar;
import patisseriegestionnaire.ui.jpanel.JPanelCaisse.IPanelCaisse;
import patisseriegestionnaire.interfaces.InterfaceJFrame;
import patisseriegestionnaire.models.Commande;
import patisseriegestionnaire.models.CommandePersonnalise;
import patisseriegestionnaire.models.ElementdUnTicket;
import patisseriegestionnaire.models.Evenement;
import patisseriegestionnaire.models.Gateau;
import patisseriegestionnaire.ui.jpanel.JPanelCaisse;
import patisseriegestionnaire.ui.jtables.CACjTable;
import patisseriegestionnaire.ui.jtables.CACjTable.CACjTableListener;
import patisseriegestionnaire.ui.jtables.ListCommandePersojTable;
import patisseriegestionnaire.ui.jtables.ListCommandePersojTable.LcomPersojTableListener;
import patisseriegestionnaire.ui.jtables.ListGateaujTable;
import patisseriegestionnaire.ui.jtables.ListGateaujTable.ListGateauListener;
/**
 *
 * @author Romane Malherbe
 */
public class mainJFrame extends javax.swing.JFrame {

    static private final java.awt.Color FOND1 = new java.awt.Color(0x8B8383);
    static private final java.awt.Color FONDTITTRE = new java.awt.Color(0x585858);
    static private final java.awt.Color FOND2 = new java.awt.Color(0x3E3A3A);
    static private final java.awt.Color FOND3 = new java.awt.Color(0xcbcbcb);
    static private final java.awt.Color FONDBT = new java.awt.Color(0x23272B);
    static private final java.awt.Font FONT_BIG = new java.awt.Font("Helvetica", Font.BOLD, 30);
    static private final java.awt.Font FONT = new java.awt.Font("Helvetica", Font.PLAIN, 20);
    static private final java.awt.Font FONT_SMALL = new java.awt.Font("Helvetica", Font.PLAIN, 15);
    
    
    
    private InterfaceJFrame interfaceJFrame = null;
 
// CONSTRUCTEURS
    
    /**
     * Constructeur par défault
     */
    public mainJFrame() {
        initComponents();
        changeVue("cardMenu");    
        super.setExtendedState(this.MAXIMIZED_BOTH);
    }
    
    /**
     * Constructeur avec une {@link InterfaceJFrame} en parametre permettrant de récupérer les données, et les mettre à jours dans une source extérieur (base de donnée)
     * @param i Classe implementant les méthode de l'interface {@link InterfaceJFrame} 
     */
    public mainJFrame(InterfaceJFrame i) { 
        this.interfaceJFrame = i;
        initComponents();
        listGateaujTable1.addListGateauListener(new ListGateauListener() {
            @Override
            public void editionGateau(Gateau g) {
                interfaceJFrame.modifyGateau(g);
            }
        });
        listCommandePersojTable1.addLcomPersolistener(new LcomPersojTableListener() {
            @Override
            public boolean validerCommande(CommandePersonnalise c) {
                 return interfaceJFrame.validerCommandePerso(c);
            }
        });
        this.clickAndCollectJTable1.addCAClistener(new CACjTableListener() {
            @Override
            public boolean validerCommande(Commande c) {
                return interfaceJFrame.validerGateauCommande(c);
            }
        });
        this.jPanelCaisse1.setI(new IPanelCaisse() {
            @Override
            public void addGateau(Gateau g) {
                JlistModelTicket model = ((JlistModelTicket) jList1.getModel());
                if (model.ajoutGateau(g)){
                    jLabel3.setText("Total : "+model.getPrixTotal());
                    jList1.updateUI();
                    jPanel2.updateUI();
                }else{
                    JOptionPane.showMessageDialog(mainJFrame.this, "Attention saisie erroner,\nImpossible d'ajouter plus de "+g.getNom(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        
        this.calendarCustom1.setIcalendar(new ICalendar(){
            @Override
            public ArrayList<Evenement> getListEvenement(int mois, int annee) {
                return interfaceJFrame.getListEvenement(mois,annee);
            }

            @Override
            public boolean ajoutEvenement(Evenement e) {
                return interfaceJFrame.ajoutEvenement(e);
            }

            @Override
            public boolean ajoutModificationEvmt(Evenement e) {
                return interfaceJFrame.ajoutModificationEvmt(e);
            }

            @Override
            public boolean supprimerEvenement(Evenement e) {
                return interfaceJFrame.supprimerEvenement(e);
            }
        
        });
        
        changeVue("cardMenu");
        super.setExtendedState(this.MAXIMIZED_BOTH);
    }

    /**
     * Fonction permettant de facilment changer le panel afficher dans le CardLayout du JPanelConteneur
     * et également de modifier/mettre à jour si besoin des elements
     * @param nomCard Nom du card qui vas être affiché
     */
    private void changeVue(String nomCard){
        ((CardLayout) jPanelConteneur.getLayout()).show(jPanelConteneur, nomCard);
        
        jButtonListCommande.setIcon(recupererEtRedimensionnerIMG("/patisseriegestionnaire/asset/commandeIcone.png"));
       
        jButtonCalendrier.setIcon(recupererEtRedimensionnerIMG("/patisseriegestionnaire/asset/calendrierIcone.png"));
        
        jButtonListGateau.setIcon(recupererEtRedimensionnerIMG("/patisseriegestionnaire/asset/iconeGateau.png"));
        
        jButtonListeCommandPersonnaliser.setIcon(recupererEtRedimensionnerIMG("/patisseriegestionnaire/asset/CommandePersonnalisee.png"));
        
        jButtonCaisse.setIcon(recupererEtRedimensionnerIMG("/patisseriegestionnaire/asset/ticketIcone.png"));
        
        switch (nomCard){
            case "cardListCommandePersonnalise" :
                if (interfaceJFrame!=null) 
                    this.listCommandePersojTable1.setNewModel(interfaceJFrame.getAllCommandesPerso());
                jButtonListeCommandPersonnaliser.setIcon(recupererEtRedimensionnerIMG("/patisseriegestionnaire/asset/CommandePersonnalisee1.png"));
                break;
            case "cardListCommande":
                if (interfaceJFrame!=null) 
                    clickAndCollectJTable1.setNewModel(interfaceJFrame.getAllCommandes());
                jButtonListCommande.setIcon(recupererEtRedimensionnerIMG("/patisseriegestionnaire/asset/commandeIcone1.png"));
                break;
            case "cardListGateau":
                if (interfaceJFrame!=null) 
                    listGateaujTable1.setNewModel(interfaceJFrame.getAllGateau());
            case "CardAjoutGateau":
                jButtonListGateau.setIcon(recupererEtRedimensionnerIMG("/patisseriegestionnaire/asset/iconeGateau1.png"));
                break;
            case "cardCaisse":
                jButtonCaisse.setIcon(recupererEtRedimensionnerIMG("/patisseriegestionnaire/asset/ticketIcone1.png"));
                if (interfaceJFrame!=null) {
                    jPanelCaisse1.majListGateau(interfaceJFrame.getAllGateau());
                    jList1.setModel(new JlistModelTicket());
                    jLabel3.setText("Total : ");
                }
                break;
            case "cardCalendrier":
                jButtonCalendrier.setIcon(recupererEtRedimensionnerIMG("/patisseriegestionnaire/asset/calendrierIcone1.png"));
                break;
            default:
                
        }
    }
    
    /**
     * Methode qui recupère la ressource du projet se trouvant à l'meplacement mis en paramètre
     * la transforme en {@link ImageIcon} et la redimensionne en Image de 70*70 pixel.
     * @param lienRessource chemin dans le projet vers la ressource
     * @return {@link ImageIcon} 
     */
    private ImageIcon recupererEtRedimensionnerIMG(String lienRessource){
        ImageIcon i = new javax.swing.ImageIcon(getClass().getResource(lienRessource));
        return new ImageIcon(i.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
    }
    
    @Override
    public void dispose() {  
        if (interfaceJFrame!=null)
            interfaceJFrame.fermeture();
        super.dispose(); 
        
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jFileChooserLienImg = new javax.swing.JFileChooser();
        jPanelNavigation = new javax.swing.JPanel();
        jButtonListCommande = new javax.swing.JButton();
        jButtonCalendrier = new javax.swing.JButton();
        jButtonListGateau = new javax.swing.JButton();
        jButtonListeCommandPersonnaliser = new javax.swing.JButton();
        jButtonCaisse = new javax.swing.JButton();
        jPanelConteneur = new javax.swing.JPanel();
        jPanelListGateau = new javax.swing.JPanel();
        jLabelTitreListGateau = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listGateaujTable1 = (interfaceJFrame==null)?new ListGateaujTable():new ListGateaujTable(interfaceJFrame.getAllGateau());
        jPanel3 = new javax.swing.JPanel();
        jButtonSupprimerProduit = new javax.swing.JButton();
        jToggleButtonEditionProduit = new javax.swing.JToggleButton();
        jButtonRestockerProduit = new javax.swing.JButton();
        jButtonAjouterGateau = new javax.swing.JButton();
        jPanelAjoutGateau = new javax.swing.JPanel();
        jLabelNom = new javax.swing.JLabel();
        jTextFieldNom = new javax.swing.JTextField();
        jLabelPrix = new javax.swing.JLabel();
        jSpinnerPrix = new javax.swing.JSpinner();
        jLabelDescription = new javax.swing.JLabel();
        jScrollPaneDescription = new javax.swing.JScrollPane();
        jTextAreaDescription = new javax.swing.JTextArea();
        jLabelLienImg = new javax.swing.JLabel();
        jPanelChoixLienImg = new javax.swing.JPanel();
        jTextFieldLienImg = new javax.swing.JTextField();
        jButtonBrowseLienImg = new javax.swing.JButton();
        jButtonUploadLienImg = new javax.swing.JButton();
        jLabelQuantite = new javax.swing.JLabel();
        jSpinnerQuantite = new javax.swing.JSpinner();
        jButtonValider = new javax.swing.JButton();
        jButtonRetour = new javax.swing.JButton();
        jLabelDescriptionGen = new javax.swing.JLabel();
        jScrollPaneDescriptionGen = new javax.swing.JScrollPane();
        jTextAreaDescriptionGen = new javax.swing.JTextArea();
        jPanelAffichageCommande = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButtonValiderCAC = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        clickAndCollectJTable1 = (interfaceJFrame==null)? new CACjTable():new CACjTable(interfaceJFrame.getAllCommandes());
        jPanelListCommandePersonnalise = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listCommandePersojTable1 = (interfaceJFrame==null)?new ListCommandePersojTable():new ListCommandePersojTable(interfaceJFrame.getAllCommandesPerso());
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButtonAnnulerComPerso = new javax.swing.JButton();
        jButtonValiderComPerso = new javax.swing.JButton();
        jPanelMenu = new javax.swing.JPanel();
        jButtonMenuCAC = new javax.swing.JButton();
        jButtonMenuCal = new javax.swing.JButton();
        jButtonMenuGat = new javax.swing.JButton();
        jButtonMenuComPerso = new javax.swing.JButton();
        jPanelCaisse = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanelCaisse1 = (interfaceJFrame==null)?new JPanelCaisse():new JPanelCaisse(interfaceJFrame.getAllGateau(),FONDBT,FONT);
        jPanelCalendar = new javax.swing.JPanel();
        calendarCustom1 = new patisseriegestionnaire.calendrier.main.CalendarCustom();

        jFileChooserLienImg.setDialogTitle("Selectionner une image");
        jFileChooserLienImg.setFileFilter(new FileNameExtensionFilter("Images", "bmp", "gif", "jpg", "jpeg", "png"));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Application Gestionnaire");
        setBackground(FOND1);

        jPanelNavigation.setBackground(FOND2);
        jPanelNavigation.setPreferredSize(new java.awt.Dimension(90, 350));
        jPanelNavigation.setLayout(new java.awt.GridBagLayout());

        jButtonListCommande.setBackground(FOND2);
        jButtonListCommande.setForeground(new java.awt.Color(255, 255, 255));
        jButtonListCommande.setIcon(recupererEtRedimensionnerIMG("/patisseriegestionnaire/asset/commandeIcone.png"));
        jButtonListCommande.setBorder(null);
        jButtonListCommande.setBorderPainted(false);
        jButtonListCommande.setPreferredSize(new java.awt.Dimension(70, 70));
        jButtonListCommande.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListCommandeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanelNavigation.add(jButtonListCommande, gridBagConstraints);

        jButtonCalendrier.setBackground(FOND2);
        jButtonCalendrier.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCalendrier.setIcon(recupererEtRedimensionnerIMG("/patisseriegestionnaire/asset/calendrierIcone.png"));
        jButtonCalendrier.setBorder(null);
        jButtonCalendrier.setBorderPainted(false);
        jButtonCalendrier.setPreferredSize(new java.awt.Dimension(70, 70));
        jButtonCalendrier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCalendrierActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanelNavigation.add(jButtonCalendrier, gridBagConstraints);

        jButtonListGateau.setBackground(FOND2);
        jButtonListGateau.setForeground(new java.awt.Color(255, 255, 255));
        jButtonListGateau.setIcon(recupererEtRedimensionnerIMG("/patisseriegestionnaire/asset/iconeGateau.png"));
        jButtonListGateau.setBorder(null);
        jButtonListGateau.setBorderPainted(false);
        jButtonListGateau.setPreferredSize(new java.awt.Dimension(70, 70));
        jButtonListGateau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListGateauActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanelNavigation.add(jButtonListGateau, gridBagConstraints);

        jButtonListeCommandPersonnaliser.setBackground(FOND2);
        jButtonListeCommandPersonnaliser.setForeground(new java.awt.Color(255, 255, 255));
        jButtonListeCommandPersonnaliser.setIcon(recupererEtRedimensionnerIMG("/patisseriegestionnaire/asset/CommandePersonnalisee.png"));
        jButtonListeCommandPersonnaliser.setBorder(null);
        jButtonListeCommandPersonnaliser.setBorderPainted(false);
        jButtonListeCommandPersonnaliser.setPreferredSize(new java.awt.Dimension(70, 70));
        jButtonListeCommandPersonnaliser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListeCommandPersonnaliserActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanelNavigation.add(jButtonListeCommandPersonnaliser, gridBagConstraints);

        jButtonCaisse.setBackground(FOND2);
        jButtonCaisse.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCaisse.setIcon(recupererEtRedimensionnerIMG("/patisseriegestionnaire/asset/ticketIcone.png"));
        jButtonCaisse.setBorder(null);
        jButtonCaisse.setBorderPainted(false);
        jButtonCaisse.setPreferredSize(new java.awt.Dimension(75, 75));
        jButtonCaisse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCaisseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        jPanelNavigation.add(jButtonCaisse, gridBagConstraints);

        getContentPane().add(jPanelNavigation, java.awt.BorderLayout.LINE_START);

        jPanelConteneur.setBackground(FOND1);
        jPanelConteneur.setLayout(new java.awt.CardLayout());

        jPanelListGateau.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanelListGateau.setOpaque(false);
        jPanelListGateau.setLayout(new java.awt.BorderLayout(0, 10));

        jLabelTitreListGateau.setFont(FONT_BIG);
        jLabelTitreListGateau.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitreListGateau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitreListGateau.setIcon(recupererEtRedimensionnerIMG("/patisseriegestionnaire/asset/iconeGateau.png"));
        jLabelTitreListGateau.setText("Produit actuellement en vente :");
        jLabelTitreListGateau.setIconTextGap(10);
        jPanelListGateau.add(jLabelTitreListGateau, java.awt.BorderLayout.NORTH);

        listGateaujTable1.setBackground(FOND3);
        listGateaujTable1.getTableHeader().setBackground(FOND2);
        listGateaujTable1.getTableHeader().setFont(FONT_SMALL);
        listGateaujTable1.getTableHeader().setForeground(java.awt.Color.WHITE);
        jScrollPane3.setViewportView(listGateaujTable1);

        jPanelListGateau.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel3.setOpaque(false);

        jButtonSupprimerProduit.setBackground(FONDBT);
        jButtonSupprimerProduit.setFont(FONT);
        jButtonSupprimerProduit.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSupprimerProduit.setText("Supprimer produit");
        jButtonSupprimerProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprimerProduitActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonSupprimerProduit);

        jToggleButtonEditionProduit.setBackground(FONDBT);
        jToggleButtonEditionProduit.setFont(FONT);
        jToggleButtonEditionProduit.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButtonEditionProduit.setText("Editer produits");
        jToggleButtonEditionProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonEditionProduitActionPerformed(evt);
            }
        });
        jPanel3.add(jToggleButtonEditionProduit);

        jButtonRestockerProduit.setBackground(FONDBT);
        jButtonRestockerProduit.setFont(FONT);
        jButtonRestockerProduit.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRestockerProduit.setText("Restocker un produit");
        jButtonRestockerProduit.setToolTipText("");
        jButtonRestockerProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRestockerProduitActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonRestockerProduit);

        jButtonAjouterGateau.setBackground(FONDBT);
        jButtonAjouterGateau.setFont(FONT);
        jButtonAjouterGateau.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAjouterGateau.setText("Ajouter produit");
        jButtonAjouterGateau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjouterGateauActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonAjouterGateau);

        jPanelListGateau.add(jPanel3, java.awt.BorderLayout.SOUTH);

        jPanelConteneur.add(jPanelListGateau, "cardListGateau");

        jPanelAjoutGateau.setOpaque(false);
        jPanelAjoutGateau.setLayout(new java.awt.GridBagLayout());

        jLabelNom.setFont(FONT);
        jLabelNom.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNom.setText("Nom :");
        jLabelNom.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAjoutGateau.add(jLabelNom, gridBagConstraints);

        jTextFieldNom.setColumns(20);
        jTextFieldNom.setFont(FONT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanelAjoutGateau.add(jTextFieldNom, gridBagConstraints);

        jLabelPrix.setFont(FONT);
        jLabelPrix.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPrix.setText("Prix :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAjoutGateau.add(jLabelPrix, gridBagConstraints);

        jSpinnerPrix.setFont(FONT);
        jSpinnerPrix.setModel(new javax.swing.SpinnerNumberModel(1.0f, 1.0f, null, 1.0f));
        jSpinnerPrix.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanelAjoutGateau.add(jSpinnerPrix, gridBagConstraints);

        jLabelDescription.setFont(FONT);
        jLabelDescription.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDescription.setText("Description (gouts) :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAjoutGateau.add(jLabelDescription, gridBagConstraints);

        jTextAreaDescription.setColumns(20);
        jTextAreaDescription.setFont(FONT);
        jTextAreaDescription.setLineWrap(true);
        jTextAreaDescription.setRows(5);
        jScrollPaneDescription.setViewportView(jTextAreaDescription);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanelAjoutGateau.add(jScrollPaneDescription, gridBagConstraints);

        jLabelLienImg.setFont(FONT);
        jLabelLienImg.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLienImg.setText("Emplacement de l'image :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAjoutGateau.add(jLabelLienImg, gridBagConstraints);

        jPanelChoixLienImg.setOpaque(false);
        jPanelChoixLienImg.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jTextFieldLienImg.setFont(FONT);
        jTextFieldLienImg.setText("Choose file");
        jTextFieldLienImg.setEnabled(false);
        jPanelChoixLienImg.add(jTextFieldLienImg);

        jButtonBrowseLienImg.setFont(FONT);
        jButtonBrowseLienImg.setText("Browse");
        jButtonBrowseLienImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBrowseLienImgActionPerformed(evt);
            }
        });
        jPanelChoixLienImg.add(jButtonBrowseLienImg);

        jButtonUploadLienImg.setFont(FONT);
        jButtonUploadLienImg.setText("Upload");
        jPanelChoixLienImg.add(jButtonUploadLienImg);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanelAjoutGateau.add(jPanelChoixLienImg, gridBagConstraints);

        jLabelQuantite.setFont(FONT);
        jLabelQuantite.setForeground(new java.awt.Color(255, 255, 255));
        jLabelQuantite.setText("Nombre de gateaux produits :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAjoutGateau.add(jLabelQuantite, gridBagConstraints);

        jSpinnerQuantite.setFont(FONT);
        jSpinnerQuantite.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanelAjoutGateau.add(jSpinnerQuantite, gridBagConstraints);

        jButtonValider.setBackground(FONDBT);
        jButtonValider.setFont(FONT);
        jButtonValider.setForeground(new java.awt.Color(255, 255, 255));
        jButtonValider.setText("Valider");
        jButtonValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValiderActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanelAjoutGateau.add(jButtonValider, gridBagConstraints);

        jButtonRetour.setBackground(FONDBT);
        jButtonRetour.setFont(FONT);
        jButtonRetour.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRetour.setText("Retour");
        jButtonRetour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRetourActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAjoutGateau.add(jButtonRetour, gridBagConstraints);

        jLabelDescriptionGen.setFont(FONT);
        jLabelDescriptionGen.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDescriptionGen.setText("Description (général) :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAjoutGateau.add(jLabelDescriptionGen, gridBagConstraints);

        jTextAreaDescriptionGen.setColumns(20);
        jTextAreaDescriptionGen.setFont(FONT);
        jTextAreaDescriptionGen.setLineWrap(true);
        jTextAreaDescriptionGen.setRows(5);
        jScrollPaneDescriptionGen.setViewportView(jTextAreaDescriptionGen);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanelAjoutGateau.add(jScrollPaneDescriptionGen, gridBagConstraints);

        jPanelConteneur.add(jPanelAjoutGateau, "CardAjoutGateau");

        jPanelAffichageCommande.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanelAffichageCommande.setOpaque(false);
        jPanelAffichageCommande.setLayout(new java.awt.BorderLayout(20, 10));

        jLabel1.setFont(FONT_BIG);
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(recupererEtRedimensionnerIMG("/patisseriegestionnaire/asset/commandeIcone.png"));
        jLabel1.setText("Commandes en Click and Collect :");
        jLabel1.setToolTipText("");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanelAffichageCommande.add(jLabel1, java.awt.BorderLayout.NORTH);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.BorderLayout());

        jButtonValiderCAC.setBackground(FONDBT);
        jButtonValiderCAC.setFont(FONT);
        jButtonValiderCAC.setForeground(new java.awt.Color(255, 255, 255));
        jButtonValiderCAC.setText("Valider commande");
        jButtonValiderCAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValiderCACActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonValiderCAC, java.awt.BorderLayout.EAST);

        jPanelAffichageCommande.add(jPanel1, java.awt.BorderLayout.SOUTH);

        jScrollPane1.setBackground(FOND3);

        clickAndCollectJTable1.setBackground(FOND3);
        clickAndCollectJTable1.setOpaque(false);
        clickAndCollectJTable1.getTableHeader().setBackground(FOND2);
        clickAndCollectJTable1.getTableHeader().setFont(FONT_SMALL);
        clickAndCollectJTable1.getTableHeader().setForeground(java.awt.Color.WHITE);
        jScrollPane1.setViewportView(clickAndCollectJTable1);

        jPanelAffichageCommande.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanelConteneur.add(jPanelAffichageCommande, "cardListCommande");

        jPanelListCommandePersonnalise.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanelListCommandePersonnalise.setOpaque(false);
        jPanelListCommandePersonnalise.setLayout(new java.awt.BorderLayout(0, 10));

        jScrollPane2.setMinimumSize(new java.awt.Dimension(427, 300));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(1000, 402));

        listCommandePersojTable1.setBackground(FOND3);
        listCommandePersojTable1.getTableHeader().setBackground(FOND2);
        listCommandePersojTable1.getTableHeader().setFont(FONT_SMALL);
        listCommandePersojTable1.getTableHeader().setForeground(java.awt.Color.WHITE);
        jScrollPane2.setViewportView(listCommandePersojTable1);

        jPanelListCommandePersonnalise.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jLabel2.setFont(FONT_BIG);
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(recupererEtRedimensionnerIMG("/patisseriegestionnaire/asset/CommandePersonnalisee.png"));
        jLabel2.setText("Commandes Personnalisés :");
        jPanelListCommandePersonnalise.add(jLabel2, java.awt.BorderLayout.NORTH);

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.BorderLayout());

        jButtonAnnulerComPerso.setBackground(FONDBT);
        jButtonAnnulerComPerso.setFont(FONT);
        jButtonAnnulerComPerso.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAnnulerComPerso.setText("Annuler commande");
        jButtonAnnulerComPerso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnnulerComPersoActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonAnnulerComPerso, java.awt.BorderLayout.WEST);

        jButtonValiderComPerso.setBackground(FONDBT);
        jButtonValiderComPerso.setFont(FONT);
        jButtonValiderComPerso.setForeground(new java.awt.Color(255, 255, 255));
        jButtonValiderComPerso.setText("Marquer comme fait");
        jButtonValiderComPerso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValiderComPersoActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonValiderComPerso, java.awt.BorderLayout.EAST);

        jPanelListCommandePersonnalise.add(jPanel4, java.awt.BorderLayout.SOUTH);

        jPanelConteneur.add(jPanelListCommandePersonnalise, "cardListCommandePersonnalise");

        jPanelMenu.setBackground(FOND2);
        jPanelMenu.setLayout(new java.awt.GridBagLayout());

        jButtonMenuCAC.setBackground(FONDBT);
        jButtonMenuCAC.setFont(FONT_BIG);
        jButtonMenuCAC.setForeground(new java.awt.Color(255, 255, 255));
        jButtonMenuCAC.setText("Liste commande click and collect");
        jButtonMenuCAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMenuCACActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanelMenu.add(jButtonMenuCAC, gridBagConstraints);

        jButtonMenuCal.setBackground(FONDBT);
        jButtonMenuCal.setFont(FONT_BIG);
        jButtonMenuCal.setForeground(new java.awt.Color(255, 255, 255));
        jButtonMenuCal.setText("Calendrier");
        jButtonMenuCal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMenuCalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelMenu.add(jButtonMenuCal, gridBagConstraints);

        jButtonMenuGat.setBackground(FONDBT);
        jButtonMenuGat.setFont(FONT_BIG);
        jButtonMenuGat.setForeground(new java.awt.Color(255, 255, 255));
        jButtonMenuGat.setText("Liste des produits");
        jButtonMenuGat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMenuGatActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanelMenu.add(jButtonMenuGat, gridBagConstraints);

        jButtonMenuComPerso.setBackground(FONDBT);
        jButtonMenuComPerso.setFont(FONT_BIG);
        jButtonMenuComPerso.setForeground(new java.awt.Color(255, 255, 255));
        jButtonMenuComPerso.setText("Liste commande personnalisée");
        jButtonMenuComPerso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMenuComPersoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelMenu.add(jButtonMenuComPerso, gridBagConstraints);

        jPanelConteneur.add(jPanelMenu, "cardMenu");

        jPanelCaisse.setOpaque(false);
        jPanelCaisse.setLayout(new java.awt.BorderLayout());

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jScrollPane4.setMinimumSize(new java.awt.Dimension(200, 300));
        jScrollPane4.setOpaque(false);
        jScrollPane4.setPreferredSize(new java.awt.Dimension(258, 300));

        jList1.setModel(new patisseriegestionnaire.ui.JlistModelTicket());
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setOpaque(false);
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jList1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jScrollPane4, gridBagConstraints);

        jLabel3.setText("Total : ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jLabel3, gridBagConstraints);

        jButton1.setBackground(FONDBT);
        jButton1.setFont(FONT);
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Valider");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jButton1, gridBagConstraints);

        jButton2.setBackground(FONDBT);
        jButton2.setFont(FONT);
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Annuler");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jButton2, gridBagConstraints);

        jPanelCaisse.add(jPanel2, java.awt.BorderLayout.LINE_END);

        jScrollPane5.setBackground(FOND1);
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanelCaisse1.setBackground(FOND1);
        jPanelCaisse1.setOpaque(true);
        jScrollPane5.setViewportView(jPanelCaisse1);

        jPanelCaisse.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        jPanelConteneur.add(jPanelCaisse, "cardCaisse");

        jPanelCalendar.setOpaque(false);
        jPanelCalendar.setLayout(new java.awt.CardLayout());

        calendarCustom1.setBackground(FOND3);
        calendarCustom1.setColorText(FONDTITTRE);
        calendarCustom1.setBackground1(FOND2);
        jPanelCalendar.add(calendarCustom1, "card2");

        jPanelConteneur.add(jPanelCalendar, "cardCalendrier");

        getContentPane().add(jPanelConteneur, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValiderActionPerformed
        Gateau g = new Gateau();
        
        String nom = jTextFieldNom.getText();
        float prix = (float) jSpinnerPrix.getValue();
        String description = jTextAreaDescription.getText();
        int quantite = (int) jSpinnerQuantite.getValue();
        String lien_img = jTextFieldLienImg.getText();
        if (!"".equals(nom) && nom.length()<21){
            if (!"".equals(description) && description.length()<201){
                if ((new File(lien_img).isFile())){
                    g.setNom(nom);
                    g.setDescription(description);
                    g.setPrix(prix);
                    g.setQuantite(quantite);
                    g.setLienImg(new File(lien_img).getName());
                    g.setDescriptionGeneral(this.jTextAreaDescriptionGen.getText());
                    System.out.println(g);
                    JOptionPane.showMessageDialog(this, "Le produit :\n"+g+"\n A été ajouter.", "Ajout", JOptionPane.INFORMATION_MESSAGE);
                    if (interfaceJFrame!=null){
                        if (!interfaceJFrame.addGateau(g))
                            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du gateau", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }  
                }else
                    JOptionPane.showMessageDialog(this, "L'image selectionner n'est pas un fichier", "Erreur", JOptionPane.ERROR_MESSAGE);        
            }else
                JOptionPane.showMessageDialog(this, "Veuiller saisir une description de 200 caractère maximum", "Erreur", JOptionPane.ERROR_MESSAGE);
        }else
            JOptionPane.showMessageDialog(this, "Veuiller saisir un nom de 20 caractère maximum", "Erreur", JOptionPane.ERROR_MESSAGE);
        
            
       
    }//GEN-LAST:event_jButtonValiderActionPerformed

    private void jButtonListCommandeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListCommandeActionPerformed
        changeVue("cardListCommande");      
    }//GEN-LAST:event_jButtonListCommandeActionPerformed

    private void jButtonValiderCACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValiderCACActionPerformed
        int indice = clickAndCollectJTable1.getSelectedRow();
        if (indice != -1){ 
            if (interfaceJFrame!=null){
                        Commande c=clickAndCollectJTable1.getCommande(indice);
                        c.setReady(true);
                        if (!interfaceJFrame.validerCommande(c)){
                            JOptionPane.showMessageDialog(this, "Erreur lors de la validation de la commande", "Erreur", JOptionPane.ERROR_MESSAGE);
                            c.setReady(false);
                        }else
                            clickAndCollectJTable1.validerCommande(indice);
            }else
                clickAndCollectJTable1.validerCommande(indice);
            clickAndCollectJTable1.repaint();
        }else
            JOptionPane.showMessageDialog(this, "Veuiller selectionner une ligne","Attention",JOptionPane.WARNING_MESSAGE);
        
    }//GEN-LAST:event_jButtonValiderCACActionPerformed

    private void jButtonListGateauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListGateauActionPerformed
        changeVue("cardListGateau");
    }//GEN-LAST:event_jButtonListGateauActionPerformed

    private void jButtonAjouterGateauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjouterGateauActionPerformed
         changeVue("CardAjoutGateau");
    }//GEN-LAST:event_jButtonAjouterGateauActionPerformed

    private void jButtonRetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRetourActionPerformed
        // TODO add your handling code here:
        changeVue("cardListGateau");
    }//GEN-LAST:event_jButtonRetourActionPerformed

    private void jButtonBrowseLienImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBrowseLienImgActionPerformed
        // TODO add your handling code here:
        int retour = jFileChooserLienImg.showOpenDialog(this);
        if (retour == JFileChooser.APPROVE_OPTION){
            jTextFieldLienImg.setText(jFileChooserLienImg.getSelectedFile().getAbsolutePath());
            
            
        }
    }//GEN-LAST:event_jButtonBrowseLienImgActionPerformed

    private void jToggleButtonEditionProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonEditionProduitActionPerformed
        // TODO add your handling code here:
        if (jToggleButtonEditionProduit.isSelected()){
            listGateaujTable1.commencerEdition();
            jToggleButtonEditionProduit.setText("Sortir mode edition");
        }else{
            listGateaujTable1.finirEdition();
            jToggleButtonEditionProduit.setText("Editer produits");
        }
            
        
    }//GEN-LAST:event_jToggleButtonEditionProduitActionPerformed

    private void jButtonSupprimerProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprimerProduitActionPerformed
        int indice = listGateaujTable1.getSelectedRow();
        if (indice != -1){
            int reponse = JOptionPane.showConfirmDialog(this, "Etes vous sur de supprimer le gateau", "Attention", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (reponse == JOptionPane.OK_OPTION){
                if (interfaceJFrame!=null){
                        if (!interfaceJFrame.removeGateau(listGateaujTable1.getGateau(indice)))
                            JOptionPane.showMessageDialog(this, "Erreur lors de la suppresion du gateau", "Erreur", JOptionPane.ERROR_MESSAGE);
                        else
                            listGateaujTable1.supprimerGateau(indice);
                }else
                    listGateaujTable1.supprimerGateau(indice);
            }
        }else
            JOptionPane.showMessageDialog(this, "Veuiller selectionner une ligne","Attention",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_jButtonSupprimerProduitActionPerformed

    private void jButtonAnnulerComPersoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnnulerComPersoActionPerformed
        // TODO add your handling code here:
        int indice = listCommandePersojTable1.getSelectedRow();
        if (indice != -1){
            int reponse = JOptionPane.showConfirmDialog(this, "Etes vous sur de supprimer la commande", "Attention", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (reponse == JOptionPane.OK_OPTION){
                if (interfaceJFrame!=null){
                        if (!interfaceJFrame.removeCommandePerso(listCommandePersojTable1.getCommande(indice)))
                            JOptionPane.showMessageDialog(this, "Erreur lors de la suppresion de la commande", "Erreur", JOptionPane.ERROR_MESSAGE);
                        else 
                            listCommandePersojTable1.supprimerCommande(indice);
                }else
                listCommandePersojTable1.supprimerCommande(indice);
            }
        }else
            JOptionPane.showMessageDialog(this, "Veuiller selectionner une ligne","Attention",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_jButtonAnnulerComPersoActionPerformed

    private void jButtonValiderComPersoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValiderComPersoActionPerformed
        int indice = listCommandePersojTable1.getSelectedRow();
        if (indice != -1){ 
            if (interfaceJFrame!=null){
                        if (!interfaceJFrame.validerCommandePerso(listCommandePersojTable1.getCommande(indice)))
                            JOptionPane.showMessageDialog(this, "Erreur lors de la validation de la commande", "Erreur", JOptionPane.ERROR_MESSAGE);
                        else
                            listCommandePersojTable1.validerCommande(indice);
                            
                }
            else
            listCommandePersojTable1.validerCommande(indice);
        }else
            JOptionPane.showMessageDialog(this, "Veuiller selectionner une ligne","Attention",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_jButtonValiderComPersoActionPerformed

    private void jButtonListeCommandPersonnaliserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListeCommandPersonnaliserActionPerformed
        // TODO add your handling code here:
        changeVue("cardListCommandePersonnalise");
    }//GEN-LAST:event_jButtonListeCommandPersonnaliserActionPerformed

    private void jButtonMenuCACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMenuCACActionPerformed
        changeVue("cardListCommande");   
    }//GEN-LAST:event_jButtonMenuCACActionPerformed

    private void jButtonMenuGatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMenuGatActionPerformed
       changeVue("cardListGateau");
    }//GEN-LAST:event_jButtonMenuGatActionPerformed

    private void jButtonMenuComPersoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMenuComPersoActionPerformed
        changeVue("cardListCommandePersonnalise");
    }//GEN-LAST:event_jButtonMenuComPersoActionPerformed

    private void jButtonRestockerProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRestockerProduitActionPerformed
        int indice = listGateaujTable1.getSelectedRow();
        if (indice != -1){
            int reponse = QuantiteJDialog.show(this, listGateaujTable1.getGateau(indice), FOND1, FONDBT, FONT);
            
            if (reponse != -1){
                if (interfaceJFrame!=null){
                        if (!interfaceJFrame.restockerGateau(listGateaujTable1.getGateau(indice),reponse))
                            JOptionPane.showMessageDialog(this, "Erreur lors du restockage du gateau", "Erreur", JOptionPane.ERROR_MESSAGE);
                        else
                            listGateaujTable1.restocker(indice,reponse);
                }else
                    listGateaujTable1.restocker(indice,reponse);
            }
        }else
            JOptionPane.showMessageDialog(this, "Veuiller selectionner une ligne","Attention",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_jButtonRestockerProduitActionPerformed

    private void jButtonCaisseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCaisseActionPerformed
        changeVue("cardCaisse");
    }//GEN-LAST:event_jButtonCaisseActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Confirmer annulation du ticket", "Confirmer", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION){
            jList1.setModel(new JlistModelTicket());
            jLabel3.setText("Total : ");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Confirmer validation du ticket", "Confirmer", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
            
            if (JOptionPane.showConfirmDialog(this, "Imprimer le ticket ?", "Confirmer", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, new ImageIcon(new ImageIcon(getClass().getResource("/patisseriegestionnaire/asset/imprimanteIcon.png")).getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT))
                    )==JOptionPane.YES_OPTION){
                ((JlistModelTicket) jList1.getModel()).getTicket().imprimer();
            }
            

            
            if (interfaceJFrame!=null){
                if (interfaceJFrame.ajouterCommandeComptoire(((JlistModelTicket)jList1.getModel()).getTicket())){
                    jList1.setModel(new JlistModelTicket());
                    jLabel3.setText("Total : ");
                
                }else
                    JOptionPane.showMessageDialog(this, "Erreur lors de la validation vérifier saisie et recommencer", "erreur", JOptionPane.ERROR_MESSAGE);
                
            }else{
                jList1.setModel(new JlistModelTicket());
                jLabel3.setText("Total : ");
            }
            
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonCalendrierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalendrierActionPerformed
        changeVue("cardCalendrier");
    }//GEN-LAST:event_jButtonCalendrierActionPerformed

    private void jButtonMenuCalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMenuCalActionPerformed
       changeVue("cardCalendrier");
    }//GEN-LAST:event_jButtonMenuCalActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        if (jList1.getSelectedIndex()!=-1){
            ElementdUnTicket elmt;
            JlistModelTicket model = (JlistModelTicket) jList1.getModel();
            elmt = model.getTicket().get(jList1.getSelectedIndex());
            int new_qt = QuantiteJDialog.show(this, elmt.getGateau(), elmt.getQuantite(),FOND1,FONDBT,FONT);
            model.getTicket().modifierQt(jList1.getSelectedIndex(), new_qt);
            jLabel3.setText("Total : "+model.getPrixTotal());
            jList1.updateUI();
            jPanel2.updateUI();
            
            
        }
    }//GEN-LAST:event_jList1MouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private patisseriegestionnaire.calendrier.main.CalendarCustom calendarCustom1;
    private patisseriegestionnaire.ui.jtables.CACjTable clickAndCollectJTable1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAjouterGateau;
    private javax.swing.JButton jButtonAnnulerComPerso;
    private javax.swing.JButton jButtonBrowseLienImg;
    private javax.swing.JButton jButtonCaisse;
    private javax.swing.JButton jButtonCalendrier;
    private javax.swing.JButton jButtonListCommande;
    private javax.swing.JButton jButtonListGateau;
    private javax.swing.JButton jButtonListeCommandPersonnaliser;
    private javax.swing.JButton jButtonMenuCAC;
    private javax.swing.JButton jButtonMenuCal;
    private javax.swing.JButton jButtonMenuComPerso;
    private javax.swing.JButton jButtonMenuGat;
    private javax.swing.JButton jButtonRestockerProduit;
    private javax.swing.JButton jButtonRetour;
    private javax.swing.JButton jButtonSupprimerProduit;
    private javax.swing.JButton jButtonUploadLienImg;
    private javax.swing.JButton jButtonValider;
    private javax.swing.JButton jButtonValiderCAC;
    private javax.swing.JButton jButtonValiderComPerso;
    private javax.swing.JFileChooser jFileChooserLienImg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelDescription;
    private javax.swing.JLabel jLabelDescriptionGen;
    private javax.swing.JLabel jLabelLienImg;
    private javax.swing.JLabel jLabelNom;
    private javax.swing.JLabel jLabelPrix;
    private javax.swing.JLabel jLabelQuantite;
    private javax.swing.JLabel jLabelTitreListGateau;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelAffichageCommande;
    private javax.swing.JPanel jPanelAjoutGateau;
    private javax.swing.JPanel jPanelCaisse;
    private patisseriegestionnaire.ui.jpanel.JPanelCaisse jPanelCaisse1;
    private javax.swing.JPanel jPanelCalendar;
    private javax.swing.JPanel jPanelChoixLienImg;
    private javax.swing.JPanel jPanelConteneur;
    private javax.swing.JPanel jPanelListCommandePersonnalise;
    private javax.swing.JPanel jPanelListGateau;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelNavigation;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPaneDescription;
    private javax.swing.JScrollPane jScrollPaneDescriptionGen;
    private javax.swing.JSpinner jSpinnerPrix;
    private javax.swing.JSpinner jSpinnerQuantite;
    private javax.swing.JTextArea jTextAreaDescription;
    private javax.swing.JTextArea jTextAreaDescriptionGen;
    private javax.swing.JTextField jTextFieldLienImg;
    private javax.swing.JTextField jTextFieldNom;
    private javax.swing.JToggleButton jToggleButtonEditionProduit;
    private patisseriegestionnaire.ui.jtables.ListCommandePersojTable listCommandePersojTable1;
    private patisseriegestionnaire.ui.jtables.ListGateaujTable listGateaujTable1;
    // End of variables declaration//GEN-END:variables
   
    
   
}
