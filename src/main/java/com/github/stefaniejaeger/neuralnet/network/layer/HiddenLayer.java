package com.github.stefaniejaeger.neuralnet.network.layer;

import com.github.stefaniejaeger.neuralnet.network.neuron.BiasNeuron;
import com.github.stefaniejaeger.neuralnet.network.neuron.HiddenNeuron;
import com.github.stefaniejaeger.neuralnet.network.neuron.Neuron;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stefanie
 */
public class HiddenLayer implements Layer {

    private BiasNeuron biasNeuron;
    private List<HiddenNeuron> hiddenNeurons;

    public HiddenLayer(BiasNeuron biasNeuron, List<HiddenNeuron> hiddenNeurons) {
        this.biasNeuron = biasNeuron;
        this.hiddenNeurons = hiddenNeurons;
    }

    public HiddenLayer(BiasNeuron biasNeuron, int numberOfNeurons) {
        this.biasNeuron = biasNeuron;

        this.hiddenNeurons = new ArrayList<>();
        for (int i = 0; i < numberOfNeurons; i++) {
            hiddenNeurons.add(new HiddenNeuron());
        }
    }

    @Override
    public List<Neuron> getNeurons() {
        List<Neuron> neurons = new ArrayList<>();
        neurons.add(biasNeuron);
        neurons.addAll(hiddenNeurons);

        return neurons;
    }

    public List<HiddenNeuron> getHiddenNeurons() {
        return hiddenNeurons;
    }

}
