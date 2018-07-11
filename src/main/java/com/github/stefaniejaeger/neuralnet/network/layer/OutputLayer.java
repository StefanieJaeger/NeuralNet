package com.github.stefaniejaeger.neuralnet.network.layer;

import com.github.stefaniejaeger.neuralnet.network.neuron.Neuron;
import com.github.stefaniejaeger.neuralnet.network.neuron.OutputNeuron;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stefanie
 */
public class OutputLayer extends Layer {
    public OutputLayer(int numberOfNeurons){
        for(int i = 0; i< numberOfNeurons; i++){
            neurons.add(new OutputNeuron());
        }
    }
    
    public List<Double> getOutputs(){
        List<Double> outputs = new ArrayList<>();
        for(Neuron neuron : neurons)
            outputs.add(neuron.getValue());
        return outputs;
    }
}