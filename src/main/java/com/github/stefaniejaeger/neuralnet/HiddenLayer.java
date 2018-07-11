package com.github.stefaniejaeger.neuralnet;

/**
 *
 * @author Stefanie
 */
public class HiddenLayer extends Layer {
    public HiddenLayer(int numberOfNeurons, double bias){
        for(int i = 0; i < numberOfNeurons; i++){
            neurons.add(new HiddenNeuron(bias));
        }
    }
}
