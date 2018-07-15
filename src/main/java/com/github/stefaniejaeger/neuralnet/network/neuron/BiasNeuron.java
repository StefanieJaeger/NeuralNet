package com.github.stefaniejaeger.neuralnet.network.neuron;

import com.github.stefaniejaeger.neuralnet.network.Connection;

import java.util.List;

public class BiasNeuron extends Neuron {
    public BiasNeuron(Double bias) {
        value = bias;
    }

    @Override
    public void calculateValue() {

    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public void addConnections(List<Connection> cons) {

    }
}
