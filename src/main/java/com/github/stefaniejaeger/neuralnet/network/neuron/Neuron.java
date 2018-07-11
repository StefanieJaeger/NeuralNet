package com.github.stefaniejaeger.neuralnet.network.neuron;

/**
 *
 * @author Stefanie
 */
public abstract class Neuron {
    public Double value = null;
    
    public double getValue(){
        if(value == null)
            calculateValue();
        return this.value;            
    }
    
    public abstract void calculateValue();
}
