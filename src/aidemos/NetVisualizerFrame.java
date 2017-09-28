/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aidemos;

import java.util.ArrayList;
import javax.swing.JPanel;
import neural_net.NeuralNet;
import neural_net.NeuralNetIntf;

/**
 *
 * @author kwl
 */
public class NetVisualizerFrame extends javax.swing.JFrame {

    /**
     * Creates new form NetVisualizer
     */
    public NetVisualizerFrame() {
        initComponents();
        initData();
    }
    
    private void initData(){
        // initialize net
        net = NeuralNet.neuralNetFactory(getPerceptionLayerCount(), getHiddenLayerCount(), getOutputLayerCount());
        updateExpectedDataIndex();
        updateGUIDataElements();
        
        //new test stuff
        ArrayList<JPanel> dataPanels = new ArrayList<>();
        dataPanels.add(new ModelValueSetPanel(new ModelValueSet<>("Input 1", "False", "True", "False", "True")));
        dataPanels.add(new ModelValueSetPanel(new ModelValueSet<>("Input 2", "False", "False", "True", "True")));
        dataPanels.add(new ModelValueSetPanel(new ModelValueSet<>("- AND -", "False", "False", "False", "True")));
        dataPanels.add(new ModelValueSetPanel(new ModelValueSet<>("Trained", 0.10021, 0.14932, 0.10125, 0.89533)));
        
        JPanel modelDataPanel = new ModelDataPanel("3) Model Data - AND", dataPanels);
        pnlModel.add(modelDataPanel);
        pnlModel.setVisible(true);
        
    }
    
//<editor-fold defaultstate="collapsed" desc="Data Properties">
    NeuralNetIntf net;
    
    private static final double LOW = 0.1;
    private static final double MID = 0.5;
    private static final double HIGH = 0.9;
    
    private static final String FALSE = "F";
    private static final String TRUE = "T";

    private static final double[][] TRUTH_TABLE_INPUT = 
    {
        { LOW, LOW },
        { LOW, HIGH },
        { HIGH, LOW },
        { HIGH, HIGH }
    };
    
    public double[][] getInputs(){
        return TRUTH_TABLE_INPUT;
    }

    public double[][] getExpectedOutputs(){
        switch (getExpectedDataIndex()) {
            case AND_IDX :
                return AND_EXPECTED_OUTPUT;
            case OR_IDX :
                return OR_EXPECTED_OUTPUT;
            case XOR_IDX :
            default:
                return XOR_EXPECTED_OUTPUT;
        }
    }

    private static final double[][] XOR_EXPECTED_OUTPUT = 
    {
        { LOW },
        { HIGH },
        { HIGH },
        { LOW }
    };

    private static final double[][] AND_EXPECTED_OUTPUT = 
    {
        { LOW },
        { LOW },
        { LOW },
        { HIGH }
    };

    private final double[][] OR_EXPECTED_OUTPUT = 
    {
        { LOW },
        { HIGH },
        { HIGH },
        { HIGH }
    };

    private int expectedDataIndex;
    
    public int getExpectedDataIndex(){
        return expectedDataIndex;
    }
    
    public void setExpectedDataIndex(int expectedDataIndex){
        if (this.expectedDataIndex != expectedDataIndex){
            this.expectedDataIndex = expectedDataIndex;
            updateGUIDataElements();
        }
    }
        
    private void updateExpectedDataIndex(){
        setExpectedDataIndex(jcbxInputDataSet.getSelectedIndex());
    }
    
    private static final int AND_IDX = 0;
    private static final int OR_IDX  = 1;
    private static final int XOR_IDX = 2;

    private static final int OUTPUT_IDX_FF = 0;
    private static final int OUTPUT_IDX_TF = 1;
    private static final int OUTPUT_IDX_FT = 2;
    private static final int OUTPUT_IDX_TT = 3;
    
//    private static final double[][] EXPECTED_OUTPUT = 
//    {   // FF,  TF,   FT,   TT
//        { LOW,  LOW,  LOW,  HIGH }, // AND
//        { LOW,  HIGH, HIGH, HIGH }, // OR
//        { LOW,  HIGH, HIGH, LOW }   // XOR
//    };
//    
//    public double[] getExpectedOutput(){
//        return EXPECTED_OUTPUT[getExpectedDataIndex()];
//    }
    
    private void updateGUIDataElements(){
        jlblInput00.setText(translateValueToBooleanLabel(TRUTH_TABLE_INPUT[0][0]));
        jlblInput01.setText(translateValueToBooleanLabel(TRUTH_TABLE_INPUT[0][1]));
        jlblExpectedFF.setText(translateValueToBooleanLabel(getExpectedOutputs()[OUTPUT_IDX_FF][0]));

        jlblInput10.setText(translateValueToBooleanLabel(TRUTH_TABLE_INPUT[1][0]));
        jlblInput11.setText(translateValueToBooleanLabel(TRUTH_TABLE_INPUT[1][1]));
        jlblExpectedTF.setText(translateValueToBooleanLabel(getExpectedOutputs()[OUTPUT_IDX_TF][0]));

        jlblInput20.setText(translateValueToBooleanLabel(TRUTH_TABLE_INPUT[2][0]));
        jlblInput21.setText(translateValueToBooleanLabel(TRUTH_TABLE_INPUT[2][1]));
        jlblExpectedFT.setText(translateValueToBooleanLabel(getExpectedOutputs()[OUTPUT_IDX_FT][0]));

        jlblInput30.setText(translateValueToBooleanLabel(TRUTH_TABLE_INPUT[3][0]));
        jlblInput31.setText(translateValueToBooleanLabel(TRUTH_TABLE_INPUT[3][1]));
        jlblExpectedTT.setText(translateValueToBooleanLabel(getExpectedOutputs()[OUTPUT_IDX_TT][0]));  
    }
    
    private String translateValueToBooleanLabel(double value){
        return value >= MID ? TRUE : FALSE;
    }
    
    private String translateValueToLabel(double value, double accuracyLimit){
        if (value >= HIGH - Math.abs(accuracyLimit)) {
            return TRUE;
        } else if (value <= LOW + Math.abs(accuracyLimit)){
            return FALSE;
        } else {
            return "Indeterminate";
        }
    }


    private boolean trained;
    
    public int getPerceptionLayerCount(){
        return (Integer) jspinPerceptionLayerCount.getValue();
    }
    
    public int getHiddenLayerCount(){
        return (Integer) jspinHiddenLayerCount.getValue();
    }
    
    public int getOutputLayerCount(){
        return (Integer) jspinOutputLayerCount.getValue();
    }
    
    public int getIterationLimit(){
        return (Integer) jspinIterationLimit.getValue();
    }
    
        public double getLearningRate(){
            return 3.0;
        }    
//</editor-fold>
    
    private long trainingCount;
    
    public void updateGUI(){
        jlblTrainingCount.setText(String.valueOf(trainingCount));

        
//        jcbxInput1.setEnabled(trained);
//        jcbxInput2.setEnabled(trained);
        btnTestModel.setEnabled(trained);
    }
    
    private double FF_TrainedValue, TF_TrainedValue, FT_TrainedValue, TT_TrainedValue;
    
    private void setFF_TrainedValue(double value){
        FF_TrainedValue = value;
        jlblTrainedValueFF.setText(String.format("%.5f", value));
    }
    
    private void setTF_TrainedValue(double value){
        TF_TrainedValue = value;
        jlblTrainedValueTF.setText(String.format("%.5f", value));
    }
    
    private void setFT_TrainedValue(double value){
        FT_TrainedValue = value;
        jlblTrainedValueFT.setText(String.format("%.5f", value));
    }
    
    private void setTT_TrainedValue(double value){
        TT_TrainedValue = value;
        jlblTrainedValueTT.setText(String.format("%.5f", value));
    }
    
    private void testModel() {
        final int TRUE_ITEM = 0;
        
//        double input1 = jcbxInput1.getSelectedIndex() == TRUE_ITEM ? high : low;
//        double input2 = jcbxInput2.getSelectedIndex() == TRUE_ITEM ? high : low;
        
//        net.getPerceptionLayer().getNeurons().get(0).setOutput(input1);
//        net.getPerceptionLayer().getNeurons().get(1).setOutput(input2);
        net.pulse();
        
        double result = net.getOutputLayer().getNeurons().get(0).getOutput();
        
        jlblModelResult.setText((result >= (HIGH - 0.1)) ? "True" : "False");
    }
    
    private void trainModel(){
        System.out.println("Training...");
        trained = false;
        double accuracy = 0.05;
        
        ExpectedValue ff_ev, tf_ev, ft_ev, tt_ev;
        
        switch (getExpectedDataIndex()) {
            case AND_IDX:
                ff_ev = ExpectedValue.expectedValueFactory(LOW, "FF", accuracy, ExpectedValue.AccuracyTest.LESS_THAN);
                tf_ev = ExpectedValue.expectedValueFactory(LOW, "TF", accuracy, ExpectedValue.AccuracyTest.LESS_THAN);
                ft_ev = ExpectedValue.expectedValueFactory(LOW, "FT", accuracy, ExpectedValue.AccuracyTest.LESS_THAN);
                tt_ev = ExpectedValue.expectedValueFactory(HIGH, "TT", accuracy, ExpectedValue.AccuracyTest.GREATER_THAN);
                break;
            case OR_IDX:
                ff_ev = ExpectedValue.expectedValueFactory(LOW, "FF", accuracy, ExpectedValue.AccuracyTest.LESS_THAN);
                tf_ev = ExpectedValue.expectedValueFactory(HIGH, "TF", accuracy, ExpectedValue.AccuracyTest.GREATER_THAN);
                ft_ev = ExpectedValue.expectedValueFactory(HIGH, "FT", accuracy, ExpectedValue.AccuracyTest.GREATER_THAN);
                tt_ev = ExpectedValue.expectedValueFactory(HIGH, "TT", accuracy, ExpectedValue.AccuracyTest.GREATER_THAN);
                break;
            default:
                //if (getExpectedDataIndex() == XOR_IDX){
                ff_ev = ExpectedValue.expectedValueFactory(LOW, "FF", accuracy, ExpectedValue.AccuracyTest.LESS_THAN);
                tf_ev = ExpectedValue.expectedValueFactory(HIGH, "TF", accuracy, ExpectedValue.AccuracyTest.GREATER_THAN);
                ft_ev = ExpectedValue.expectedValueFactory(HIGH, "FT", accuracy, ExpectedValue.AccuracyTest.GREATER_THAN);
                tt_ev = ExpectedValue.expectedValueFactory(LOW, "TT", accuracy, ExpectedValue.AccuracyTest.LESS_THAN);
                break;
        }
        
        int iteration = 0;
        int trainCount = 5;
        
        do {
            iteration++;
            
            net.setLearningRate(getLearningRate());
            net.train(TRUTH_TABLE_INPUT, getExpectedOutputs(), trainCount);
            
            // false, false
            net.getPerceptionLayer().getNeurons().get(0).setOutput(LOW);
            net.getPerceptionLayer().getNeurons().get(1).setOutput(LOW);
            net.pulse();

            ff_ev.setValue(net.getOutputLayer().getNeurons().get(0).getOutput());
            
            // false, true
            net.getPerceptionLayer().getNeurons().get(0).setOutput(LOW);
            net.getPerceptionLayer().getNeurons().get(1).setOutput(HIGH);
            net.pulse();

            ft_ev.setValue(net.getOutputLayer().getNeurons().get(0).getOutput());
            
            // true, false
            net.getPerceptionLayer().getNeurons().get(0).setOutput(HIGH);
            net.getPerceptionLayer().getNeurons().get(1).setOutput(LOW);
            net.pulse();

            tf_ev.setValue(net.getOutputLayer().getNeurons().get(0).getOutput());

            // true, true
            net.getPerceptionLayer().getNeurons().get(0).setOutput(HIGH);
            net.getPerceptionLayer().getNeurons().get(1).setOutput(HIGH);
            net.pulse();

            tt_ev.setValue(net.getOutputLayer().getNeurons().get(0).getOutput());
            
            // todo - implement tracking output
            System.out.printf("%d) %s [%f] TF [%f] FT [%f] TT [%f] \n", iteration, ff_ev.getLabel(), ff_ev.getValue(), tf_ev.getValue(), ft_ev.getValue(), tt_ev.getValue());

            if (iteration > getIterationLimit()){
                break;
            }           
        } while (
                 (! ff_ev.hasMetAccuracyTest() ) || 
                 (! tf_ev.hasMetAccuracyTest() ) || 
                 (! ft_ev.hasMetAccuracyTest() ) || 
                 (! tt_ev.hasMetAccuracyTest() )
                );
        
        trainingCount = iteration;
        trained = true;
        
        setFF_TrainedValue(ff_ev.getValue());
        setTF_TrainedValue(tf_ev.getValue());
        setFT_TrainedValue(ft_ev.getValue());
        setTT_TrainedValue(tt_ev.getValue());
        
        updateGUI();        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnlControl = new javax.swing.JPanel();
        jbtnTrain = new javax.swing.JButton();
        jlblLayers = new javax.swing.JLabel();
        jlblActualIterations = new javax.swing.JLabel();
        jlblTrainingCount = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jspinIterationLimit = new javax.swing.JSpinner();
        jlblDefineSection = new javax.swing.JLabel();
        jlblTrainModelSection = new javax.swing.JLabel();
        jpnlDivider = new javax.swing.JPanel();
        jlblIterationsGroup = new javax.swing.JLabel();
        jlblPerceptionNodeCount = new javax.swing.JLabel();
        jlblHiddenNodeCount = new javax.swing.JLabel();
        jspinPerceptionLayerCount = new javax.swing.JSpinner();
        jlblOutputNodeCount = new javax.swing.JLabel();
        jspinHiddenLayerCount = new javax.swing.JSpinner();
        jspinOutputLayerCount = new javax.swing.JSpinner();
        jlblInputDataSet = new javax.swing.JLabel();
        jcbxInputDataSet = new javax.swing.JComboBox<>();
        jpnlOutput = new javax.swing.JPanel();
        jlblModelTestSection = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnTestModel = new javax.swing.JButton();
        jlblModelResult = new javax.swing.JLabel();
        jlblExpectedResult = new javax.swing.JLabel();
        jlblInput00 = new javax.swing.JLabel();
        jlblInput10 = new javax.swing.JLabel();
        jlblInput20 = new javax.swing.JLabel();
        jlblInput30 = new javax.swing.JLabel();
        jlblInput01 = new javax.swing.JLabel();
        jlblInput11 = new javax.swing.JLabel();
        jlblInput21 = new javax.swing.JLabel();
        jlblInput31 = new javax.swing.JLabel();
        jlblExpectedFF = new javax.swing.JLabel();
        jlblExpectedTF = new javax.swing.JLabel();
        jlblExpectedFT = new javax.swing.JLabel();
        jlblExpectedTT = new javax.swing.JLabel();
        jlblCalculatedFF = new javax.swing.JLabel();
        jlblCalculatedTF = new javax.swing.JLabel();
        jlblCalculatedFT = new javax.swing.JLabel();
        jlblCalculatedTT = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jlblCalculatedValueFF = new javax.swing.JLabel();
        jlblCalculatedValueTF = new javax.swing.JLabel();
        jlblCalculatedValueFT = new javax.swing.JLabel();
        jlblCalculatedValueTT = new javax.swing.JLabel();
        jlblTrainedValueFF = new javax.swing.JLabel();
        jlblTrainedValueTF = new javax.swing.JLabel();
        jlblTrainedValueFT = new javax.swing.JLabel();
        jlblTrainedValueTT = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        pnlModel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jbtnTrain.setText("Train!");
        jbtnTrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTrainActionPerformed(evt);
            }
        });

        jlblLayers.setText("Layers");

        jlblActualIterations.setText("Actual");

        jlblTrainingCount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlblTrainingCount.setText(" ");

        jLabel6.setText("Limit");

        jspinIterationLimit.setModel(new javax.swing.SpinnerNumberModel(1000, 10, 10000, 100));

        jlblDefineSection.setText("1) Define");

        jlblTrainModelSection.setText("2) Train Model");

        jpnlDivider.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jpnlDividerLayout = new javax.swing.GroupLayout(jpnlDivider);
        jpnlDivider.setLayout(jpnlDividerLayout);
        jpnlDividerLayout.setHorizontalGroup(
            jpnlDividerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpnlDividerLayout.setVerticalGroup(
            jpnlDividerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jlblIterationsGroup.setText("Iterations");

        jlblPerceptionNodeCount.setText("Perception");

        jlblHiddenNodeCount.setText("Hidden");

        jspinPerceptionLayerCount.setModel(new javax.swing.SpinnerNumberModel(2, null, null, 1));
        jspinPerceptionLayerCount.setEnabled(false);

        jlblOutputNodeCount.setText("Output");

        jspinHiddenLayerCount.setModel(new javax.swing.SpinnerNumberModel(2, null, null, 1));

        jspinOutputLayerCount.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1, 1));
        jspinOutputLayerCount.setEnabled(false);

        jlblInputDataSet.setText("Input Data");

        jcbxInputDataSet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AND Truth Table", "OR Truth Table", "XOR Truth Table" }));
        jcbxInputDataSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbxInputDataSetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnlControlLayout = new javax.swing.GroupLayout(jpnlControl);
        jpnlControl.setLayout(jpnlControlLayout);
        jpnlControlLayout.setHorizontalGroup(
            jpnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlControlLayout.createSequentialGroup()
                .addGroup(jpnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnlControlLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jpnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblLayers)
                            .addGroup(jpnlControlLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jpnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblPerceptionNodeCount)
                                    .addComponent(jlblHiddenNodeCount)
                                    .addComponent(jlblOutputNodeCount))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jspinPerceptionLayerCount, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jspinHiddenLayerCount, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jspinOutputLayerCount, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jpnlControlLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlblDefineSection))
                    .addGroup(jpnlControlLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlblInputDataSet)
                        .addGap(12, 12, 12)
                        .addComponent(jcbxInputDataSet, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49)
                .addComponent(jpnlDivider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblTrainModelSection, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnlControlLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jpnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblIterationsGroup)
                            .addGroup(jpnlControlLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jpnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jlblActualIterations))
                                .addGap(38, 38, 38)
                                .addGroup(jpnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblTrainingCount, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jspinIterationLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jbtnTrain, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnlControlLayout.setVerticalGroup(
            jpnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnlControlLayout.createSequentialGroup()
                        .addGroup(jpnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnlControlLayout.createSequentialGroup()
                                .addComponent(jlblDefineSection)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlblLayers)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlblPerceptionNodeCount)
                                    .addComponent(jspinPerceptionLayerCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlblHiddenNodeCount)
                                    .addComponent(jspinHiddenLayerCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlblOutputNodeCount)
                                    .addComponent(jspinOutputLayerCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpnlControlLayout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(jlblActualIterations)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblInputDataSet)
                            .addComponent(jcbxInputDataSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addGroup(jpnlControlLayout.createSequentialGroup()
                        .addGroup(jpnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpnlDivider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jpnlControlLayout.createSequentialGroup()
                                .addComponent(jlblTrainModelSection, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlblIterationsGroup)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jspinIterationLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlblTrainingCount)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnTrain)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );

        jlblModelTestSection.setText("3) Test Model");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Inputs");

        jLabel14.setText("Expected");

        btnTestModel.setText("Test!");
        btnTestModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestModelActionPerformed(evt);
            }
        });

        jlblModelResult.setText(" ");

        jlblExpectedResult.setText(" ");

        jlblInput00.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblInput00.setText("False");
        jlblInput00.setFocusTraversalKeysEnabled(false);
        jlblInput00.setFocusable(false);
        jlblInput00.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jlblInput00.setInheritsPopupMenu(false);
        jlblInput00.setName(""); // NOI18N
        jlblInput00.setRequestFocusEnabled(false);

        jlblInput10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblInput10.setText("True");
        jlblInput10.setFocusTraversalKeysEnabled(false);
        jlblInput10.setFocusable(false);
        jlblInput10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jlblInput10.setInheritsPopupMenu(false);
        jlblInput10.setMaximumSize(new java.awt.Dimension(32, 16));
        jlblInput10.setMinimumSize(new java.awt.Dimension(32, 16));
        jlblInput10.setName(""); // NOI18N
        jlblInput10.setPreferredSize(new java.awt.Dimension(32, 16));
        jlblInput10.setRequestFocusEnabled(false);

        jlblInput20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblInput20.setText("False");
        jlblInput20.setFocusTraversalKeysEnabled(false);
        jlblInput20.setFocusable(false);
        jlblInput20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jlblInput20.setInheritsPopupMenu(false);
        jlblInput20.setName(""); // NOI18N
        jlblInput20.setRequestFocusEnabled(false);

        jlblInput30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblInput30.setText("True");
        jlblInput30.setFocusTraversalKeysEnabled(false);
        jlblInput30.setFocusable(false);
        jlblInput30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jlblInput30.setInheritsPopupMenu(false);
        jlblInput30.setMaximumSize(new java.awt.Dimension(32, 16));
        jlblInput30.setMinimumSize(new java.awt.Dimension(32, 16));
        jlblInput30.setName(""); // NOI18N
        jlblInput30.setPreferredSize(new java.awt.Dimension(32, 16));
        jlblInput30.setRequestFocusEnabled(false);

        jlblInput01.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblInput01.setText("False");
        jlblInput01.setFocusTraversalKeysEnabled(false);
        jlblInput01.setFocusable(false);
        jlblInput01.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jlblInput01.setInheritsPopupMenu(false);
        jlblInput01.setName(""); // NOI18N
        jlblInput01.setRequestFocusEnabled(false);

        jlblInput11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblInput11.setText("False");
        jlblInput11.setFocusTraversalKeysEnabled(false);
        jlblInput11.setFocusable(false);
        jlblInput11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jlblInput11.setInheritsPopupMenu(false);
        jlblInput11.setName(""); // NOI18N
        jlblInput11.setRequestFocusEnabled(false);

        jlblInput21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblInput21.setText("True");
        jlblInput21.setFocusTraversalKeysEnabled(false);
        jlblInput21.setFocusable(false);
        jlblInput21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jlblInput21.setInheritsPopupMenu(false);
        jlblInput21.setName(""); // NOI18N
        jlblInput21.setRequestFocusEnabled(false);

        jlblInput31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblInput31.setText("True");
        jlblInput31.setFocusTraversalKeysEnabled(false);
        jlblInput31.setFocusable(false);
        jlblInput31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jlblInput31.setInheritsPopupMenu(false);
        jlblInput31.setName(""); // NOI18N
        jlblInput31.setRequestFocusEnabled(false);

        jlblExpectedFF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblExpectedFF.setText("False");
        jlblExpectedFF.setFocusTraversalKeysEnabled(false);
        jlblExpectedFF.setFocusable(false);
        jlblExpectedFF.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jlblExpectedFF.setInheritsPopupMenu(false);
        jlblExpectedFF.setName(""); // NOI18N
        jlblExpectedFF.setRequestFocusEnabled(false);

        jlblExpectedTF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblExpectedTF.setText("False");
        jlblExpectedTF.setFocusTraversalKeysEnabled(false);
        jlblExpectedTF.setFocusable(false);
        jlblExpectedTF.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jlblExpectedTF.setInheritsPopupMenu(false);
        jlblExpectedTF.setName(""); // NOI18N
        jlblExpectedTF.setRequestFocusEnabled(false);

        jlblExpectedFT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblExpectedFT.setText("True");
        jlblExpectedFT.setFocusTraversalKeysEnabled(false);
        jlblExpectedFT.setFocusable(false);
        jlblExpectedFT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jlblExpectedFT.setInheritsPopupMenu(false);
        jlblExpectedFT.setMaximumSize(new java.awt.Dimension(32, 16));
        jlblExpectedFT.setMinimumSize(new java.awt.Dimension(32, 16));
        jlblExpectedFT.setName(""); // NOI18N
        jlblExpectedFT.setPreferredSize(new java.awt.Dimension(32, 16));
        jlblExpectedFT.setRequestFocusEnabled(false);

        jlblExpectedTT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblExpectedTT.setText("True");
        jlblExpectedTT.setFocusTraversalKeysEnabled(false);
        jlblExpectedTT.setFocusable(false);
        jlblExpectedTT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jlblExpectedTT.setInheritsPopupMenu(false);
        jlblExpectedTT.setMaximumSize(new java.awt.Dimension(32, 16));
        jlblExpectedTT.setMinimumSize(new java.awt.Dimension(32, 16));
        jlblExpectedTT.setName(""); // NOI18N
        jlblExpectedTT.setPreferredSize(new java.awt.Dimension(32, 16));
        jlblExpectedTT.setRequestFocusEnabled(false);

        jlblCalculatedFF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblCalculatedFF.setText("False");
        jlblCalculatedFF.setFocusTraversalKeysEnabled(false);
        jlblCalculatedFF.setFocusable(false);
        jlblCalculatedFF.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jlblCalculatedFF.setInheritsPopupMenu(false);
        jlblCalculatedFF.setName(""); // NOI18N
        jlblCalculatedFF.setRequestFocusEnabled(false);

        jlblCalculatedTF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblCalculatedTF.setText("False");
        jlblCalculatedTF.setFocusTraversalKeysEnabled(false);
        jlblCalculatedTF.setFocusable(false);
        jlblCalculatedTF.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jlblCalculatedTF.setInheritsPopupMenu(false);
        jlblCalculatedTF.setName(""); // NOI18N
        jlblCalculatedTF.setRequestFocusEnabled(false);

        jlblCalculatedFT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblCalculatedFT.setText("True");
        jlblCalculatedFT.setFocusTraversalKeysEnabled(false);
        jlblCalculatedFT.setFocusable(false);
        jlblCalculatedFT.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jlblCalculatedFT.setInheritsPopupMenu(false);
        jlblCalculatedFT.setMaximumSize(new java.awt.Dimension(32, 16));
        jlblCalculatedFT.setMinimumSize(new java.awt.Dimension(32, 16));
        jlblCalculatedFT.setName(""); // NOI18N
        jlblCalculatedFT.setPreferredSize(new java.awt.Dimension(32, 16));
        jlblCalculatedFT.setRequestFocusEnabled(false);

        jlblCalculatedTT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblCalculatedTT.setText("True");
        jlblCalculatedTT.setFocusTraversalKeysEnabled(false);
        jlblCalculatedTT.setFocusable(false);
        jlblCalculatedTT.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jlblCalculatedTT.setInheritsPopupMenu(false);
        jlblCalculatedTT.setMaximumSize(new java.awt.Dimension(32, 16));
        jlblCalculatedTT.setMinimumSize(new java.awt.Dimension(32, 16));
        jlblCalculatedTT.setName(""); // NOI18N
        jlblCalculatedTT.setPreferredSize(new java.awt.Dimension(32, 16));
        jlblCalculatedTT.setRequestFocusEnabled(false);

        jLabel15.setText("Calculated");

        jlblCalculatedValueFF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlblCalculatedValueFF.setText("False");
        jlblCalculatedValueFF.setFocusTraversalKeysEnabled(false);
        jlblCalculatedValueFF.setFocusable(false);
        jlblCalculatedValueFF.setInheritsPopupMenu(false);
        jlblCalculatedValueFF.setMaximumSize(new java.awt.Dimension(80, 16));
        jlblCalculatedValueFF.setMinimumSize(new java.awt.Dimension(80, 16));
        jlblCalculatedValueFF.setName(""); // NOI18N
        jlblCalculatedValueFF.setPreferredSize(new java.awt.Dimension(80, 16));
        jlblCalculatedValueFF.setRequestFocusEnabled(false);

        jlblCalculatedValueTF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlblCalculatedValueTF.setText("False");
        jlblCalculatedValueTF.setFocusTraversalKeysEnabled(false);
        jlblCalculatedValueTF.setFocusable(false);
        jlblCalculatedValueTF.setInheritsPopupMenu(false);
        jlblCalculatedValueTF.setMaximumSize(new java.awt.Dimension(80, 16));
        jlblCalculatedValueTF.setMinimumSize(new java.awt.Dimension(80, 16));
        jlblCalculatedValueTF.setName(""); // NOI18N
        jlblCalculatedValueTF.setPreferredSize(new java.awt.Dimension(80, 16));
        jlblCalculatedValueTF.setRequestFocusEnabled(false);

        jlblCalculatedValueFT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlblCalculatedValueFT.setText("True");
        jlblCalculatedValueFT.setFocusTraversalKeysEnabled(false);
        jlblCalculatedValueFT.setFocusable(false);
        jlblCalculatedValueFT.setInheritsPopupMenu(false);
        jlblCalculatedValueFT.setMaximumSize(new java.awt.Dimension(80, 16));
        jlblCalculatedValueFT.setMinimumSize(new java.awt.Dimension(80, 16));
        jlblCalculatedValueFT.setName(""); // NOI18N
        jlblCalculatedValueFT.setPreferredSize(new java.awt.Dimension(80, 16));
        jlblCalculatedValueFT.setRequestFocusEnabled(false);

        jlblCalculatedValueTT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlblCalculatedValueTT.setText("True");
        jlblCalculatedValueTT.setFocusTraversalKeysEnabled(false);
        jlblCalculatedValueTT.setFocusable(false);
        jlblCalculatedValueTT.setInheritsPopupMenu(false);
        jlblCalculatedValueTT.setMaximumSize(new java.awt.Dimension(80, 16));
        jlblCalculatedValueTT.setMinimumSize(new java.awt.Dimension(80, 16));
        jlblCalculatedValueTT.setName(""); // NOI18N
        jlblCalculatedValueTT.setPreferredSize(new java.awt.Dimension(80, 16));
        jlblCalculatedValueTT.setRequestFocusEnabled(false);

        jlblTrainedValueFF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlblTrainedValueFF.setText("False");
        jlblTrainedValueFF.setFocusTraversalKeysEnabled(false);
        jlblTrainedValueFF.setFocusable(false);
        jlblTrainedValueFF.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jlblTrainedValueFF.setInheritsPopupMenu(false);
        jlblTrainedValueFF.setMaximumSize(new java.awt.Dimension(80, 16));
        jlblTrainedValueFF.setMinimumSize(new java.awt.Dimension(80, 16));
        jlblTrainedValueFF.setName(""); // NOI18N
        jlblTrainedValueFF.setPreferredSize(new java.awt.Dimension(80, 16));
        jlblTrainedValueFF.setRequestFocusEnabled(false);

        jlblTrainedValueTF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlblTrainedValueTF.setText("False");
        jlblTrainedValueTF.setFocusTraversalKeysEnabled(false);
        jlblTrainedValueTF.setFocusable(false);
        jlblTrainedValueTF.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jlblTrainedValueTF.setInheritsPopupMenu(false);
        jlblTrainedValueTF.setMaximumSize(new java.awt.Dimension(80, 16));
        jlblTrainedValueTF.setMinimumSize(new java.awt.Dimension(80, 16));
        jlblTrainedValueTF.setName(""); // NOI18N
        jlblTrainedValueTF.setPreferredSize(new java.awt.Dimension(80, 16));
        jlblTrainedValueTF.setRequestFocusEnabled(false);

        jlblTrainedValueFT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlblTrainedValueFT.setText("True");
        jlblTrainedValueFT.setFocusTraversalKeysEnabled(false);
        jlblTrainedValueFT.setFocusable(false);
        jlblTrainedValueFT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jlblTrainedValueFT.setInheritsPopupMenu(false);
        jlblTrainedValueFT.setMaximumSize(new java.awt.Dimension(80, 16));
        jlblTrainedValueFT.setMinimumSize(new java.awt.Dimension(80, 16));
        jlblTrainedValueFT.setName(""); // NOI18N
        jlblTrainedValueFT.setPreferredSize(new java.awt.Dimension(80, 16));
        jlblTrainedValueFT.setRequestFocusEnabled(false);

        jlblTrainedValueTT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlblTrainedValueTT.setText("True");
        jlblTrainedValueTT.setFocusTraversalKeysEnabled(false);
        jlblTrainedValueTT.setFocusable(false);
        jlblTrainedValueTT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jlblTrainedValueTT.setInheritsPopupMenu(false);
        jlblTrainedValueTT.setMaximumSize(new java.awt.Dimension(80, 16));
        jlblTrainedValueTT.setMinimumSize(new java.awt.Dimension(80, 16));
        jlblTrainedValueTT.setName(""); // NOI18N
        jlblTrainedValueTT.setPreferredSize(new java.awt.Dimension(80, 16));
        jlblTrainedValueTT.setRequestFocusEnabled(false);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Train Result");

        javax.swing.GroupLayout jpnlOutputLayout = new javax.swing.GroupLayout(jpnlOutput);
        jpnlOutput.setLayout(jpnlOutputLayout);
        jpnlOutputLayout.setHorizontalGroup(
            jpnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlOutputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnlOutputLayout.createSequentialGroup()
                        .addGroup(jpnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnlOutputLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnlOutputLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jpnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblInput10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlblInput20, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlblInput00, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlblInput30, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblInput11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlblInput21, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlblInput01, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlblInput31, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jpnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnlOutputLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel14))
                            .addGroup(jpnlOutputLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jpnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblExpectedTF, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlblExpectedFT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlblExpectedFF, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlblExpectedTT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlblTrainedValueTF, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblTrainedValueFT, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblTrainedValueFF, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblTrainedValueTT, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jpnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnTestModel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlOutputLayout.createSequentialGroup()
                                    .addGroup(jpnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jlblCalculatedTF, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlblCalculatedFT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlblCalculatedFF, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlblCalculatedTT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jpnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jlblCalculatedValueTF, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlblCalculatedValueFT, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlblCalculatedValueFF, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlblCalculatedValueTT, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpnlOutputLayout.createSequentialGroup()
                        .addComponent(jlblModelTestSection)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addGroup(jpnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlblExpectedResult, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblModelResult, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(363, 363, 363))))
        );
        jpnlOutputLayout.setVerticalGroup(
            jpnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlOutputLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlblModelResult)
                        .addComponent(jlblModelTestSection, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlblExpectedResult))
                .addGroup(jpnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnlOutputLayout.createSequentialGroup()
                        .addGroup(jpnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpnlOutputLayout.createSequentialGroup()
                                    .addComponent(jlblInput00, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jlblInput10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(jlblInput20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jlblInput30, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpnlOutputLayout.createSequentialGroup()
                                    .addComponent(jlblInput01, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jlblInput11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(jlblInput21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jlblInput31, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpnlOutputLayout.createSequentialGroup()
                                    .addComponent(jlblExpectedFF, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jlblExpectedTF, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(jlblExpectedFT, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jlblExpectedTT, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpnlOutputLayout.createSequentialGroup()
                                    .addComponent(jlblTrainedValueFF, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jlblTrainedValueTF, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(jlblTrainedValueFT, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jlblTrainedValueTT, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpnlOutputLayout.createSequentialGroup()
                                .addComponent(jlblCalculatedFF, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlblCalculatedTF, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jlblCalculatedFT, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlblCalculatedTT, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addComponent(btnTestModel))
                    .addGroup(jpnlOutputLayout.createSequentialGroup()
                        .addComponent(jlblCalculatedValueFF, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlblCalculatedValueTF, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jlblCalculatedValueFT, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlblCalculatedValueTT, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jlblExpectedFF.getAccessibleContext().setAccessibleName("");
        jlblExpectedTF.getAccessibleContext().setAccessibleName("");
        jlblExpectedFT.getAccessibleContext().setAccessibleName("");
        jlblExpectedTT.getAccessibleContext().setAccessibleName("");

        pnlModel.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout pnlModelLayout = new javax.swing.GroupLayout(pnlModel);
        pnlModel.setLayout(pnlModelLayout);
        pnlModelLayout.setHorizontalGroup(
            pnlModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlModelLayout.setVerticalGroup(
            pnlModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 191, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlControl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnlOutput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlModel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnlControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnlOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlModel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnTrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTrainActionPerformed
        trainModel();
    }//GEN-LAST:event_jbtnTrainActionPerformed

    private void btnTestModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestModelActionPerformed
        testModel();
    }//GEN-LAST:event_btnTestModelActionPerformed

    private void jcbxInputDataSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbxInputDataSetActionPerformed
        System.out.println("updating");
        
        updateExpectedDataIndex();
        
        System.out.println("updated");
    }//GEN-LAST:event_jcbxInputDataSetActionPerformed

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
            java.util.logging.Logger.getLogger(NetVisualizerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NetVisualizerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NetVisualizerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NetVisualizerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NetVisualizerFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTestModel;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton jbtnTrain;
    private javax.swing.JComboBox<String> jcbxInputDataSet;
    private javax.swing.JLabel jlblActualIterations;
    private javax.swing.JLabel jlblCalculatedFF;
    private javax.swing.JLabel jlblCalculatedFT;
    private javax.swing.JLabel jlblCalculatedTF;
    private javax.swing.JLabel jlblCalculatedTT;
    private javax.swing.JLabel jlblCalculatedValueFF;
    private javax.swing.JLabel jlblCalculatedValueFT;
    private javax.swing.JLabel jlblCalculatedValueTF;
    private javax.swing.JLabel jlblCalculatedValueTT;
    private javax.swing.JLabel jlblDefineSection;
    private javax.swing.JLabel jlblExpectedFF;
    private javax.swing.JLabel jlblExpectedFT;
    private javax.swing.JLabel jlblExpectedResult;
    private javax.swing.JLabel jlblExpectedTF;
    private javax.swing.JLabel jlblExpectedTT;
    private javax.swing.JLabel jlblHiddenNodeCount;
    private javax.swing.JLabel jlblInput00;
    private javax.swing.JLabel jlblInput01;
    private javax.swing.JLabel jlblInput10;
    private javax.swing.JLabel jlblInput11;
    private javax.swing.JLabel jlblInput20;
    private javax.swing.JLabel jlblInput21;
    private javax.swing.JLabel jlblInput30;
    private javax.swing.JLabel jlblInput31;
    private javax.swing.JLabel jlblInputDataSet;
    private javax.swing.JLabel jlblIterationsGroup;
    private javax.swing.JLabel jlblLayers;
    private javax.swing.JLabel jlblModelResult;
    private javax.swing.JLabel jlblModelTestSection;
    private javax.swing.JLabel jlblOutputNodeCount;
    private javax.swing.JLabel jlblPerceptionNodeCount;
    private javax.swing.JLabel jlblTrainModelSection;
    private javax.swing.JLabel jlblTrainedValueFF;
    private javax.swing.JLabel jlblTrainedValueFT;
    private javax.swing.JLabel jlblTrainedValueTF;
    private javax.swing.JLabel jlblTrainedValueTT;
    private javax.swing.JLabel jlblTrainingCount;
    private javax.swing.JPanel jpnlControl;
    private javax.swing.JPanel jpnlDivider;
    private javax.swing.JPanel jpnlOutput;
    private javax.swing.JSpinner jspinHiddenLayerCount;
    private javax.swing.JSpinner jspinIterationLimit;
    private javax.swing.JSpinner jspinOutputLayerCount;
    private javax.swing.JSpinner jspinPerceptionLayerCount;
    private javax.swing.JPanel pnlModel;
    // End of variables declaration//GEN-END:variables

}
