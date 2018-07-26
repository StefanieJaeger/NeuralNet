package com.github.stefaniejaeger.neuralnet.network.neuron;

import com.github.stefaniejaeger.neuralnet.network.Connection;

/**
 *
 * @author Stefanie
 */
public abstract class Neuron {

    Double value = null;

    public double getValue(){
        if (value == null) {
            calculateValue();
        }

        return value;
    }

    public abstract void calculateValue();

    public void reset() {
        value = null;
    }

    public abstract void addConnection(Connection connection);

}
