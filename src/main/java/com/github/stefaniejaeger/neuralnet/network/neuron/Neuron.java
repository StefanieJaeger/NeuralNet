package com.github.stefaniejaeger.neuralnet.network.neuron;

import com.github.stefaniejaeger.neuralnet.network.Connection;

import java.util.List;

/**
 *
 * @author Stefanie
 */
public abstract class Neuron {
    public Double value = null;
    
    public double getValue(){
        if (value == null) {
            calculateValue();
        }

        return value;
    }
    
    public abstract void calculateValue();
    
    public abstract String toString();

    public abstract void addConnection(Connection connection);

    public abstract void reset();

}
