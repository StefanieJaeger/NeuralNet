/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnet;

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
        double x = weight + from.getValue();
        double result = 1 / (1 + Math.exp(-x));
        return result;
    }
}
