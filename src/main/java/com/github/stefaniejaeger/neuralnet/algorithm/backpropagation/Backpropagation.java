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
        int expectedOutput = getExpectedOutput(positionInLayer);
        Double output = neuron.getValue();
        return (expectedOutput - output) * (expectedOutput - output);
    }
    
    private Double getWantedChangeForWeight(Connection connection, Neuron neuron, Layer layer){
        //dC0/dwL = dzL/dwL * daL/dzL * dC0L/daL
        double difNeuron;       
        double valuePreviousNeuron;
        
        double difCostDifNeuron = 2(divNeuron - y);
        double difNeuronDifZ;
        double difZDifWeight = valuePreviousNeuron;
        double difCostDvWeight = difCostDifNeuron * difNeuronDifZ * difZDifWeight;
        
        
        double divNeuron = 0.0;
        double wantedResult = 1.0;
        double divCost = divNeuron - wantedResult;
        double divz;
        double divWeight;
        double divPreviousNeuron;
        double divBias = layer.getBiasNeuron();
    }
    
    private Double getWantedChangeForPreviousNeuron(){
        //dC0/daL-1 = dzL/daL-1 * daL/dzL * dC0L/daL
        double divNeuron = 0.0;
        double wantedResult = 1.0;
        double divCost = 2*(divNeuron - wantedResult);
        double divz;
        double divWeight;
        double divPreviousNeuron;    
        double divBias = layer.getBiasNeuron();
    }
    
    private Double getWantedChangeForBias(){
        //dC0/dbL = dzL/dbL * daL/dzL * dC0L/daL
        double divNeuron = 0.0;
        double wantedResult = 1.0;
        double divCost = 2*(divNeuron - wantedResult);
        double divz;
        double divWeight;
        double divPreviousNeuron;   
        double divBias = layer.getBiasNeuron();
    }
    
    private int getExpectedOutput(int positionInLayer){
        return test.getExpectedOutputAtIndex(positionInLayer);
    }
    
    private List<Double> getChangesForWeights(Layer layer){
        List<Double> weights = new ArrayList<>();
        for(Neuron neuron : layer.getNeurons()){
            weights.add(0.0);
            for(Connection connection : neuron.getConnections()){
                weights.set(weights.size()-1, getWantedChangeForWeight(connection, neuron) * getCost(neuron, layer.getNeurons().indexOf(neuron)));
            }
            weights.set(weights.size()-1, weights.get(weights.size() -1) / neuron.getConnections().size());
        }
        return weights;
    }
}
