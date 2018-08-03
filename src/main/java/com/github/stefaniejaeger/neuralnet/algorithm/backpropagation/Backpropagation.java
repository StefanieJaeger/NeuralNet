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
    private List<Test> tests;
    private Test currentTest;
    private List<ChangesForTest> changesAndTest;
    
    public Backpropagation(NeuralNet net) {
        network = net;
        changesAndTest = new ArrayList<>();
    }
    
    public void propagate(List<Test> tests) {
        this.tests = tests;
        for(Test test : tests) {
            currentTest = test;
            propagateForTest(test);
        }
    }
    
    private void propagateForTest(Test test) {                 
        List<Double> changes = getChangesForWeights(network.getOutputLayer());
        for(Layer hiddenLayer : network.getHiddenLayers()){
            changes.addAll(getChangesForWeights(hiddenLayer));
        }
        changes.addAll(getChangesForWeights(network.getInputLayer()));
        
        ChangesForTest changesForTest = new ChangesForTest(test);
        changesForTest.setWeightChanges(changes);
        changesAndTest.add(changesForTest);
        //network.setWeights(weights);
    }
    
    /**
     * The cost for a neuron is the difference between the expected output 
     * and actual output. We square it to eliminate negative values
     * @param neuron The neuron whose cost we want
     * @param positionInLayer The position in its layer let's us get
     * its expected output (only works for output neurons)
     * @return 
     */
    private Double getCostForWeight(Neuron neuron) {
        int expectedOutput = (int)getExpectedValue(neuron);
        Double output = neuron.getValue();
        return (expectedOutput - output) * (expectedOutput - output);
    }
    
    private Double getWeightCostGradientRelation(Connection connection, Neuron currentNeuron, Layer layer, boolean isHiddenLayer){
        //dC0/dwL = dzL/dwL * daL/dzL * dC0L/daL        
        double biasValue;
        double previousNeuronValue = connection.getSource().getValue();
        double zValue = connection.getWeight() * previousNeuronValue * biasValue;
        double neuronValue = currentNeuron.getValue();
        double expectedNeuronValue = getExpectedValue(currentNeuron);
        double costNeuronGradRelation;
        double neuronZGradRelation;
        double zWeightGradRelation;
        double costWeightGradRelation;
                
         if(isHiddenLayer) {
        costNeuronGradRelation = 2*(neuronValue - getExpectedValue(currentNeuron));
                } else {
        costNeuronGradRelation;                 
                }
         
        zWeightGradRelation = previousNeuronValue;
        neuronZGradRelation = sig(zValue);
        
        costWeightGradRelation = zWeightGradRelation * neuronZGradRelation * costNeuronGradRelation;
        
        return costWeightGradRelation;
    }
    private Double getNeuronCostGradientRelation(Connection connection, Neuron currentNeuron, boolean isHiddenLayer){
        //dC0/daL-1 = dzL/daL-1 * daL/dzL * dC0L/daL   
        double biasValue;
        double previousNeuronValue = connection.getSource().getValue();
        double zValue = connection.getWeight() * previousNeuronValue * biasValue;
        double neuronValue = currentNeuron.getValue();
        double expectedNeuronValue = getExpectedValue(currentNeuron);
        double costNeuronGradRelation;
        double neuronZGradRelation;
        double zPreviousNeuronGradRelation;
        double costPreviousNeuronGradRelation;
                if(isHiddenLayer) {
        costNeuronGradRelation = previousNeuronValue;
                } else {
        costNeuronGradRelation;                 
                }
        zPreviousNeuronGradRelation = previousNeuronValue;
        neuronZGradRelation = sig(zValue);
        
        costPreviousNeuronGradRelation = zPreviousNeuronGradRelation * neuronZGradRelation * costNeuronGradRelation;
        
        return costPreviousNeuronGradRelation;
    }
    
     private Double getBiasCostGradientRelation(Connection connection, Neuron currentNeuron, Layer layer, boolean isHiddenLayer){
        //dC0/dbL = dzL/dbL * daL/dzL * dC0L/daL        
        double biasValue;
        double previousNeuronValue = connection.getSource().getValue();
        double zValue = connection.getWeight() * previousNeuronValue * biasValue;
        double neuronValue = currentNeuron.getValue();
        double expectedNeuronValue = getExpectedValue(currentNeuron);
        double costNeuronGradRelation;
        double neuronZGradRelation;
        double costBiasGradRelation;
                
         if(isHiddenLayer) {
        costNeuronGradRelation = previousNeuronValue;
                } else {
        costNeuronGradRelation;                
                }
        
        neuronZGradRelation = sig(zValue);
        costBiasGradRelation = neuronZGradRelation * costNeuronGradRelation;
        
        return costBiasGradRelation;
    }
    
    private List<Double> getChangesForWeights(Layer layer){
        List<Double> weights = new ArrayList<>();
        for(Neuron neuron : layer.getNeurons()){
            weights.add(0.0);
            boolean isHiddenLayer;
            /*for(Connection connection : neuron.getConnections()){
                weights.set(weights.size()-1, getWeightCostGradientRelation(connection, neuron, layer, isHiddenLayer) * getCostForWeight(neuron, layer.getNeurons().indexOf(neuron)));
            }*/
            weights.set(weights.size()-1, weights.get(weights.size() -1) / neuron.getConnections().size());
        }
        return weights;
    }
    
    private List<Double> getChangeForPreviousNeuron(Neuron currentNeuron, Neuron previousNeuron) {
        //return getWantedChangeForPreviousNeuron() * getCostForPreviousNeuron();
    }
    
    private double getExpectedValue(Neuron neuron){
        
    }
    
    private double sig(Double z) {
        return 0.0;
        //return Math.ln(z/(1-z));
    }
}
