package com.github.stefaniejaeger.neuralnet;

import com.github.stefaniejaeger.neuralnet.algorithm.backpropagation.Backpropagation;
import com.github.stefaniejaeger.neuralnet.network.NeuralNet;
import com.github.stefaniejaeger.neuralnet.network.NeuralNetConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StarterBackpropagationSmiley {
    private static NeuralNet neuralNet;
    private static List<Test> testCases = new ArrayList<>();
    private static double learningRate = 0.2;

    public static void main(String[] args) {
        NeuralNetConfiguration netConfig = new NeuralNetConfiguration();
        netConfig.numberOfInputNeurons = 64;
        netConfig.numberOfOutputNeurons = 1;
        netConfig.numberOfHiddenLayers = 1;
        netConfig.numberOfNeuronsPerHiddenLayer = 2;
        netConfig.bias = 1;
        neuralNet = new NeuralNet(netConfig);

        DataReader dataReader = new DataReader("./SmileyData.txt");
        testCases = dataReader.getTestData();

        Backpropagation back = new Backpropagation(neuralNet, learningRate);

        startTesting(back);
    }

    public static void startTesting(Backpropagation back) {

        back.propagate(testCases);
    }
}
