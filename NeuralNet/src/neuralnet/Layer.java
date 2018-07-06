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
public abstract class Layer {
    public List<Neuron> neurons = new ArrayList<>();
    
    public List<Neuron> getNeurons(){
        return this.neurons;
    }
}
