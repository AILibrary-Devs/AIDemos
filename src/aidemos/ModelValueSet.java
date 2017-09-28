package aidemos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kwl
 * @param <T> the type of the values to be stored in the set
 */
public class ModelValueSet<T> {
    
//<editor-fold defaultstate="collapsed" desc="Constructor">
    public ModelValueSet(String title, T value1, T value2, T value3, T value4){
        this.title = title;
        
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private String title;
    private T value1, value2, value3, value4;
    
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * @return the value1
     */
    public T getValue1() {
        return value1;
    }
    
    /**
     * @return the value1 as a String object
     */
    public String getValue1AsString() {
        return String.valueOf(value1);
    }
    
    /**
     * @param value1 the value1 to set
     */
    public void setValue1(T value1) {
        this.value1 = value1;
    }
    
    /**
     * @return the value2
     */
    public T getValue2() {
        return value2;
    }
    
    /**
     * @return the value2 as a String object
     */
    public String getValue2AsString() {
        return String.valueOf(value2);
    }
    
    /**
     * @param value2 the value2 to set
     */
    public void setValue2(T value2) {
        this.value2 = value2;
    }
    
    /**
     * @return the value3
     */
    public T getValue3() {
        return value3;
    }
    
    /**
     * @return the value3 as a String object
     */
    public String getValue3AsString() {
        return String.valueOf(value3);
    }
    
    /**
     * @param value3 the value3 to set
     */
    public void setValue3(T value3) {
        this.value3 = value3;
    }
    
    /**
     * @return the value4
     */
    public T getValue4() {
        return value4;
    }
    
    /**
     * @return the value4 as a String object
     */
    public String getValue4AsString() {
        return String.valueOf(value4);
    }
    
    /**
     * @param value4 the value4 to set
     */
    public void setValue4(T value4) {
        this.value4 = value4;
    }
//</editor-fold>
}
