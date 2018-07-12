package com.github.stefaniejaeger.neuralnet.network;

import com.github.stefaniejaeger.neuralnet.network.layer.HiddenLayer;
import com.github.stefaniejaeger.neuralnet.network.layer.InputLayer;
import com.github.stefaniejaeger.neuralnet.network.layer.Layer;
import com.github.stefaniejaeger.neuralnet.network.layer.OutputLayer;
import com.github.stefaniejaeger.neuralnet.network.neuron.HiddenNeuron;
import com.github.stefaniejaeger.neuralnet.network.neuron.Neuron;
import com.github.stefaniejaeger.neuralnet.network.neuron.OutputNeuron;

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
        
        //create input layer
        layers.add(new InputLayer(configuration.numberOfInputNeurons));
        
        //create all hidden layers
        for(int i = 0; i < configuration.numberOfHiddenLayers; i++){
            layers.add(new HiddenLayer(configuration.numberOfNeuronsPerHiddenLayer, configuration.bias));
        }
        
        //create output layer
        layers.add(new OutputLayer(configuration.numberOfOutputNeurons));
    }
    
    public void setWeights(List<Double> w){
        weights = w;
        connect();
    }
    
    private void connect(){
        List<Layer> layers = this.layers;
        List<Connection> connections = new ArrayList<>();
        int weightCounter = 0;        
        //go through all hidden layers
                
        for(int i = 1; i < layers.size()-1; i++){
            //add a connection for each neuron in the previous layer to each neuron in the current one
            for(int k = 0; k< layers.get(i).getNeurons().size(); k++){   
                
                for(int j =0; j < layers.get(i-1).getNeurons().size(); j++){
                    connections.add(new Connection(weights.get(weightCounter),layers.get(i-1).getNeurons().get(j)));
                    weightCounter++;
                }
            
                layers.get(i).getNeurons().get(k).addConnections(connections);
                //hiddenNeuron.getConnections().forEach(f-> System.out.println(f.toString()));
                connections = new ArrayList<>();
            }
        }
        //output layer
        int index = layers.size()-1;
        for(int k = 0; k < layers.get(index).getNeurons().size(); k++){
            //add a connection for each neuron in the previous layer to each neuron in the current one
            for(int j =0; j < layers.get(index-1).getNeurons().size(); j++){
                connections.add(new Connection(weights.get(weightCounter), layers.get(index-1).getNeurons().get(j)));
                weightCounter++;
            }
             
            layers.get(index).getNeurons().get(k).addConnections(connections);       
            connections = new ArrayList<>();
        }
        this.layers = layers;        
    }
    
    @Override
    public String toString(){
        String text = "NEURAL NET STRUCTURE: " + '\n' + '\t';
        for(Layer layer : layers)
            text += layer.toString() + '\n' + '\t';
        return text;
    }
    
    public void printNet(){
        String input = "";
        List<String> hidden = new ArrayList<>();
        String output = "";
        List<String> cons = new ArrayList<>();
        
        String neuron = " (o ";
        String connection = "(| ";
        
        for(Neuron n : layers.get(0).getNeurons()){
            input += neuron + n.getValue() + ") ";
        }
                     
        for(int j = 1; j < layers.size()-1; j++){
            String s1 = "";
            String s2 ="";
            for(int i = 0; i <layers.get(j).getNeurons().size(); i++){
                HiddenNeuron h = (HiddenNeuron)layers.get(j).getNeurons().get(i);
                for(Connection c : h.getConnections())
                    s1 += connection + c.getWeight() + ") ";
                s2 += neuron + h.getValue() + ") ";
            }
            cons.add(s1);
            hidden.add(s2);
        }
        
        for(int i = 0; i < layers.get(layers.size()-1).getNeurons().size(); i++){
            OutputNeuron o = (OutputNeuron)layers.get(layers.size()-1).getNeurons().get(i);
            String s1 = "";
            for(Connection c : o.getConnections())
                s1 += connection + c.getWeight() + ") ";
            cons.add(s1);  
            output += neuron + o.getValue() + ") ";
        }
        
        System.out.println(input);
        for(int i = 0; i < hidden.size(); i++){
            System.out.println(cons.get(i));
            System.out.println(hidden.get(i));
        }        
        System.out.println(cons.get(cons.size()-1));
        System.out.println(output);
    }
    
    public void calculateOutputs(List<Double> inputs){
        outputs.clear();
        InputLayer input = (InputLayer)layers.get(0);
        input.setInputs(inputs);
        OutputLayer output = (OutputLayer)layers.get(layers.size()-1);
        output.neurons.get(0).calculateValue();
        outputs = output.getOutputs();
    }
}
