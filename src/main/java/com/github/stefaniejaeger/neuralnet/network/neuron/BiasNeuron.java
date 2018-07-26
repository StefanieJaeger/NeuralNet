package com.github.stefaniejaeger.neuralnet.network.neuron;

import com.github.stefaniejaeger.neuralnet.network.Connection;

public class BiasNeuron extends Neuron {

    public BiasNeuron(Double bias) {
        value = bias;
    }

    @Override
    public void calculateValue() { }

    @Override
    public void reset() { }

    @Override
    public void addConnection(Connection connection) { }

    @Override
    public String toString() {
        return "Bias neuron with value " + value;
    }

}
