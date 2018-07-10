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
        
        //connect();
    }
    
    public void setWeights(List<Double> w){
        weights = w;
        connect();
    }
    
    private void connect(){
        //hidden layers and output layers
        for(int i = 1; i < layers.size(); i++){
            List<Connection> connections = new ArrayList<>();
            if(i != layers.size()-1){
            HiddenLayer layer = (HiddenLayer)layers.get(i);
            
            for(int j =0; j < layers.get(i-1).getNeurons().size(); j++){
                connections.add(new Connection(weights.get(0),layers.get(i-1).getNeurons().get(j)));
                weights.remove(0);
            }
            
            /*for(int j = 0; j < configuration.numberOfInputNeurons; j++){
                connections.add(new Connection(weights.get(0),layers.get(0).getNeurons().get(j)));
                weights.remove(0);
            }*/
            layer.getNeurons().forEach(k->{
                HiddenNeuron neuron = (HiddenNeuron)k;
                neuron.addConnections(connections);
            });
            }
            //output layer
            else{
                OutputLayer layer2 = (OutputLayer)layers.get(i);
                for(int j =0; j < layers.get(i-1).getNeurons().size(); j++){
                    connections.add(new Connection(weights.get(0), layers.get(i-1).getNeurons().get(j)));
                    weights.remove(0);
                }
                
                /*for(int j = 0; j < configuration.numberOfNeuronsPerHiddenLayer; j++){

                }*/
                layer2.getNeurons().forEach(k->{
                    OutputNeuron neuron = (OutputNeuron)k;
                    neuron.addConnections(connections);
                });
            }
        }
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
        OutputLayer out = (OutputLayer)layers.get(layers.size()-1);
        outputs = out.getOutputs();
        outputs.forEach(e->Math.round(e));
    }
}
