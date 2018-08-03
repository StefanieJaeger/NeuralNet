package com.github.stefaniejaeger.neuralnet.network.layer;

import com.github.stefaniejaeger.neuralnet.network.neuron.BiasNeuron;
import com.github.stefaniejaeger.neuralnet.network.neuron.Neuron;

import java.util.List;

/**
 *
 * @author Stefanie
 */
public interface Layer {

    List<Neuron> getNeurons();

    BiasNeuron getBiasNeuron();
}