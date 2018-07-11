package com.github.stefaniejaeger.neuralnet;

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
}
