package com.github.stefaniejaeger.neuralnet.network.layer;

import com.github.stefaniejaeger.neuralnet.network.neuron.Neuron;
import com.github.stefaniejaeger.neuralnet.network.neuron.OutputNeuron;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stefanie
 */
public class OutputLayer implements Layer {

    private List<OutputNeuron> outputNeurons;

    public OutputLayer(int numberOfNeurons) {
        outputNeurons = new ArrayList<>();

        for(int i = 0; i< numberOfNeurons; i++){
            outputNeurons.add(new OutputNeuron());
        }
    }

    @Override
    public List<Neuron> getNeurons() {
        return new ArrayList<>(outputNeurons);
    }

    public List<OutputNeuron> getOutputNeurons() {
        return outputNeurons;
    }

}
