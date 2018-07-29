package com.github.stefaniejaeger.neuralnet.network.neuron.common;

import com.github.stefaniejaeger.neuralnet.network.Connection;

import java.util.List;

public class Helper {

    public static Double calculateNeuronValue(List<Connection> incomingConnections) {
        double xw = 0;
        double e;

        for (Connection c : incomingConnections) {
            xw+=c.calculateOutput();
        }

        e = xw;

        //Sigmoid
        double value = 1 / (1 + Math.exp(e * -1));
        return (value - 0.5) * 2;
    }

}
