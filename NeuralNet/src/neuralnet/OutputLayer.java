/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnet;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stefanie
 */
public class OutputLayer extends Layer {
    public OutputLayer(int numberOfNeurons){
        for(int i = 0; i< numberOfNeurons; i++){
            neurons.add(new OutputNeuron());
        }
    }
    
    public List<Double> getOutputs(){
        List<Double> outputs = new ArrayList<>();
        for(Neuron neuron : neurons)
        outputs.add(neuron.getValue());
        return outputs;
    }
}
