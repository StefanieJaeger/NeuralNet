package com.github.stefaniejaeger.neuralnet;

import com.github.stefaniejaeger.neuralnet.algorithm.genetic.GeneticAlgorithm;
import com.github.stefaniejaeger.neuralnet.algorithm.genetic.GeneticAlgorithmConfiguration;
import com.github.stefaniejaeger.neuralnet.algorithm.genetic.RandomProvider;
import com.github.stefaniejaeger.neuralnet.algorithm.genetic.RealRandomProvider;
import com.github.stefaniejaeger.neuralnet.network.NeuralNet;
import com.github.stefaniejaeger.neuralnet.network.NeuralNetConfiguration;

import java.util.List;

/**
 *
 * @author Stefanie
 */
public class StarterBackpropagationXOR {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //configurate the neural net structure
        NeuralNetConfiguration netConfig = new NeuralNetConfiguration();
        netConfig.numberOfInputNeurons = 64;
        netConfig.numberOfOutputNeurons = 1;
        netConfig.numberOfHiddenLayers = 1;
        netConfig.numberOfNeuronsPerHiddenLayer = 2;
        netConfig.bias = 1;
        NeuralNet net = new NeuralNet(netConfig);
        
        GeneticAlgorithmConfiguration algConfig = new GeneticAlgorithmConfiguration();
        algConfig.chromosomeLengthOfGenome = netConfig.getRequiredNumberOfWeights();
        algConfig.crossoverRate = 0.7;
        algConfig.mutationRate = 0.1;
        algConfig.neuralNet = net;
        algConfig.populationSize = 200;

        DataReader dataReader = new DataReader("./SmileyData.txt");
        List<Test> testCases = dataReader.getTestData();

        RandomProvider randomProvider = new RealRandomProvider();

        //genetic algorithm with crossoverRate 0.7, mutationRate 0.1, population count 10 and chromosome count per genome
        GeneticAlgorithm genAlg = new GeneticAlgorithm(algConfig, randomProvider);
        
        genAlg.testPrintAndScorePopulation(testCases);
        
        while(!genAlg.isDone()){
            genAlg.makeNextGeneration();
            genAlg.testPrintAndScorePopulation(testCases);
        }
        System.out.println(genAlg.winner);
        genAlg.writer.close();
    }

}
