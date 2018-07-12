package com.github.stefaniejaeger.neuralnet.network.layer;

import com.github.stefaniejaeger.neuralnet.network.neuron.Neuron;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stefanie
 */
public abstract class Layer {
    public List<Neuron> neurons = new ArrayList<>();
    
    public List<Neuron> getNeurons(){
        return this.neurons;
    }
    
    @Override
    public String toString(){
        String sNeurons = "";
        for(Neuron neuron : this.neurons)
            sNeurons += neuron.toString() + " ";
        return "Layer contains " + this.neurons.size() + " neurons: " + sNeurons;
    }
}
