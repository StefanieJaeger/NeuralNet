package com.github.stefaniejaeger.neuralnet.algorithm;

import com.github.stefaniejaeger.neuralnet.network.NeuralNet;

/**
 *
 * @author jaeger
 */
public class GeneticAlgorithmConfiguration {
    public NeuralNet neuralNet;
    public double crossoverRate;
    public double mutationRate;
    public int populationSize;
    public int chromosomeLengthOfGenome;
}
