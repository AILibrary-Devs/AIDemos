/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aidemos;

import javax.swing.JFrame;

/**
 *
 * @author kwl
 */
public class AIDemos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NetVisualizerFrame nv = new NetVisualizerFrame();
        nv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nv.setVisible(true);
    }
    
}
