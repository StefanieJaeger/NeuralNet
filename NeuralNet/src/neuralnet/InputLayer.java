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
public class InputLayer extends Layer {
    public InputLayer(int numberOfNeurons){
        for(int i = 0; i < numberOfNeurons; i++){
            neurons.add(new InputNeuron());
        }
    }  
    
    public void setInputs(List<Double> inputs){
        if(inputs.size() != neurons.size())
            return;
        
        for(int i = 0; i < inputs.size(); i++){
            InputNeuron neuron = (InputNeuron)neurons.get(i);
            neuron.setValue(inputs.get(i));
        }
    }
}
