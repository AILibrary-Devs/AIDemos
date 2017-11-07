/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package images;

import common.ImageTools;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;

/**
 *
 * @author kwl
 */
public class ImageViewerFrame extends javax.swing.JFrame {

    {
        images = new Image[0][0];
        sourceImages = new HashMap<>();
    }

    /**
     * Creates new form ImageViewer
     *
     * @param images Images to be available for viewing
     */
    public ImageViewerFrame(Image[][] images) {
        initComponents();
        initCustomComponents();
        initData();

//        this.images = images;
    }

    private void initCustomComponents() {
        SpringLayout layout = new SpringLayout();
        pv = new PixelViewerPanel();

        jpnlPixelHolder.setLayout(layout);
        jpnlPixelHolder.add(pv);

        layout.putConstraint(SpringLayout.WEST, pv, 1, SpringLayout.WEST, jpnlPixelHolder);
        layout.putConstraint(SpringLayout.NORTH, pv, 1, SpringLayout.NORTH, jpnlPixelHolder);
        layout.putConstraint(SpringLayout.EAST, pv, 1, SpringLayout.EAST, jpnlPixelHolder);
        layout.putConstraint(SpringLayout.SOUTH, pv, 1, SpringLayout.SOUTH, jpnlPixelHolder);

        pv.setVisible(true);
    }

    private void initData() {
        sourceImages.put("Train 0", "images/mnist_train0.jpg");
        sourceImages.put("Train 1", "images/mnist_train1.jpg");
        sourceImages.put("Train 2", "images/mnist_train2.jpg");
        sourceImages.put("Train 3", "images/mnist_train3.jpg");
        sourceImages.put("Train 4", "images/mnist_train4.jpg");
        sourceImages.put("Train 5", "images/mnist_train5.jpg");
        sourceImages.put("Train 6", "images/mnist_train6.jpg");
        sourceImages.put("Train 7", "images/mnist_train7.jpg");
        sourceImages.put("Train 8", "images/mnist_train8.jpg");
        sourceImages.put("Train 9", "images/mnist_train9.jpg");
        sourceImages.put("Test 0", "images/mnist_test0.jpg");
        sourceImages.put("Test 1", "images/mnist_test1.jpg");
        sourceImages.put("Test 2", "images/mnist_test2.jpg");
        sourceImages.put("Test 3", "images/mnist_test3.jpg");
        sourceImages.put("Test 4", "images/mnist_test4.jpg");
        sourceImages.put("Test 5", "images/mnist_test5.jpg");
        sourceImages.put("Test 6", "images/mnist_test6.jpg");
        sourceImages.put("Test 7", "images/mnist_test7.jpg");
        sourceImages.put("Test 8", "images/mnist_test8.jpg");
        sourceImages.put("Test 9", "images/mnist_test9.jpg");

        jcbxSourceImages.removeAllItems();
        sourceImages.keySet().forEach((key) -> jcbxSourceImages.addItem(key));
    }

//<editor-fold defaultstate="collapsed" desc="Properties">
    private Image[][] images;
    PixelViewerPanel pv;
    HashMap<String, String> sourceImages;

    public void setSourceImage(String string) {
//        System.out.println(string + "  " + sourceImages.get(string));
        String path = sourceImages.get(string);
        if (path != null) {
            if (path.contains("test")) {
                setImageRows(30);
                setImageColumns(32);
            } else {
                setImageRows(76);
                setImageColumns(77);
            }
            
            Image image = ImageTools.loadImageFromResource(sourceImages.get(string));
            Image[][] images = ImageTools.getSubimageArrays(image, getImageRows(), getImageColumns());

            setImages(images);
        }
    }
    
    private int rows, columns;
    
    public void setImageRows(int rows){
        this.rows = rows;
        ((SpinnerNumberModel) jspinRow.getModel()).setMaximum(rows - 1);
    }
    
    public int getImageRows(){
        return rows;
    }
    
    public void setImageColumns(int columns){
        this.columns = columns;
        ((SpinnerNumberModel) jspinColumn.getModel()).setMaximum(columns - 1);
    }
    
    public int getImageColumns(){
        return columns;
    }

    public void setImages(Image[][] images) {
        this.images = images;
    }

    public void showImage() {
        showImage((int) jspinColumn.getValue(), (int) jspinRow.getValue(), jcbxFlattened.isSelected());
    }

    public void showImage(int x, int y, boolean flattened) {
        if ((x >= 0) && (x < images.length) && (y >= 0) && (y < images[0].length)) {
            if (pv != null) {
                pv.setFlattened(flattened);
                pv.setImage((BufferedImage) images[x][y]);
            }

            jlblImage.setIcon(new ImageIcon(images[x][y]));
        }
    }
//</editor-fold>

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlblImage = new javax.swing.JLabel();
        jcbxFlattened = new javax.swing.JCheckBox();
        jcbxSourceImages = new javax.swing.JComboBox<>();
        jlblRow = new javax.swing.JLabel();
        jlblRow1 = new javax.swing.JLabel();
        jlblRow2 = new javax.swing.JLabel();
        jspinRow = new javax.swing.JSpinner();
        jspinColumn = new javax.swing.JSpinner();
        jpnlPixelHolder = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jcbxFlattened.setText("Flatten Color Space");
        jcbxFlattened.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbxFlattenedActionPerformed(evt);
            }
        });

        jcbxSourceImages.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Train 1", "Train 2", "Train 3", "Train 4", "Train 5", "Train 6", "Train 7", "Train 8", "Train 9", "Test 0", "Test 1", "Test 2", "Test 3", "Test 4", "Test 5", "Test 6", "Test 7", "Test 8", "Test 9", " ", " " }));
        jcbxSourceImages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbxSourceImagesActionPerformed(evt);
            }
        });

        jlblRow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblRow.setText("Row");

        jlblRow1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblRow1.setText("Column");

        jlblRow2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblRow2.setText("Model");

        jspinRow.setModel(new javax.swing.SpinnerNumberModel(0, 0, 32, 1));
        jspinRow.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspinRowStateChanged(evt);
            }
        });

        jspinColumn.setModel(new javax.swing.SpinnerNumberModel(0, 0, 31, 1));
        jspinColumn.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspinColumnStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jspinRow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspinColumn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbxSourceImages, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbxFlattened))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlblRow, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlblRow1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlblRow2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblRow)
                            .addComponent(jlblRow1)
                            .addComponent(jlblRow2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jspinRow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jspinColumn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbxSourceImages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbxFlattened))
                        .addGap(0, 4, Short.MAX_VALUE))
                    .addComponent(jlblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jpnlPixelHolderLayout = new javax.swing.GroupLayout(jpnlPixelHolder);
        jpnlPixelHolder.setLayout(jpnlPixelHolderLayout);
        jpnlPixelHolderLayout.setHorizontalGroup(
            jpnlPixelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );
        jpnlPixelHolderLayout.setVerticalGroup(
            jpnlPixelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 446, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnlPixelHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnlPixelHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbxFlattenedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbxFlattenedActionPerformed
        showImage();
    }//GEN-LAST:event_jcbxFlattenedActionPerformed

    private void jspinRowStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspinRowStateChanged
        showImage();
    }//GEN-LAST:event_jspinRowStateChanged

    private void jspinColumnStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspinColumnStateChanged
        showImage();
    }//GEN-LAST:event_jspinColumnStateChanged

    private void jcbxSourceImagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbxSourceImagesActionPerformed
        setSourceImage((String) jcbxSourceImages.getSelectedItem());
    }//GEN-LAST:event_jcbxSourceImagesActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ImageViewerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ImageViewerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ImageViewerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ImageViewerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ImageViewerFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox jcbxFlattened;
    private javax.swing.JComboBox<String> jcbxSourceImages;
    private javax.swing.JLabel jlblImage;
    private javax.swing.JLabel jlblRow;
    private javax.swing.JLabel jlblRow1;
    private javax.swing.JLabel jlblRow2;
    private javax.swing.JPanel jpnlPixelHolder;
    private javax.swing.JSpinner jspinColumn;
    private javax.swing.JSpinner jspinRow;
    // End of variables declaration//GEN-END:variables

}
