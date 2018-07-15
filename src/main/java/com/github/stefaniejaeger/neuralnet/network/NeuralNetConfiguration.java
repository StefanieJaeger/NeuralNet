package com.github.stefaniejaeger.neuralnet.network;

/**
 *
 * @author Stefanie
 */
public class NeuralNetConfiguration {
    public int numberOfInputNeurons;
    public int numberOfNeuronsPerHiddenLayer;
    public int numberOfOutputNeurons;
    public int numberOfHiddenLayers;
    public double bias;

    public int getRequiredNumberOfWeights() {
        int requiredNumberOfWeights = 0;

        // Input layer -> Hidden layer
        requiredNumberOfWeights += (numberOfInputNeurons + 1) * numberOfNeuronsPerHiddenLayer;

        // Hidden layer -> Hidden layer
        requiredNumberOfWeights += (numberOfHiddenLayers - 1) * (numberOfNeuronsPerHiddenLayer + 1) * numberOfNeuronsPerHiddenLayer;

        // Hidden layer -> Output layer
        requiredNumberOfWeights += (numberOfNeuronsPerHiddenLayer + 1) * numberOfOutputNeurons;

        return requiredNumberOfWeights;
    }
}
