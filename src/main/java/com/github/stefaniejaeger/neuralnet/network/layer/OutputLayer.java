package com.github.stefaniejaeger.neuralnet.network.layer;

import com.github.stefaniejaeger.neuralnet.network.neuron.BiasNeuron;
import com.github.stefaniejaeger.neuralnet.network.neuron.Neuron;
import com.github.stefaniejaeger.neuralnet.network.neuron.OutputNeuron;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stefanie
 */
public class OutputLayer implements Layer {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    private List<OutputNeuron> outputNeurons;

    public OutputLayer(int numberOfNeurons) {
        outputNeurons = new ArrayList<>();

        for(int i = 0; i< numberOfNeurons; i++){
            outputNeurons.add(new OutputNeuron());
        }
    }

    @Override
    public List<Neuron> getNeurons() {
        return new ArrayList<>(outputNeurons);
    }

    public List<OutputNeuron> getOutputNeurons() {
        return outputNeurons;
    }

    @Override
    public BiasNeuron getBiasNeuron() {     
        LOGGER.error("Calling getBiasNeuron on an output neuron");
        throw new UnsupportedOperationException(); 
    }

}
