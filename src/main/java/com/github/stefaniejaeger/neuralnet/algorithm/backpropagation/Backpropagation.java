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
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jaeger
 */
public class Backpropagation {
    private NeuralNet network;
    private Test currentTest;
    private List<ChangesForTest> changesAndTest;
    
    public Backpropagation(NeuralNet net) {
        network = net;
        changesAndTest = new ArrayList<>();
    }
    
    /**
     * Finds changes for weights and biases in neural net to help it get closer to result
     * @param tests
     * @return Object with new values for weights, biases and neurons
     */
    public ChangesForTest propagate(List<Test> tests) {
        for(Test test : tests) {
            currentTest = test;
            try {
            changesAndTest.add(propagateForTest(test));
            } catch (CloneNotSupportedException cE) {
                
            }
        }
        return getFinalChangesForAllTest();
    }
    
    /**
     * Returns changes for the network for a certain test
     * @param test
     * @return 
     */
    private ChangesForTest propagateForTest(Test test) throws CloneNotSupportedException {                 
        //TODO copy network and make changes there. apply changes to last layer,
        //then go to next layer
        NeuralNet neuralNet = (NeuralNet)network.clone();
        ChangesForTest changesForTest = new ChangesForTest(test);
        
        List<Double> weights = getChangeForWeights(neuralNet.getOutputLayer(), false);
        for(Layer hiddenLayer : neuralNet.getHiddenLayers()) {
            weights.addAll(getChangeForWeights(hiddenLayer, true));
        }
        
        changesForTest.setWeightChanges(weights);
        neuralNet.setWeights(weights);
        
        List<Double> biases = Arrays.asList(getChangeForBias(neuralNet.getOutputLayer(), false));
        for(Layer hiddenLayer : neuralNet.getHiddenLayers()) {
            biases.add(getChangeForBias(hiddenLayer, true));
        }
        
        changesForTest.setBiasChanges(biases);
        
        return changesForTest;
    }
    
    /**
     * Calculate the average change for every single weight and bias from 
     * the changes every test wants
     * @return 
     */
    private ChangesForTest getFinalChangesForAllTest(){
        List<Double> doubles = new ArrayList<>();
        List<Integer> ints = new ArrayList<>();
        ChangesForTest average = new ChangesForTest(new Test(doubles, ints));
        
        List<Double> weights = new ArrayList<>();
        List<Double> biases = new ArrayList<>();
        
        int weightCounter = 0;
        int biasCounter = 0;
        
        for(ChangesForTest change : changesAndTest){
            weightCounter = 0;
            biasCounter = 0;
            
            for(double weightChange : change.getWeightChanges()){
                weights.set(weightCounter, weights.get(weightCounter) + weightChange);
                weightCounter++;
            }
            for(double biasChange : change.getBiasChanges()){
                biases.set(biasCounter, biases.get(biasCounter) + biasChange);
                biasCounter++;
            }
        }       
        
        for(int i = 0; i < weights.size(); i++){
            weights.set(i, weights.get(i) / weightCounter);
        }
        for(int i = 0; i < biases.size(); i++){
            biases.set(i, biases.get(i) / biasCounter);
        }
        
        average.setWeightChanges(weights);
        average.setBiasChanges(biases);
        
        return average;
    }
    
    /**
     * The cost is the difference between the expected output 
     * and actual output. We square it to eliminate negative values
     * @param neuron The neuron whose cost we want
     * its expected output (only works for output neurons)
     * @param layer 
     * @return 
     */
    private Double getCost(Neuron neuron, Layer layer) {
        int expectedOutput = (int)getExpectedValue(neuron, layer);
        Double output = neuron.getValue();
        return (expectedOutput - output) * (expectedOutput - output);
    }
    
    /**
     * Get the impact of a change of a certain weight on the overall cost of a certain neuron
     * @param connection The connection whose weight to use
     * @param currentNeuron The neuron whose value to use
     * @param layer The layer the neuron is in
     * @param isHiddenLayer Whether the layer is a hidden or an output layer
     * @return 
     */
    private Double getWeightAndCostDerivativeRelation(Connection connection, Neuron currentNeuron, Layer layer, boolean isHiddenLayer) {
        //dC0/dwL = dzL/dwL * daL/dzL * dC0L/daL   
        double biasValue = layer.getBiasNeuron().getValue();
        double previousNeuronValue = connection.getSource().getValue();
        double zValue = connection.getWeight() * previousNeuronValue * biasValue;
        double neuronValue = currentNeuron.getValue();
        double costAnNeuronDerivativeRelation;
        double neuronAndZDerivativeRelation;
        double zAndWeightDerivativeRelation;
        double costAndWeightDerivativeRelation;
                
        if(isHiddenLayer) {
            costAnNeuronDerivativeRelation = getGradientOfHiddenLayerWithCost();       
        } else {
            //simple calculation to get value for dC0/aL for output neuron
            costAnNeuronDerivativeRelation = 2*(neuronValue - getExpectedValue(currentNeuron, layer));         
        }
         
        zAndWeightDerivativeRelation = previousNeuronValue;
        neuronAndZDerivativeRelation = derivativeSigmoid(zValue);
        
        costAndWeightDerivativeRelation = zAndWeightDerivativeRelation * neuronAndZDerivativeRelation * costAnNeuronDerivativeRelation;
        
        return costAndWeightDerivativeRelation;
    }
    
        private Double getBiasAndCostDerivativeRelation(Connection connection, Neuron currentNeuron, Layer layer, boolean isHiddenLayer) {
        //dC0/dbL = dzL/dbL * daL/dzL * dC0L/daL   
        double biasValue = layer.getBiasNeuron().getValue();
        double previousNeuronValue = connection.getSource().getValue();
        double zValue = connection.getWeight() * previousNeuronValue * biasValue;
        double neuronValue = currentNeuron.getValue();
        double costAnNeuronDerivativeRelation;
        double zAndWeightDerivativeRelation;
        double neuronAndZDerivativeRelation;
        double costAndBiasDerivativeRelation;
                
        if(isHiddenLayer) {
            costAnNeuronDerivativeRelation = getGradientOfHiddenLayerWithCost();       
        } else {
            //simple calculation to get value for dC0/aL for output neuron
            costAnNeuronDerivativeRelation = 2*(neuronValue - getExpectedValue(currentNeuron, layer));         
        }
         
        zAndWeightDerivativeRelation = biasValue / biasValue;
        neuronAndZDerivativeRelation = derivativeSigmoid(zValue);
        
        costAndBiasDerivativeRelation = zAndWeightDerivativeRelation * neuronAndZDerivativeRelation * costAnNeuronDerivativeRelation;
        
        return costAndBiasDerivativeRelation;
    }

     /**
      * Gets the changes for all connections between the passed layer and 
      * the previous one.
      * @param layer
      * @param isHiddenLayer
      * @return 
      */
    private List<Double> getChangeForWeights(Layer layer, boolean isHiddenLayer) {
        List<Double> weights = new ArrayList<>();
        for(Neuron neuron : layer.getNeurons()) {
            //TODO find how to define cost for non-output layers
            double cost = getCost(neuron, layer);
            weights.add(0.0);
            for(Connection connection : neuron.getConnections()) {            
                double newWeightValue = getWeightAndCostDerivativeRelation(connection, neuron, layer, isHiddenLayer) * cost;
                weights.set(weights.size()-1, newWeightValue);
            }
            weights.set(weights.size()-1, weights.get(weights.size() -1) / neuron.getConnections().size());
        }
        return weights;
    }
    
    /**
     * Get the changes for the bias in the specified layer
     * @param layer
     * @param isHiddenLayer
     * @return 
     */
    private Double getChangeForBias(Layer layer, boolean isHiddenLayer){
        List<Double> biases = new ArrayList<>();
        double bias;
        for(Neuron neuron : layer.getNeurons()) {
            //TODO find how to define cost for non-hidden layers
            double cost = getCost(neuron, layer);
            biases.add(0.0);
            for(Connection connection : neuron.getConnections()) {            
                double newBiasValue = getBiasAndCostDerivativeRelation(connection, neuron, layer, isHiddenLayer) * cost;
                // Add up every change every connection wants to make
                biases.set(biases.size()-1, newBiasValue);
            }
            // Get the average change every connection of a neuron wants to make
            biases.set(biases.size()-1, biases.get(biases.size() -1) / neuron.getConnections().size());
        }
        bias = biases.stream().mapToInt(Integer::intValue).sum();
        return bias / biases.size();
    }
    
    /**
     * 
     * @return 
     */
    private double getGradientOfHiddenLayerWithCost() {
        //weird E(top: nL+1 (-1), bottom: j=0)  w(L+1)jk * o'(zj(L+1)) * dC/daj(L+1) 
        //TODO implement
        return 0.0;
    }
    
    /**
     * Returns expected value for output neurons
     * @param neuron
     * @param layer
     * @return 
     */
    private int getExpectedValue(Neuron neuron, Layer layer) {
        int indexOfNeuron = layer.getNeurons().indexOf(neuron);
        return currentTest.getExpectedOutputs().get(indexOfNeuron);
    }
    
    /**
     * 
     * @param z
     * @return 
     */
    private double derivativeSigmoid(Double z) {
        double sigmoid = 1 / (1 + Math.exp(z * -1));
        return sigmoid * (1 - sigmoid);     
    }
}
