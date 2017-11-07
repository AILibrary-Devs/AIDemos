/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package images;

import common.ImageTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

/**
 *
 * @author kwl
 */
public class PixelViewerPanel extends javax.swing.JPanel {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
    left = DEFAULT_OFFSET;
    right = DEFAULT_OFFSET;
    top = DEFAULT_OFFSET;
    bottom = DEFAULT_OFFSET;
    
    setFlattened(false);
}
    /**
     * Creates new form PixelViewerPanel
     */
    public PixelViewerPanel() {
        initComponents();
    }
    /**
     * Creates new form PixelViewerPanel
     * @param image
     */
    public PixelViewerPanel(BufferedImage image) {
        this.image = image;
        initComponents();
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private static final int DEFAULT_OFFSET = 3;
    private int left, right, top, bottom;
    private boolean flattened;
    private BufferedImage image;
    private int[][] imageInt;
    
    private int width(){
        return Math.max(0, this.getWidth() - (right + left));
    }
    
    private int height(){
        return Math.max(0, this.getHeight() - (top + bottom));
    }
    
    /**
     * @return the flattened
     */
    public boolean isFlattened() {
        return flattened;
    }
    
    /**
     * @param flattened the flattened to set
     */
    public void setFlattened(boolean flattened) {
        this.flattened = flattened;
    }
    
    /**
     * @return the image
     */
    public BufferedImage getImage() {
        return image;
    }
    
    private void updateImageIntArrays(){
        if (getImage() != null) {
            imageInt = ImageTools.imageToIntRowColArrays(image);
        }
    }
    
    /**
     * @param image the image to set
     */
    public void setImage(BufferedImage image) {
        this.image = image;
        updateImageIntArrays();
        repaint();
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Methods">
    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        
        if (imageInt != null) {
            int h = height() / imageInt.length;
            int w = width() / imageInt[0].length;

            for (int row = 0; row < imageInt.length; row++) {
                for (int col = 0; col < imageInt[row].length; col++) {
                    
                    if (flattened) {
                        graphics.setColor(ImageTools.getARGBSum(imageInt[row][col]) > 3 * 128 ? Color.WHITE : Color.BLACK );
                    } else {
                        graphics.setColor(new Color(imageInt[row][col], true));
                    }
                    
                    graphics.fillRect(left + (col * w), top + (row * h), w, h);
                }
            }
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
