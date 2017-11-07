/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aidemos;

import common.ImageTools;
import images.ImageViewerFrame;
import java.awt.Image;

/**
 *
 * @author kwl
 */
public class AIDemos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        showNetVisualizer();
//        showDemonsrationController();
        showImageViewer();
    }

    private static void showNetVisualizer() {
        NetVisualizerFrame nv = new NetVisualizerFrame();
        nv.setVisible(true);
    }

    private static void showImageViewer() {
        Image image = ImageTools.loadImageFromResource("images/mnist_test0.jpg");
        Image[][] images = ImageTools.getSubimageArrays(image, 31, 32);
        
        ImageViewerFrame iv = new ImageViewerFrame(images);
        iv.setVisible(true);
    }
    
    private static void showDemonsrationController() {
        DemonstrationControllerFrame dc = new DemonstrationControllerFrame();
        dc.setVisible(true);

//        DemonstrationControllerFrame.main(new String[0]);
    }

}
