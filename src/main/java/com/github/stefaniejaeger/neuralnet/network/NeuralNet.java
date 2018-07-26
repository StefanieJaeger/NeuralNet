package com.github.stefaniejaeger.neuralnet.network;

import com.github.stefaniejaeger.neuralnet.network.layer.HiddenLayer;
import com.github.stefaniejaeger.neuralnet.network.layer.InputLayer;
import com.github.stefaniejaeger.neuralnet.network.layer.OutputLayer;
import com.github.stefaniejaeger.neuralnet.network.neuron.BiasNeuron;
import com.github.stefaniejaeger.neuralnet.network.neuron.HiddenNeuron;
import com.github.stefaniejaeger.neuralnet.network.neuron.Neuron;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Stefanie
 */
public class NeuralNet {
    private InputLayer inputLayer;
    private List<HiddenLayer> hiddenLayers;
    private OutputLayer outputLayer;

    private List<Connection> connections;

    private NeuralNetConfiguration configuration;

    public NeuralNet(NeuralNetConfiguration configuration) {
        this.configuration = configuration;

        inputLayer = new InputLayer(createBiasNeuron(), configuration.numberOfInputNeurons);
        outputLayer = new OutputLayer(configuration.numberOfOutputNeurons);
        hiddenLayers = new ArrayList<>();

        for (int i = 0; i < configuration.numberOfHiddenLayers; i++) {
            hiddenLayers.add(new HiddenLayer(createBiasNeuron(), configuration.numberOfNeuronsPerHiddenLayer));
        }

        connectLayers();
    }

    private BiasNeuron createBiasNeuron() {
        return new BiasNeuron(configuration.bias);
    }

    private void connectInputLayerWithHiddenLayer(InputLayer inputLayer, HiddenLayer hiddenLayer) {
        for (HiddenNeuron hiddenNeuron : hiddenLayer.getHiddenNeurons()) {
            for (Neuron inputNeuron : inputLayer.getNeurons()) {
                hiddenNeuron.addConnection(createConnection(inputNeuron));
            }
        }
    }

    private void connectHiddenLayerWithHiddenLayer(HiddenLayer firstHiddenLayer, HiddenLayer secondHiddenLayer) {
        for (HiddenNeuron hiddenNeuronFromSecondLayer : secondHiddenLayer.getHiddenNeurons()) {
            for (HiddenNeuron hiddenNeuronFromFirstLayer : firstHiddenLayer.getHiddenNeurons()) {
                hiddenNeuronFromSecondLayer.addConnection(createConnection(hiddenNeuronFromFirstLayer));
            }
        }
    }

    private void connectHiddenLayerWithOutputLayer(HiddenLayer hiddenLayer, OutputLayer outputLayer) {
        for (Neuron outputNeuron : outputLayer.getNeurons()) {
            for (Neuron hiddenNeuron : hiddenLayer.getNeurons()) {
                outputNeuron.addConnection(createConnection(hiddenNeuron));
            }
        }
    }

    private void connectInputLayerWithOutputLayer(InputLayer inputLayer, OutputLayer outputLayer) {
        for (Neuron outputNeuron : outputLayer.getNeurons()) {
            for (Neuron inputNeuron : inputLayer.getNeurons()) {
                outputNeuron.addConnection(createConnection(inputNeuron));
            }
        }
    }

    private Connection createConnection(Neuron source) {
        Connection connection = new Connection(source);
        connections.add(connection);

        return connection;
    }

    private void connectLayers() {
        connections = new ArrayList<>();

        if (hiddenLayers.size() == 0) {
            connectInputLayerWithOutputLayer(inputLayer, outputLayer);
            return;
        }

        connectInputLayerWithHiddenLayer(inputLayer, hiddenLayers.get(0));

        for (int i = 0; i < hiddenLayers.size() - 1; i++) {
            connectHiddenLayerWithHiddenLayer(hiddenLayers.get(i), hiddenLayers.get(i + 1));
        }

        connectHiddenLayerWithOutputLayer(hiddenLayers.get(hiddenLayers.size() - 1), outputLayer);
    }

    public void setWeights(List<Double> weights) {
        assert weights.size() == connections.size();

        for (int i = 0; i < connections.size(); i++) {
            connections.get(i).setWeight(weights.get(i));
        }
    }

    public List<Integer> calculateOutputs(List<Double> inputs) {
        resetNetwork();

        assert inputs.size() == inputLayer.getInputNeurons().size();

        for (int i = 0; i < inputLayer.getInputNeurons().size(); i++) {
            inputLayer.getInputNeurons().get(i).setValue(inputs.get(i));
        }

        return outputLayer.getOutputNeurons().stream().map(Neuron::getValue).map(value -> value > 0 ? 1 : 0).collect(Collectors.toList());
    }

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
