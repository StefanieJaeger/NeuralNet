/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.stefaniejaeger.neuralnet.algorithm.backpropagation;

import com.github.stefaniejaeger.neuralnet.Test;
import com.github.stefaniejaeger.neuralnet.network.Connection;
import com.github.stefaniejaeger.neuralnet.network.NeuralNet;
import com.github.stefaniejaeger.neuralnet.network.layer.Layer;
import com.github.stefaniejaeger.neuralnet.network.neuron.Neuron;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jaeger
 */
public class Backpropagation {
    private NeuralNet network;
    private Test test;
    
    public Backpropagation(NeuralNet net){
        network = net;
    }
    
    public void propagate(Test test){
        this.test = test;
        
        List<Double> weights = network.getWeights();
        List<Double> changes = getChangesForWeights(network.getOutputLayer());
        weights = weights.subList(0, weights.size() - changes.size());
        weights.addAll(0, changes);
        network.setWeights(weights);
    }
    
    private Double getCost(Neuron neuron, int positionInLayer){
        int expectedOutput;
        int output;
        return ()
    }
    
    private Double getWantedChangeForWeight(){
    
    }
    
    private Double getWantedChangeForPreviousNeuron(){
    
    }
    
    private Double getWantedChangeForBias(){
    
    }
    
    private List<Double> getChangesForWeights(Layer layer){
        List<Double> weights = new ArrayList<>();
        for(Neuron neuron : layer.getNeurons()){
            weights.add(0.0);
            for(Connection connection : neuron.getConnections()){
                weights.set(weights.size()-1, getWantedChangeForWeight() * getCost());
            }
            weights.set(weights.size()-1, weights.get(weights.size() -1) / neuron.getConnections().size());
        }
        return weights;
    }
}
