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
public class NeuralNet {
    private List<Layer> layers;
    private NeuralNetConfiguration configuration;
    
    public NeuralNet(NeuralNetConfiguration configuration, List<Double> weights){
        this.configuration = configuration;
        layers = new ArrayList<>();        
        
        layers.add(new InputLayer(configuration.numberOfInputNeurons));
        
        for(int i = 0; i < configuration.numberOfHiddenLayers; i++){
            layers.add(new HiddenLayer(configuration.numberOfNeuronsPerHiddenLayer, configuration.bias));
        }
        
        layers.add(new OutputLayer(configuration.numberOfOutputNeurons));
        
        connect();
    }
    
    private void connect(){
        for(int i = 1; i < layers.size()-2; i++){
            HiddenLayer layer = (HiddenLayer)layers.get(i);
            List<Connection> connections = new ArrayList<>();
            for(int j = 0; j < configuration.numberOfInputNeurons; j++){
                connections.add(new Connection(0,layers.get(0).getNeurons().get(j)));
            }
            layer.neurons.forEach(k->{
                HiddenNeuron neuron = (HiddenNeuron)k;
                neuron.addConnections(connections);
            });
            if(i == layers.size()-3){
                OutputLayer layer2 = (OutputLayer)layers.get(i+2);
                for(int j = 0; j < configuration.numberOfNeuronsPerHiddenLayer; j++){
                    connections.add(new Connection(0, layers.get(i).getNeurons().get(j)));
                }
                layer2.neurons.forEach(k->{
                    OutputNeuron neuron = (OutputNeuron)k;
                    neuron.addConnections(connections);
                });
            }
        }
    }
    
    public void calculateOutputs(List<Double> inputs){
        
    }
}
