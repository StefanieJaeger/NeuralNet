package com.github.stefaniejaeger.neuralnet.network.layer;

import com.github.stefaniejaeger.neuralnet.network.neuron.BiasNeuron;
import com.github.stefaniejaeger.neuralnet.network.neuron.InputNeuron;
import com.github.stefaniejaeger.neuralnet.network.neuron.Neuron;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stefanie
 */
public class InputLayer implements Layer {

    private BiasNeuron biasNeuron;
    private List<InputNeuron> inputNeurons;

    public InputLayer(BiasNeuron biasNeuron, int numberOfNeurons) {
        this.biasNeuron = biasNeuron;

        this.inputNeurons = new ArrayList<>();
        for (int i = 0; i < numberOfNeurons; i++) {
            inputNeurons.add(new InputNeuron());
        }
    }

    @Override
    public List<Neuron> getNeurons() {
        List<Neuron> neurons = new ArrayList<>();
        neurons.add(biasNeuron);
        neurons.addAll(inputNeurons);

        return neurons;
    }

    public List<InputNeuron> getInputNeurons() {
        return inputNeurons;
    }

}
