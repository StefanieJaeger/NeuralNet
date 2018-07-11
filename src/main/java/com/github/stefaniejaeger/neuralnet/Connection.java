package com.github.stefaniejaeger.neuralnet;

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
    
    public double calculateOutput(){
        double x = weight * from.getValue();
        double result = 1 / (1 + Math.exp(-x));
        return result;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public double getInputValue(){
        return from.getValue();
    }
}
