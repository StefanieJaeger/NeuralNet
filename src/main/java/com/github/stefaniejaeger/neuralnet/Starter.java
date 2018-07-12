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
        netConfig.bias = 2;
        NeuralNet net = new NeuralNet(netConfig);
        
        GeneticAlgorithmConfiguration algConfig = new GeneticAlgorithmConfiguration();
        algConfig.chromosomeLengthOfGenome = 9;
        algConfig.crossoverRate = 0.7;
        algConfig.mutationRate = 0.15;
        algConfig.neuralNet = net;
        algConfig.populationSize = 5;
        
        //inputs and expected outputs
        List<Double> inputs1 = Arrays.asList(0.0,0.0);
        List<Double> expOutputs1 = Arrays.asList(1.0);
        List<Double> inputs2 = Arrays.asList(0.0,1.0);
        List<Double> expOutputs2 = Arrays.asList(0.0);
        List<Double> inputs3 = Arrays.asList(1.0,0.0);
        List<Double> expOutputs3 = Arrays.asList(0.0);
        List<Double> inputs4 = Arrays.asList(1.0,1.0);
        List<Double> expOutputs4 = Arrays.asList(1.0);
        
        //genetic alogrithm with crossoverRate 0.7, mutationRate 0.1, population count 10 and chromosone count per genome
        GeneticAlgorithm genAlg = new GeneticAlgorithm(algConfig);
        
        //test, score and print the genomes and neural net
        genAlg.testPrintAndScorePopulation(inputs1, expOutputs1);
        genAlg.testPrintAndScorePopulation(inputs2, expOutputs2);
        genAlg.testPrintAndScorePopulation(inputs3, expOutputs3);
        genAlg.testPrintAndScorePopulation(inputs4, expOutputs4);
        
        //System.out.println(genAlg.winner);
        
        int countr = 0;
        while(!genAlg.isDone() && countr < 5){
            genAlg.makeNextGeneration();
            genAlg.testPrintAndScorePopulation(inputs1, expOutputs1);
            genAlg.testPrintAndScorePopulation(inputs2, expOutputs2);
            genAlg.testPrintAndScorePopulation(inputs3, expOutputs3);
            genAlg.testPrintAndScorePopulation(inputs4, expOutputs4);
            countr++;
        }
        
    }
    
}
