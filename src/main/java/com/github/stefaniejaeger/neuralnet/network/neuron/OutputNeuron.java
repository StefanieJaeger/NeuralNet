package com.github.stefaniejaeger.neuralnet.network.neuron;

import com.github.stefaniejaeger.neuralnet.network.Connection;

import java.util.List;

/**
 *
 * @author Stefanie
 */
public class OutputNeuron extends Neuron {
    private List<Connection> incommingConnections;
    
    public OutputNeuron(){
        
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
        double e = 0;
        int xw = 0;
        //Sigmoid
        for(Connection c : incommingConnections)
            xw+=c.calculateOutput();
        e = xw;
        value = 1 / (1 + Math.exp(e * -1));
    }
    
    @Override
    public String toString() {
        String text = "output neuron has ";
        for(Connection con : incommingConnections)
            text += con.toString() + ", ";
        text += " making its value " + getValue();
        return text;
    }
}
