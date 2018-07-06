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
    public List<Double> outputs = new ArrayList<>();
    public List<Double> weights = new ArrayList<>();
    
    public NeuralNet(NeuralNetConfiguration configuration){
        this.configuration = configuration;
        layers = new ArrayList<>();        
        
        layers.add(new InputLayer(configuration.numberOfInputNeurons));
        
        for(int i = 0; i < configuration.numberOfHiddenLayers; i++){
            layers.add(new HiddenLayer(configuration.numberOfNeuronsPerHiddenLayer, configuration.bias));
        }
        
        layers.add(new OutputLayer(configuration.numberOfOutputNeurons));
        
        connect();
    }
    
    public void setWeights(List<Double> w){
        weights = w;
        connect();
    }
    
    private void connect(){
        //hidden layers and output layers
        for(int i = 1; i < layers.size()-2; i++){
            HiddenLayer layer = (HiddenLayer)layers.get(i);
            List<Connection> connections = new ArrayList<>();
            for(int j = 0; j < configuration.numberOfInputNeurons; j++){
                connections.add(new Connection(weights.get(0),layers.get(0).getNeurons().get(j)));
                weights.remove(0);
            }
            layer.neurons.forEach(k->{
                HiddenNeuron neuron = (HiddenNeuron)k;
                neuron.addConnections(connections);
            });
            //output layer
            if(i == layers.size()-3){
                OutputLayer layer2 = (OutputLayer)layers.get(i+2);
                for(int j = 0; j < configuration.numberOfNeuronsPerHiddenLayer; j++){
                    connections.add(new Connection(weights.get(0), layers.get(i).getNeurons().get(j)));
                    weights.remove(0);
                }
                layer2.neurons.forEach(k->{
                    OutputNeuron neuron = (OutputNeuron)k;
                    neuron.addConnections(connections);
                });
            }
        }
    }
    
    public void calculateOutputs(List<Double> inputs){
        outputs.clear();
        InputLayer input = (InputLayer)layers.get(0);
        input.setInputs(inputs);
        OutputLayer out = (OutputLayer)layers.get(layers.size()-1);
        outputs = out.getOutputs();
    }
}
