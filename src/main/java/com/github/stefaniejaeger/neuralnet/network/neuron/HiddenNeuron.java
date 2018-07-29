package com.github.stefaniejaeger.neuralnet.network.neuron;

import com.github.stefaniejaeger.neuralnet.network.Connection;
import com.github.stefaniejaeger.neuralnet.network.neuron.common.Helper;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stefanie
 */
public class HiddenNeuron extends Neuron {

    private List<Connection> incomingConnections;

    public HiddenNeuron() {
        incomingConnections = new ArrayList<>();
    }

    @Override
    public void calculateValue() {
        value = Helper.calculateNeuronValue(incomingConnections);
    }

    @Override
    public void addConnection(Connection connection) {
        incomingConnections.add(connection);
    }

    @Override
    public String toString() {
        String text = "hidden neuron with ";
        for (Connection con : incomingConnections) {
            text += con.toString() + ", ";
        }
        text += "making its value " + getValue();
        return text;
    }

}
