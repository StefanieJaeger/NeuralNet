/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnet;

import java.util.List;

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
