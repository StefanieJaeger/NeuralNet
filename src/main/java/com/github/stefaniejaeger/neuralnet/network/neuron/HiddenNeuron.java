package com.github.stefaniejaeger.neuralnet.network.neuron;

import com.github.stefaniejaeger.neuralnet.network.Connection;

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
    public void calculateValue(){
        double e;
        double xw = 0;
        //Sigmoid
        for(Connection c : incomingConnections)
            xw+=c.calculateOutput();
        e = xw;
        value = 1 / (1 + Math.exp(e * -1));
        value = (value - 0.5) * 2;
    }

    @Override
    public void addConnection(Connection connection) {
        incomingConnections.add(connection);
    }

    @Override
    public String toString() {
        String text = "hidden neuron with ";
        for(Connection con : incomingConnections)
            text += con.toString() + ", ";
        text += "making its value " + getValue();
        return text;
    }

}
