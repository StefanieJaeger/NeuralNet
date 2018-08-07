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
    Double calculateValue() {
        return Helper.calculateNeuronValue(incomingConnections);
    }

    @Override
    public void addConnection(Connection connection) {
        incomingConnections.add(connection);
    }

    public Integer getNumberOfIncomingConnections() {
        return incomingConnections.size();
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder("hidden neuron with ");
        for (Connection con : incomingConnections) {
            text.append(con.toString()).append(", ");
        }
        text.append("making its value ").append(getValue());
        return text.toString();
    }

}
