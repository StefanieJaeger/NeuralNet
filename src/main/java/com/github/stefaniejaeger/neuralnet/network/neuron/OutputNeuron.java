package com.github.stefaniejaeger.neuralnet.network.neuron;

import com.github.stefaniejaeger.neuralnet.network.Connection;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stefanie
 */
public class OutputNeuron extends Neuron {

    private List<Connection> incomingConnections;
    
    public OutputNeuron(){
        incomingConnections = new ArrayList<>();
    }

    @Override
    public void addConnection(Connection connection) {
        incomingConnections.add(connection);
    }

    @Override
    public void reset() {
        value = null;
    }

    @Override
    public void calculateValue(){
        double e = 0;
        double xw = 0;
        //Sigmoid
        for(Connection c : incomingConnections)
            xw+=c.calculateOutput();
        e = xw;
        value = 1 / (1 + Math.exp(e * -1));
        value = (value - 0.5) * 2;
    }
    
    @Override
    public String toString() {
        String text = "output neuron has ";
        for(Connection con : incomingConnections)
            text += con.toString() + ", ";
        text += '\n'+" making its value " + getValue();
        return text;
    }
}
