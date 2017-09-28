/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aidemos;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author kwl
 */
public final class DemonstrationControllerFrame extends javax.swing.JFrame {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        executors = new ArrayList<>();
    }

    /**
     * Creates new form DemoControllerFrame; will be initialized with the list
     * of provided demonstration executors
     *
     * @param executors the demonstrations to be executed by the controller
     */
    public DemonstrationControllerFrame(ArrayList<DemonstrationExecutor> executors) {
        initComponents();
        setDemonstationExecutors(executors);
    }

    /**
     * Creates new form DemoControllerFrame; will be initialized with default
     * list of demonstration executors
     */
    public DemonstrationControllerFrame() {
        initComponents();
        initDefaultExecutors();
    }
//</editor-fold>

    private void initDefaultExecutors() {
        ArrayList<DemonstrationExecutor> defaults = new ArrayList<>();
        
        //lambda duck-typing... <sigh>
        defaults.add(new DemonstrationExecutor("AND Truth Table", () -> {
            System.out.println("Running AND!!!");
        }));

        //olde school :-)
        defaults.add(new DemonstrationExecutor("OR Truth Table", new ExecutorIntf() {
            @Override
            public void run() {
                System.out.println("Running OR!!!");
            }
        }));

        defaults.add(new DemonstrationExecutor("XOR Truth Table", new ExecutorIntf() {
            @Override
            public void run() {
                System.out.println("Running XOR!!!");
            }
        }));

        defaults.add(new DemonstrationExecutor("Number Sort", new ExecutorIntf() {
            @Override
            public void run() {
                System.out.println("Number Sort...");
            }
        }));

        setDemonstationExecutors(defaults);
    }

    private ArrayList<DemonstrationExecutor> executors;

    public void setDemonstationExecutors(ArrayList<DemonstrationExecutor> executors) {
        this.executors = executors;
        updateGUI();
    }

    private void updateGUI() {
        cbxAIDemonstration.removeAll();
        executors.forEach((executor) -> {cbxAIDemonstration.addItem(executor.getName());});
    }
    
    private void runDemonstration(){
        if (cbxAIDemonstration.getSelectedIndex() != -1){
            executors.get(cbxAIDemonstration.getSelectedIndex()).run();
        } else {
            JOptionPane.showMessageDialog(null, "Please select an item in the Demonstration list, and try again.");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSelectDemonstration = new javax.swing.JLabel();
        cbxAIDemonstration = new javax.swing.JComboBox<>();
        btnExit = new javax.swing.JButton();
        btnRunDemonstation = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AI Demo Controller");

        lblSelectDemonstration.setText("Select Demonstration");

        cbxAIDemonstration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnExit.setText("Exit");

        btnRunDemonstation.setText("Run!");
        btnRunDemonstation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunDemonstationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSelectDemonstration)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxAIDemonstration, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnRunDemonstation, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelectDemonstration)
                    .addComponent(cbxAIDemonstration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRunDemonstation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(btnExit)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRunDemonstationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunDemonstationActionPerformed
        runDemonstration();
    }//GEN-LAST:event_btnRunDemonstationActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DemonstrationControllerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DemonstrationControllerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DemonstrationControllerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DemonstrationControllerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DemonstrationControllerFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnRunDemonstation;
    private javax.swing.JComboBox<String> cbxAIDemonstration;
    private javax.swing.JLabel lblSelectDemonstration;
    // End of variables declaration//GEN-END:variables
}