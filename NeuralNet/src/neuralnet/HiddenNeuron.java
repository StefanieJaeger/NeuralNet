/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnet;

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
        double e = 0;
        int xw = 0;
        for(Connection c : incommingConnections)
            xw+=c.getWeight() * c.getInputValue();
        e = xw-bias;
        value = 1 / (1 + Math.exp(e * -1));
    }
}