package com.github.stefaniejaeger.neuralnet;

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
