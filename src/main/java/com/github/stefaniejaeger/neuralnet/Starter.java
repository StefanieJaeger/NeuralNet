package com.github.stefaniejaeger.neuralnet;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Stefanie
 */
public class Starter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //configurate the neural net structure
        NeuralNetConfiguration config = new NeuralNetConfiguration();
        config.numberOfInputNeurons = 2;
        config.numberOfOutputNeurons = 1;
        config.numberOfHiddenLayers = 1;
        config.numberOfNeuronsPerHiddenLayer = 3;
        config.bias = 1;
        NeuralNet net = new NeuralNet(config);
        
        //inputs and expected outputs
        List<Double> inputs = Arrays.asList(1.0,0.0);
        List<Double> expOutputs = Arrays.asList(1.0);
        
        //genetic alogrithm with crossoverRate 0.7, mutationRate 0.1, population count 10 and chromosone count per genome
        GeneticAlgorithm genAlg = new GeneticAlgorithm(net, 0.7, 0.1, 10, 9);
        
        //test, score and print the genomes and neural net
        genAlg.testPrintAndScorePopulation(inputs, expOutputs);
        
        
        
    }
    
}
