/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patisseriegestionnaire.ui.jtables;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellRenderer;
import patisseriegestionnaire.models.Gateau;

/**
 *
 * @author roman
 */
public class ListGateaujTable extends JTable {

    private boolean edition = false;
    private ListGateauListener listGateauListener = null;

    public ListGateaujTable() {
        super();
        super.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        super.setModel(new ListeGateauTableModel());
        setColumnModel();
    }

    public ListGateaujTable(ArrayList<Gateau> lg) {
        super();
        super.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        super.setModel(new ListeGateauTableModel(lg));
        setColumnModel();
    }

    private void setColumnModel() {

        super.setRowHeight(30);
        super.getColumnModel().getColumn(1).setResizable(false);
        super.getColumnModel().getColumn(1).setMaxWidth(100);
        super.getColumnModel().getColumn(4).setCellEditor(new IntegerCellEditor());
        super.getColumnModel().getColumn(1).setCellEditor(new FloatCellEditor());
        super.getColumnModel().getColumn(3).setCellEditor(new ImageCellEditor());
    }

    public void commencerEdition() {
        edition = true;
    }

    public void finirEdition() {
        edition = false;
    }

    public void supprimerGateau(int indice) {
        ListeGateauTableModel m = (ListeGateauTableModel) this.getModel();
        if (indice < m.getRowCount()) {
            m.contains.remove(indice);
            if (m.contains.size() > 0) {
                m.fireTableRowsDeleted(0, m.contains.size() - 1);
            } else {
                m.fireTableRowsDeleted(0, 0);
            }
        }
    }

    public void addListGateauListener(ListGateauListener l) {
        listGateauListener = l;
    }

    public Gateau getGateau(int indice) {
        ListeGateauTableModel m = (ListeGateauTableModel) this.getModel();
        if (indice < m.getRowCount()) {
            return m.contains.get(indice);
        }
        return null;
    }

    public void setNewModel(ArrayList<Gateau> allGateau) {
        this.setModel(new ListeGateauTableModel(allGateau));
        setColumnModel();
    }

    public void restocker(int indice, int reponse) {
        ListeGateauTableModel m = (ListeGateauTableModel) this.getModel();
        if (indice < m.getRowCount()) {
            (m.contains.get(indice)).setQuantite(reponse);
             m.fireTableCellUpdated(indice, 4);
        }
    }
    
    private class ListeGateauTableModel extends javax.swing.table.AbstractTableModel {

        String[] tittle = {"Nom", "prix", "description", "emplacement image", "Nombre Restant"};
        ArrayList<Gateau> contains = new ArrayList<Gateau>();

        public ListeGateauTableModel() {
            super();
        }

        public ListeGateauTableModel(ArrayList<Gateau> l) {
            super();
            this.contains = l;
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
            Gateau g = contains.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return g.getNom();
                case 1:
                    return g.getPrix();
                case 2:
                    return g.getDescription();
                case 3:
                    return g.getLienImg();

                case 4:
                    return g.getQuantite();
                default:
                    return null;
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return edition;
        }

        @Override
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            Gateau g = (Gateau) contains.get(rowIndex);
            
            
            switch (columnIndex) {
                case 0:
                    g.setNom((String) value);
                    break;
                case 1:
                    g.setPrix((float) value);
                    break;
                case 2:
                    g.setDescription((String) value);
                    break;
                case 3:
                    if ((new File((String) value)).isFile()) {
                        g.setLienImg((String) value);
                    }
                    break;
                case 4:
                    g.setQuantite((int) value);
                default:

            }
            super.setValueAt(value, rowIndex, columnIndex);
            if (listGateauListener != null) {
                listGateauListener.editionGateau(g);
                
            }
        }

    }

    private class IntegerCellEditor extends javax.swing.AbstractCellEditor implements javax.swing.table.TableCellEditor {

        //ATTRIBUT
        private final javax.swing.JSpinner spin;

        //CONSTRUCTOR
        public IntegerCellEditor() {
            this.spin = new javax.swing.JSpinner();
            this.spin.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        }

        //METHODES PUBLICS
        @Override
        public Object getCellEditorValue() {
            return this.spin.getValue();
        }

        @Override
        public java.awt.Component getTableCellEditorComponent(javax.swing.JTable table, Object value, boolean isSelected, int row, int column) {
            this.spin.setValue((int) value);
            return this.spin;
        }

    }

    private class FloatCellEditor extends javax.swing.AbstractCellEditor implements javax.swing.table.TableCellEditor {

        //ATTRIBUT
        private final javax.swing.JSpinner spin;

        //CONSTRUCTOR
        public FloatCellEditor() {
            this.spin = new javax.swing.JSpinner();
            this.spin.setModel(new javax.swing.SpinnerNumberModel(1.0f, 1.0f, null, 1.0f));

        }

        //METHODES PUBLICS
        @Override
        public Object getCellEditorValue() {
            return this.spin.getValue();
        }

        @Override
        public java.awt.Component getTableCellEditorComponent(javax.swing.JTable table, Object value, boolean isSelected, int row, int column) {
            this.spin.setValue((float) value);
            return this.spin;
        }

    }

    private class ImageCellEditor extends javax.swing.AbstractCellEditor implements javax.swing.table.TableCellEditor {

        //ATTRIBUT
        private final javax.swing.JFileChooser fileChoose;
        private final javax.swing.JButton bt;
        private int retour;

        //CONSTRUCTOR
        public ImageCellEditor() {
            this.fileChoose = new javax.swing.JFileChooser();
            this.bt = new javax.swing.JButton();

            this.bt.setText("Browse");
            this.bt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (fileChoose.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        stopCellEditing();
                    }
                }
            });
            this.fileChoose.setDialogTitle("Selectionner image");
            this.fileChoose.setFileFilter(new FileNameExtensionFilter("Images", "bmp", "gif", "jpg", "jpeg", "png"));

        }

        //METHODES PUBLICS
        @Override
        public Object getCellEditorValue() {
            return fileChoose.getSelectedFile().getAbsolutePath();
        }

        @Override
        public java.awt.Component getTableCellEditorComponent(javax.swing.JTable table, Object value, boolean isSelected, int row, int column) {
            this.fileChoose.setSelectedFile(new File((String) value));

            return bt;
        }

    }

    public interface ListGateauListener {

        public void editionGateau(Gateau g);
    }

}
