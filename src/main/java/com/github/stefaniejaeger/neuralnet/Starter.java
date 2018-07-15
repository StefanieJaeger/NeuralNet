package com.github.stefaniejaeger.neuralnet;

import com.github.stefaniejaeger.neuralnet.algorithm.GeneticAlgorithm;
import com.github.stefaniejaeger.neuralnet.algorithm.GeneticAlgorithmConfiguration;
import com.github.stefaniejaeger.neuralnet.network.NeuralNet;
import com.github.stefaniejaeger.neuralnet.network.NeuralNetConfiguration;

import java.util.ArrayList;
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
        netConfig.numberOfHiddenLayers = 2;
        netConfig.numberOfNeuronsPerHiddenLayer = 4;
        netConfig.bias = 1;
        NeuralNet net = new NeuralNet(netConfig);
        
        GeneticAlgorithmConfiguration algConfig = new GeneticAlgorithmConfiguration();
        algConfig.chromosomeLengthOfGenome = 37;
        algConfig.crossoverRate = 0.7;
        algConfig.mutationRate = 0.05;
        algConfig.neuralNet = net;
        algConfig.populationSize = 100;

        List<Test> testCases = new ArrayList<>();
        testCases.add(new Test(Arrays.asList(0.0,0.0), Arrays.asList(1)));
        testCases.add(new Test(Arrays.asList(0.0,1.0), Arrays.asList(0)));
        testCases.add(new Test(Arrays.asList(1.0,0.0), Arrays.asList(0)));
        testCases.add(new Test(Arrays.asList(1.0,1.0), Arrays.asList(1)));

        //genetic algorithm with crossoverRate 0.7, mutationRate 0.1, population count 10 and chromosome count per genome
        GeneticAlgorithm genAlg = new GeneticAlgorithm(algConfig);
        
        genAlg.testPrintAndScorePopulation(testCases);
        
        //System.out.println(genAlg.winner);

        int countr = 0;
        while(!genAlg.isDone()){
            genAlg.makeNextGeneration();
            genAlg.testPrintAndScorePopulation(testCases);
            countr++;
        }
        genAlg.writer.close();

    }

}
