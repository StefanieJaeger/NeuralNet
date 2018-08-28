package com.github.stefaniejaeger.neuralnet;

import com.github.stefaniejaeger.neuralnet.algorithm.backpropagation.Backpropagation;
import com.github.stefaniejaeger.neuralnet.algorithm.genetic.GeneticAlgorithm;
import com.github.stefaniejaeger.neuralnet.algorithm.genetic.GeneticAlgorithmConfiguration;
import com.github.stefaniejaeger.neuralnet.algorithm.genetic.RandomProvider;
import com.github.stefaniejaeger.neuralnet.algorithm.genetic.RealRandomProvider;
import com.github.stefaniejaeger.neuralnet.network.NeuralNet;
import com.github.stefaniejaeger.neuralnet.network.NeuralNetConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StarterGeneticXOR {
    private static NeuralNet neuralNet;
    private static List<Test> testCases = new ArrayList<>();

    public static void main(String[] args) {
        NeuralNetConfiguration netConfig = new NeuralNetConfiguration();
        netConfig.numberOfInputNeurons = 64;
        netConfig.numberOfOutputNeurons = 1;
        netConfig.numberOfHiddenLayers = 1;
        netConfig.numberOfNeuronsPerHiddenLayer = 2;
        netConfig.bias = 1;
        neuralNet = new NeuralNet(netConfig);

        GeneticAlgorithmConfiguration algConfig = new GeneticAlgorithmConfiguration();
        algConfig.chromosomeLengthOfGenome = netConfig.getRequiredNumberOfWeights();
        algConfig.crossoverRate = 0.7;
        algConfig.mutationRate = 0.1;
        algConfig.neuralNet = neuralNet;
        algConfig.populationSize = 200;

        testCases.add(new Test(Arrays.asList(0.0, 0.0), Arrays.asList(1)));
        testCases.add(new Test(Arrays.asList(0.0, 1.0), Arrays.asList(0)));
        testCases.add(new Test(Arrays.asList(1.0, 0.0), Arrays.asList(0)));
        testCases.add(new Test(Arrays.asList(1.0, 1.0), Arrays.asList(1)));

        RandomProvider randomProvider = new RealRandomProvider();

        //genetic algorithm with crossoverRate 0.7, mutationRate 0.1, population count 10 and chromosome count per genome
        GeneticAlgorithm genAlg = new GeneticAlgorithm(algConfig, randomProvider);

        startTesting(genAlg);
    }

    public static void startTesting(GeneticAlgorithm genAlg) {
        genAlg.testPrintAndScorePopulation(testCases);

        while(!genAlg.isDone()){
            genAlg.makeNextGeneration();
            genAlg.testPrintAndScorePopulation(testCases);
        }
        System.out.println(genAlg.winner);
        genAlg.writer.close();
    }
}
