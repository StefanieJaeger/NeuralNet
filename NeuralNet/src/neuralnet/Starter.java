/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnet;

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
        // TODO code application logic here
        NeuralNetConfiguration config = new NeuralNetConfiguration();
        config.numberOfInputNeurons = 2;
        config.numberOfOutputNeurons = 1;
        config.numberOfHiddenLayers = 1;
        config.numberOfNeuronsPerHiddenLayer = 3;
        config.bias = 1;
        NeuralNet net = new NeuralNet(config);
        
        List<Double> inputs = Arrays.asList(1.0,0.0);
        List<Double> expOutputs = Arrays.asList(1.0);
        
        GeneticAlgorithm genAlg = new GeneticAlgorithm(net, 0.7, 0.1, 10, 9);
                
        genAlg.testPopulation(inputs, expOutputs);
        
        
        
    }
    
}
