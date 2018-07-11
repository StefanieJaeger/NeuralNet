package com.github.stefaniejaeger.neuralnet.network.neuron;

/**
 *
 * @author Stefanie
 */
public class InputNeuron extends Neuron {
    
    public void setValue(double input){
        super.value = input;
    }


    @Override
    public void calculateValue() {
        // Input neurons do not need to calculate an output value
    }
}
