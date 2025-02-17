/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.Product;
import utils.DatabaseUtils;

/**
 *
 * @author kiara
 */
public class ViewPage extends javax.swing.JPanel {

    /**
     * Creates new form ViewPage
     */
    private DatabaseUtils dbUtils;
    private NewProduct newProductForm;
    
    public ViewPage(NewProduct newProductForm) {
        initComponents();
        this.dbUtils = new DatabaseUtils();
        this.newProductForm = newProductForm;
        loadProductTable();
        
        productTable.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent me){
                int row = productTable.rowAtPoint(me.getPoint());
                if(row >=0){
                    int productId = (int) productTable.getValueAt(row, 0);
                    String productName = (String) productTable.getValueAt(row, 1);
                    double price = (double) productTable.getValueAt(row, 2);
                    //populate the form with product details
                    newProductForm.setProductDetails(productId, productName, price);
                }
            }
        });
    }
    public ViewPage() {
        initComponents();
        this.dbUtils = new DatabaseUtils();
        loadProductTable();
    }
    
    private void loadProductTable(){
        List<Product> products = dbUtils.getProducts();
        //initalize DefaultTableModel to manipulate data for the JTable
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        //clear existing rows
        model.setRowCount(0);
        
        for(Product product : products){
            model.addRow(new Object[]{
                product.getId(),
                product.getProductName(),
                product.getPrice()
            });
        }
    }
    
    public void refreshTable(){
        loadProductTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(204, 204, 204));

        productTable.setBackground(new java.awt.Color(255, 255, 255));
        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Product Name", "Unit Price"
            }
        ));
        productTable.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(productTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable productTable;
    // End of variables declaration//GEN-END:variables
}
