/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package images;

import common.ImageToolbox;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
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
        System.out.println(string + "  " + sourceImages.get(string));
        String path = sourceImages.get(string);
        if (path != null) {
            Image image = ImageToolbox.loadImageFromResource(sourceImages.get(string));
            Image[][] images = ImageToolbox.getSubimageArrays(image, 31, 32);

            setImages(images);

        }
    }

    public void setImages(Image[][] images) {
        this.images = images;
    }

    public void showImage() {
        showImage((int) jspinX.getValue(), (int) jspinY.getValue(), jcbxFlattened.isSelected());
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
        jspinX = new javax.swing.JSpinner();
        jspinY = new javax.swing.JSpinner();
        jlblImage = new javax.swing.JLabel();
        jcbxFlattened = new javax.swing.JCheckBox();
        jcbxSourceImages = new javax.swing.JComboBox<>();
        jpnlPixelHolder = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jspinX.setModel(new javax.swing.SpinnerNumberModel(0, 0, 31, 1));
        jspinX.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspinXStateChanged(evt);
            }
        });

        jspinY.setModel(new javax.swing.SpinnerNumberModel(0, 0, 32, 1));
        jspinY.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspinYStateChanged(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspinX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspinY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbxSourceImages, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbxFlattened)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
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
                            .addComponent(jspinX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jspinY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbxFlattened)
                            .addComponent(jcbxSourceImages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 26, Short.MAX_VALUE))
                    .addComponent(jlblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jpnlPixelHolderLayout = new javax.swing.GroupLayout(jpnlPixelHolder);
        jpnlPixelHolder.setLayout(jpnlPixelHolderLayout);
        jpnlPixelHolderLayout.setHorizontalGroup(
            jpnlPixelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 526, Short.MAX_VALUE)
        );
        jpnlPixelHolderLayout.setVerticalGroup(
            jpnlPixelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
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

    private void jspinYStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspinYStateChanged
        showImage();
    }//GEN-LAST:event_jspinYStateChanged

    private void jspinXStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspinXStateChanged
        showImage();
    }//GEN-LAST:event_jspinXStateChanged

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
    private javax.swing.JPanel jpnlPixelHolder;
    private javax.swing.JSpinner jspinX;
    private javax.swing.JSpinner jspinY;
    // End of variables declaration//GEN-END:variables

}
