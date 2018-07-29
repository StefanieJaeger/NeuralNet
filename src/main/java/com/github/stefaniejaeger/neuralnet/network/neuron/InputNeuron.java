package com.github.stefaniejaeger.neuralnet.network.neuron;

import com.github.stefaniejaeger.neuralnet.network.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Stefanie
 */
public class InputNeuron extends Neuron {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public void setValue(Double input){
        value = input;
    }

    @Override
    Double calculateValue() {
        LOGGER.error("Calling calculateValue on an input neuron");
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void addConnection(Connection connection) {
        LOGGER.error("Calling addConnection on an input neuron");
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "input neuron with value " + value;
    }

}
