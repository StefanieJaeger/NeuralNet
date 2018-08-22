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
        
        DataReader dataReader = new DataReader("./SmileyData.txt");
        List<Test> testCases = new ArrayList<>();

        testCases.add(new Test(Arrays.asList(0.0, 0.0), Arrays.asList(1)));
        testCases.add(new Test(Arrays.asList(0.0, 1.0), Arrays.asList(0)));
        testCases.add(new Test(Arrays.asList(1.0, 0.0), Arrays.asList(0)));
        testCases.add(new Test(Arrays.asList(1.0, 1.0), Arrays.asList(1)));

        Backpropagation back = new Backpropagation(net);

        back.propagate(testCases);
        

    }

}
