/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
