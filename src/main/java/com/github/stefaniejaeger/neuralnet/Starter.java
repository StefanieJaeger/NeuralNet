package com.github.stefaniejaeger.neuralnet;

import com.github.stefaniejaeger.neuralnet.algorithm.GeneticAlgorithm;
import com.github.stefaniejaeger.neuralnet.algorithm.GeneticAlgorithmConfiguration;
import com.github.stefaniejaeger.neuralnet.network.NeuralNet;
import com.github.stefaniejaeger.neuralnet.network.NeuralNetConfiguration;

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
        NeuralNetConfiguration netConfig = new NeuralNetConfiguration();
        netConfig.numberOfInputNeurons = 2;
        netConfig.numberOfOutputNeurons = 1;
        netConfig.numberOfHiddenLayers = 1;
        netConfig.numberOfNeuronsPerHiddenLayer = 3;
        netConfig.bias = 1;
        NeuralNet net = new NeuralNet(netConfig);
        
        GeneticAlgorithmConfiguration algConfig = new GeneticAlgorithmConfiguration();
        algConfig.chromosomeLengthOfGenome = 9;
        algConfig.crossoverRate = 0.7;
        algConfig.mutationRate = 0.1;
        algConfig.neuralNet = net;
        algConfig.populationSize = 10;
        
        //inputs and expected outputs
        List<Double> inputs = Arrays.asList(1.0,0.0);
        List<Double> expOutputs = Arrays.asList(1.0);
        
        //genetic alogrithm with crossoverRate 0.7, mutationRate 0.1, population count 10 and chromosone count per genome
        GeneticAlgorithm genAlg = new GeneticAlgorithm(algConfig);
        
        //test, score and print the genomes and neural net
        genAlg.testPrintAndScorePopulation(inputs, expOutputs);
        
        
        
    }
    
}
