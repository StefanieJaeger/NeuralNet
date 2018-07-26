package com.github.stefaniejaeger.neuralnet.network;

import com.github.stefaniejaeger.neuralnet.network.neuron.Neuron;

/**
 *
 * @author Stefanie
 */
public class Connection {
    private double weight;
    private Neuron from;

    public Connection(double weight, Neuron from){
        this.weight = weight;
        this.from = from;
    }

    public Connection(Neuron from){
        this.from = from;
    }

    public double calculateOutput(){
        return weight * from.getValue();
    }

    @Override
    public String toString() {
        return "connection from " + from.toString() + " and weight " + weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

}
