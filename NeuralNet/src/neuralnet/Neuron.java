/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnet;

/**
 *
 * @author Stefanie
 */
public abstract class Neuron {
    public double value = -1;    
    
    public double getValue(){
        if(value == -1)
            calculateValue();
        return this.value;            
    }
    
    public void calculateValue(){
        
    }
}
