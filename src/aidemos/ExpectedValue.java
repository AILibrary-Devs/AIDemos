/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aidemos;

/**
 *
 * @author kwl
 */
public class ExpectedValue {
    
//<editor-fold defaultstate="collapsed" desc="Constructors">
    public ExpectedValue(double value, String label, AccuracyAssessorIntf assessor){
        this.value = value;
        this.label = label;
        this.assessor = assessor;
    }
    
    public static ExpectedValue expectedValueFactory(double value, String label, double accuracy, AccuracyTest testType){
        AccuracyAssessorIntf assessor;
        
        switch (testType) {
            case GREATER_THAN :
                assessor = (double valueToAssess) -> valueToAssess >= value - accuracy;
                break;

            default:
            case LESS_THAN : 
                assessor = (double valueToAssess) -> valueToAssess <= value + accuracy;
        }
        
        return new ExpectedValue(value, label, assessor);
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Methods">
    public boolean hasMetAccuracyTest(){
        return assessor.assessValue(value);
    }
//</editor-fold>
            
//<editor-fold defaultstate="collapsed" desc="Properties">
    public enum AccuracyTest { GREATER_THAN, LESS_THAN}; //, INTERNAL_RANGE } 
    
    private double value;
    private String label;
    private final AccuracyAssessorIntf assessor;
    
    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }
    
    /**
     * @param value the value to set
     */
    public void setValue(double value) {
        this.value = value;
    }
    
    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }
    
    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }
//</editor-fold>
    
}
