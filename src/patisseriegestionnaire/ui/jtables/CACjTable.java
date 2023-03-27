
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
import patisseriegestionnaire.models.Commande;

/**
 *
 * @author roman
 */

public class CACjTable extends JTable{
    
    CACjTableListener cacListener = null;
    
   public CACjTable(){
        super();
        super.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        super.setModel(new ClickAndCollectTableModel());
        setColumnModel();
    }
   public CACjTable(ArrayList<Commande> lc){
        super();
        super.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        super.setModel(new ClickAndCollectTableModel(lc));
        setColumnModel();
    }
    
   public void addCAClistener(CACjTableListener l){
        cacListener = l;
    }
   
   private void setColumnModel(){
       super.getColumnModel().getColumn(6).setResizable(false);
        super.getColumnModel().getColumn(6).setMaxWidth(100);
        super.setRowHeight(30);
        super.getColumnModel().getColumn(1).setResizable(false);
        super.getColumnModel().getColumn(1).setMaxWidth(100);
        
        super.getColumnModel().getColumn(2).setResizable(false);
        super.getColumnModel().getColumn(2).setMaxWidth(100);
        
        
        super.getColumnModel().getColumn(6).setCellRenderer(new ColorReadyRenderer());
        
        super.getColumnModel().getColumn(6).setCellEditor(new ReadyCellEditor(this));
   }
   
   
   public void setNewModel(ArrayList<Commande> l) {
        this.setModel(new ClickAndCollectTableModel(l));
        setColumnModel();
    }
   
   
   public void updateTableAfterCommandeDeleted(){
       ClickAndCollectTableModel m = (ClickAndCollectTableModel) this.getModel();
       m.fireTableRowsDeleted(0, m.contains.size()-1);
   }
   
   public Commande getCommande(int indice){
       ClickAndCollectTableModel m = (ClickAndCollectTableModel) this.getModel();
       if (indice < m.getRowCount()){
           return m.contains.get(indice);
       }
       return null;
   }

    public void supprimerCommande(int indice) {
       ClickAndCollectTableModel m = (ClickAndCollectTableModel) this.getModel();
       if (indice < m.getRowCount()){
           m.contains.remove(indice);
           if (m.contains.size()>0)
            m.fireTableRowsDeleted(0, m.contains.size()-1);
           else 
               m.fireTableRowsDeleted(0, 0);
       }
    }

    public void validerCommande(int indice) {
        ClickAndCollectTableModel m = (ClickAndCollectTableModel) this.getModel();
        Commande com = m.contains.get(indice);
        for (Commande c : m.contains){
            if (com.getId() == c.getId()){
                c.setLivree();
                c.setReady(true);
            }
        }
        this.repaint();
    }
   
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, 
         int row, int column) 
         {
            Component c = super.prepareRenderer(renderer, row, column);
            
            ClickAndCollectTableModel m = (ClickAndCollectTableModel) this.getModel();
             System.out.println(m.contains.get(row).isLivree());
            if (m.contains.get(row).isLivree()){
                c.setBackground(Color.GRAY);
                c.setForeground(Color.WHITE);          
            }
            else{
                c.setBackground(this.getBackground());
                c.setForeground(this.getForeground());
                if (column==6){
                    c.setBackground(((m.contains.get(row).isReady())? java.awt.Color.GREEN : java.awt.Color.RED));
                }
            }
            return c;
    }
    
 
    
    
   private class ClickAndCollectTableModel extends javax.swing.table.AbstractTableModel {
    
    String[] tittle = {"Nom gateau","quantité","prix total","nom Client","Prénom Client","date et heure de récupération","Préparé ?"};
    ArrayList<Commande> contains = new ArrayList<Commande>();
   
    
    public ClickAndCollectTableModel(){
        super();
    }

    
    public ClickAndCollectTableModel(ArrayList<Commande> l){
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
        Commande c = contains.get(rowIndex);
        switch (columnIndex){
            case 0:
                return c.getNomGateaux();
            case 1:
                return c.getQuantite();
            case 2:
                return c.getPrix();
            case 3:
                return c.getNomClient();
                
            case 4:
                return c.getPrenomClient();
            case 5:
                return c.getdtRecuperer();
            case 6:
                return c.isReady();
            default:
                return null;
        }
    }
    
     @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return rowIndex >= 0 && columnIndex == 6  && !contains.get(rowIndex).isLivree();
        }
        
        /**
         * Lorsque le contenu d'une cellule vient d'être modifiée par l'intermédiaire d'un editeur et que la valeur doit être prise en compte au niveau de l'affichage et de l'objet concerné
         * @param value Correspond à la nouvelle valeur de la cellule. Celle-ci doit impérativement être prise en compte, sans quoi la valeur tombe dans le néant
         * @param rowIndex Correspond au numéro de la ligne de la cellule qui a été modifiée
         * @param columnIndex Correspond au numéro de la colonne de la cellule qui a été modifiée
         */
        @Override
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            Commande c = (Commande) contains.get(rowIndex);
            c.setReady((boolean) value);
            if (cacListener!=null)
                if (cacListener.validerCommande(c))
                    super.setValueAt(value, rowIndex, columnIndex);
                else{
                     c.setReady(!((boolean) value));
                     super.setValueAt(!((boolean) value), rowIndex, columnIndex);
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
 
   public interface CACjTableListener{
       public boolean validerCommande(Commande c);
   }
}


