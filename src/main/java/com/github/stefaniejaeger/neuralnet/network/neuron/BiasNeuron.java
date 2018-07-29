package com.github.stefaniejaeger.neuralnet.network.neuron;

import com.github.stefaniejaeger.neuralnet.network.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BiasNeuron extends Neuron {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public BiasNeuron(Double bias) {
        value = bias;
    }

    @Override
    Double calculateValue() {
        LOGGER.error("Calling calculateValue on a bias neuron");
        throw new UnsupportedOperationException();
    }

    @Override
    public void reset() {
        LOGGER.error("Calling reset on a bias neuron");
        throw new UnsupportedOperationException();
    }

    @Override
    public void addConnection(Connection connection) {
        LOGGER.error("Calling addConnection on a bias neuron");
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "Bias neuron with value " + value;
    }

}
