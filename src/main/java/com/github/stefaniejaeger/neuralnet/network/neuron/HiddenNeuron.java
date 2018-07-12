package com.github.stefaniejaeger.neuralnet.network.neuron;

import com.github.stefaniejaeger.neuralnet.network.Connection;

import java.util.List;

/**
 *
 * @author Stefanie
 */
public class HiddenNeuron extends Neuron {
    private List<Connection> incommingConnections;
    private double bias;
    
    public HiddenNeuron(double bias){
        this.bias = bias;
    }
    
    @Override
    public void addConnections(List<Connection> connections){    
        this.incommingConnections = connections;
    }
    
    public List<Connection> getConnections(){
        return this.incommingConnections;
    }
    
    @Override
    public void calculateValue(){
        double e;
        double xw = 0;
        //Sigmoid
        for(Connection c : incommingConnections)
            xw+=c.calculateOutput();
        e = xw-bias;
        value = 1 / (1 + Math.exp(e * -1));
    }

    @Override
    public String toString() {
        String text = "hidden neuron with ";
        for(Connection con : incommingConnections)
            text += con.toString() + ", ";
        text += "has bias " + bias + " making its value " + getValue();
        return text;
    }
}
