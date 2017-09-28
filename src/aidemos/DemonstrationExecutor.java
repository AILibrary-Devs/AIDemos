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
public class DemonstrationExecutor implements ExecutorIntf {
    
    public DemonstrationExecutor(String name, ExecutorIntf executor){
        this.name = name;
        this.executor = executor;
    }
    
    private final ExecutorIntf executor;
    private final String name;

    @Override
    public void run() {
        if (executor != null) {
            executor.run();
        }
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

}
