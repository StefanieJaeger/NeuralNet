package com.github.stefaniejaeger.neuralnet.network.layer;

import com.github.stefaniejaeger.neuralnet.network.neuron.InputNeuron;

import java.util.List;

/**
 *
 * @author Stefanie
 */
public class InputLayer extends Layer {
    public InputLayer(int numberOfNeurons){
        for(int i = 0; i < numberOfNeurons; i++){
            neurons.add(new InputNeuron());
        }
    }  
    
    public void setInputs(List<Double> inputs){
        if(inputs.size() != neurons.size())
            return;
        
        for(int i = 0; i < inputs.size(); i++){
            InputNeuron neuron = (InputNeuron)neurons.get(i);
            neuron.setValue(inputs.get(i));
        }
    }
}
