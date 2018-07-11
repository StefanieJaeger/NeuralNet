package com.github.stefaniejaeger.neuralnet.network.layer;

import com.github.stefaniejaeger.neuralnet.network.neuron.HiddenNeuron;
import com.github.stefaniejaeger.neuralnet.network.neuron.Neuron;

import java.util.List;

/**
 *
 * @author Stefanie
 */
public class HiddenLayer extends Layer {

    public HiddenLayer(int numberOfNeurons, double bias){
        for(int i = 0; i < numberOfNeurons; i++){
            neurons.add(new HiddenNeuron(bias));
        }
    }
}
