package com.github.stefaniejaeger.neuralnet.network;

import com.github.stefaniejaeger.neuralnet.network.layer.HiddenLayer;
import com.github.stefaniejaeger.neuralnet.network.layer.InputLayer;
import com.github.stefaniejaeger.neuralnet.network.layer.Layer;
import com.github.stefaniejaeger.neuralnet.network.layer.OutputLayer;
import com.github.stefaniejaeger.neuralnet.network.neuron.BiasNeuron;
import com.github.stefaniejaeger.neuralnet.network.neuron.Neuron;
import com.github.stefaniejaeger.neuralnet.network.neuron.OutputNeuron;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Stefanie
 */
public class NeuralNet implements Cloneable {

    private InputLayer inputLayer;
    private List<HiddenLayer> hiddenLayers;
    private OutputLayer outputLayer;

    private List<Connection> connections;

    private NeuralNetConfiguration configuration;

    public NeuralNet(NeuralNetConfiguration configuration) {
        this.configuration = configuration;

        inputLayer = new InputLayer(createBiasNeuron(), configuration.numberOfInputNeurons);
        hiddenLayers = new ArrayList<>();
        outputLayer = new OutputLayer(configuration.numberOfOutputNeurons);

        for (int i = 0; i < configuration.numberOfHiddenLayers; i++) {
            hiddenLayers.add(new HiddenLayer(createBiasNeuron(), configuration.numberOfNeuronsPerHiddenLayer));
        }

        connectLayers();
    }

    /**
     * Creates a new bias neuron with the bias value from the configuration
     * @return 
     */
    private BiasNeuron createBiasNeuron() {
        return new BiasNeuron(configuration.bias);
    }

    /**
     * Connects all the neurons from the first list to all the neurons in the second list
     */
    private void connect(List<? extends Neuron> firstListOfNeurons, List<? extends Neuron> secondListOfNeurons) {
        for (Neuron secondListNeuron : secondListOfNeurons) {
            for (Neuron firstListNeuron : firstListOfNeurons) {
                Connection connection = new Connection(firstListNeuron);
                connections.add(connection);

                secondListNeuron.addConnection(connection);
            }
        }
    }

    /**
     * Connects all the neurons from each layer to all the neurons in the following layer
     */
    private void connectLayers() {
        connections = new ArrayList<>();

        if (hiddenLayers.isEmpty()) {
            connect(inputLayer.getNeurons(), outputLayer.getNeurons());
            return;
        }

        connect(inputLayer.getNeurons(), hiddenLayers.get(0).getHiddenNeurons());

        for (int i = 0; i < hiddenLayers.size() - 1; i++) {
            connect(hiddenLayers.get(i).getNeurons(), hiddenLayers.get(i + 1).getHiddenNeurons());
        }

        connect(hiddenLayers.get(hiddenLayers.size() - 1).getNeurons(), outputLayer.getNeurons());
    }

    /**
     * Sets the weights for all connections to the weights specified in the list
     * @param weights 
     */
    public void setWeights(List<Double> weights) {
        assert weights.size() == connections.size();

        for (int i = 0; i < connections.size(); i++) {
            connections.get(i).setWeight(weights.get(i));
        }
    }
    
    public InputLayer getInputLayer() {
        return inputLayer;
    }
    
    public List<HiddenLayer> getHiddenLayers() {
        return hiddenLayers;
    }
    
    public OutputLayer getOutputLayer() {
        return outputLayer;
    }
    
    public List<Connection> getConnectionsFromNeuron(Neuron neuron){
        return connections.stream().filter(c->c.getSource() == neuron).collect(Collectors.toList());
    }
    
    @Override
    public NeuralNet clone() throws CloneNotSupportedException {
        return (NeuralNet)super.clone();
    }
        
    /**
     * Let's the whole net calculate the output, then rounds to 0 or 1
     * @param inputs
     * @return calculated outputs
     */
    public List<Integer> calculateOutputs(List<Double> inputs) {
        resetNetwork();

        assert inputs.size() == inputLayer.getInputNeurons().size();

        for (int i = 0; i < inputLayer.getInputNeurons().size(); i++) {
            inputLayer.getInputNeurons().get(i).setValue(inputs.get(i));
        }

        List<Integer> outputs = new ArrayList<>();
        for (OutputNeuron outputNeuron : outputLayer.getOutputNeurons()) {
            outputs.add(outputNeuron.getValue() > 0 ? 1 : 0);
        }

        return outputs;
    }

    /**
     * Resets each layer in the net
     */
    public void resetNetwork() {
        inputLayer.getInputNeurons().forEach(Neuron::reset);

        for (HiddenLayer hiddenLayer : hiddenLayers) {
            hiddenLayer.getHiddenNeurons().forEach(Neuron::reset);
        }

        outputLayer.getOutputNeurons().forEach(Neuron::reset);
    }

    @Override
    public String toString() {
        String text = "Neural Network with " + (2 + hiddenLayers.size()) + " layers:" + "\n";
        text += "\t" + inputLayer.toString();

        for (HiddenLayer hiddenLayer : hiddenLayers) {
            text += "\t" + hiddenLayer.toString();
        }

        text += "\t" + outputLayer.toString();

        return text;
    }

}
