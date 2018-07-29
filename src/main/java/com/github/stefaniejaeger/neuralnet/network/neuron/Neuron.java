package com.github.stefaniejaeger.neuralnet.network.neuron;

import com.github.stefaniejaeger.neuralnet.network.Connection;

/**
 *
 * @author Stefanie
 */
public abstract class Neuron {

    Double value = null;

    abstract Double calculateValue();

    public final Double getValue(){
        if (value == null) {
            value = calculateValue();
        }

        return value;
    }

    public void reset() {
        value = null;
    }

    public abstract void addConnection(Connection connection);

}
