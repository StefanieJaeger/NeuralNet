package com.github.stefaniejaeger.neuralnet;

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
    
    public void addConnections(List<Connection> connections){    
        this.incommingConnections = connections;
    }
    
    public List<Connection> getConnections(){
        return this.incommingConnections;
    }
    
    @Override
    public void calculateValue(){
        double e;
        int xw = 0;
        //Sigmoid
        for(Connection c : incommingConnections)
            xw+=c.calculateOutput();
        e = xw-bias;
        value = 1 / (1 + Math.exp(e * -1));
    }
}
