package com.github.stefaniejaeger.neuralnet.network.neuron;

import com.github.stefaniejaeger.neuralnet.network.Connection;

import java.util.List;


/**
 *
 * @author Stefanie
 */
public class InputNeuron extends Neuron {
    
    public void setValue(double input){
        value = input;
    }

    @Override
    public void calculateValue() {
        // Input neurons do not need to calculate an output value
    }
    
    @Override
    public String toString() {
        return "input neuron with value " + value;
    }

    @Override
    public void addConnection(Connection connection) {

    }

}
