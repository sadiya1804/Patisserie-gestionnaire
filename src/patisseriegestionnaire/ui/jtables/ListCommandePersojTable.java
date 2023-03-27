/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patisseriegestionnaire.ui.jtables;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.TableCellRenderer;
import patisseriegestionnaire.models.CommandePersonnalise;

/**
 *
 * @author roman
 */
public class ListCommandePersojTable extends JTable {
      
    LcomPersojTableListener listComListener = null;
    
   public ListCommandePersojTable(){
        super();
        
        super.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        super.setModel(new ListCommandePersoTableModel());
        setColumnModel();
    }
   
   public ListCommandePersojTable(ArrayList<CommandePersonnalise> l){
        super();
        
        super.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        super.setModel(new ListCommandePersoTableModel(l));
        setColumnModel();
    }
    
   public void addLcomPersolistener(LcomPersojTableListener l){
        listComListener = l;
    }
   
   
    public void setNewModel(ArrayList<CommandePersonnalise> l) {
        this.setModel(new ListCommandePersoTableModel(l));
        setColumnModel();
    }
    
    private void setColumnModel(){
        
        super.getColumnModel().getColumn(9).setResizable(false);
        super.getColumnModel().getColumn(9).setMaxWidth(80);
        super.getColumnModel().getColumn(0).setResizable(false);
        super.getColumnModel().getColumn(0).setMaxWidth(80);
        
        super.getColumnModel().getColumn(3).setResizable(false);
        super.getColumnModel().getColumn(3).setMaxWidth(60);
        
        super.getColumnModel().getColumn(5).setResizable(false);
        super.getColumnModel().getColumn(5).setMinWidth(300);
        
        super.setRowHeight(30);
        super.getColumnModel().getColumn(9).setCellEditor(new ReadyCellEditor(this));
        
        super.getColumnModel().getColumn(9).setCellRenderer(new ColorReadyRenderer());
    }
   
   public CommandePersonnalise getCommande(int indice){
       ListCommandePersoTableModel m = (ListCommandePersoTableModel) this.getModel();
       if (indice < m.getRowCount()){
           return m.contains.get(indice);
       }
       return null;
   }

    public void supprimerCommande(int indice) {
       ListCommandePersoTableModel m = (ListCommandePersoTableModel) this.getModel();
       CommandePersonnalise com = m.contains.get(indice);
       com.setDeleted();
       this.repaint();
    }

    public void validerCommande(int indice) {
        ListCommandePersoTableModel m = (ListCommandePersoTableModel) this.getModel();
        m.contains.get(indice).setPret(true);
        m.fireTableRowsUpdated(indice, indice);
    }
   
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, 
         int row, int column) 
         {
            Component c = super.prepareRenderer(renderer, row, column);
            
            ListCommandePersoTableModel m = (ListCommandePersoTableModel) this.getModel();
            if (m.contains.get(row).isDeleted()){
                c.setBackground(Color.BLACK);
                c.setForeground(Color.GRAY);
                
            }else{
                c.setBackground(this.getBackground());
                c.setForeground(this.getForeground());
                if (column==9){
                    c.setBackground(((m.contains.get(row).isPret())? java.awt.Color.GREEN : java.awt.Color.RED));
                }
            }
            
            return c;
         }
 
    
    
   private class ListCommandePersoTableModel extends javax.swing.table.AbstractTableModel {
    
    String[] tittle = {"Forme","Gout Viennoise","Gout Crème","taille","Image","Text Personnalisé","Nom Client","Prénom Client","Date et heure de récupération","Pret"};
    ArrayList<CommandePersonnalise> contains = new ArrayList<CommandePersonnalise>();
   
    
    public ListCommandePersoTableModel(){
        super();
    }

    
    public ListCommandePersoTableModel(ArrayList<CommandePersonnalise> l){
        super();
        this.contains =l;
    }

    @Override
    public int getRowCount() {
        return contains.size();
    }

    @Override
    public int getColumnCount() {
        return tittle.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return tittle[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CommandePersonnalise c = contains.get(rowIndex);
        switch (columnIndex){
            case 0:
                return c.getForme();
            case 1:
                return c.getGout_viennoise();
            case 2: 
                return c.getGout_creme();
            case 3: 
                return c.getTaille();
            case 4:
                return c.getLien_img();
            case 5:
                return c.getTexte_perso();
            case 6:
                return c.getNomClt();
                
            case 7:
                return c.getPrenomClt();
            case 8:
                return c.getDtEtHeureRecuperation();
                
            case 9:
                return c.isPret();
            default:
                return null;
        }
    }
    @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return rowIndex >= 0 && columnIndex == 9 && !contains.get(rowIndex).isDeleted();
        }
        
        /**
         * Lorsque le contenu d'une cellule vient d'être modifiée par l'intermédiaire d'un editeur et que la valeur doit être prise en compte au niveau de l'affichage et de l'objet concerné
         * @param value Correspond à la nouvelle valeur de la cellule. Celle-ci doit impérativement être prise en compte, sans quoi la valeur tombe dans le néant
         * @param rowIndex Correspond au numéro de la ligne de la cellule qui a été modifiée
         * @param columnIndex Correspond au numéro de la colonne de la cellule qui a été modifiée
         */
        @Override
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            CommandePersonnalise c = (CommandePersonnalise) contains.get(rowIndex);
            c.setPret((boolean) value);
            if (listComListener!=null)
                if (listComListener.validerCommande(c)){
                    super.setValueAt(value, rowIndex, columnIndex);
                }else{
                    super.setValueAt(!(boolean) value, rowIndex, columnIndex);
                    c.setPret(!(boolean) value);
                }   
            else 
                 super.setValueAt(value, rowIndex, columnIndex);
        }
    
   

    }
   
     private class ReadyCellEditor  extends javax.swing.AbstractCellEditor implements javax.swing.table.TableCellEditor {

        
        
    //ATTRIBUT
        private final javax.swing.JCheckBox check;
             
        
        
    //CONSTRUCTOR
        
        public ReadyCellEditor(javax.swing.JTable table) {
            this.check = new javax.swing.JCheckBox();
            check.setHorizontalAlignment(CENTER);
            
            check.setOpaque(false);
            this.check.addActionListener((java.awt.event.ActionEvent e) -> {
                stopCellEditing();
                table.clearSelection();
            });
        }
        
        
        
    //METHODES PUBLICS
        
        @Override
        public Object getCellEditorValue() {
            return  this.check.isSelected();
        }

 
        @Override
        public java.awt.Component getTableCellEditorComponent(javax.swing.JTable table, Object value, boolean isSelected, int row, int column) {
            this.check.setSelected((boolean) value);
            return this.check;
        }
        
        
    }
    
   private class ColorReadyRenderer extends javax.swing.table.DefaultTableCellRenderer  {

        
        
        /**
         * Renvoie le composant qui affiche la donnée de la cellule
         * @param table Correspond à la {@link javax.swing.JTable} concernée par l'affichage de la donnée d'une cellule
         * @param value Correspond à l'objet à afficher dans la cellule
         * @param isSelected Détermine si la cellule est séelctionnée ou pas
         * @param hasFocus Détermine si la cellule a le focus
         * @param row Correspond au numéro de la ligne de la cellule à afficher
         * @param column Correspond au numéro de la colonne de la cellule à afficher
         * @return Retourne le composant qui affiche la donnée de la cellule
         */
        @Override
        public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            javax.swing.JLabel label = (javax.swing.JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setBackground((((boolean)value)? java.awt.Color.GREEN : java.awt.Color.RED));
            label.setText("");
            return label;
        }

        
        
    }
 
   public interface LcomPersojTableListener{
       public boolean validerCommande(CommandePersonnalise c);
   }
}
