package com.github.stefaniejaeger.neuralnet.network.neuron;

import com.github.stefaniejaeger.neuralnet.network.Connection;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class BiasNeuronTest {

    @Test
    public void calculateValue() {
        BiasNeuron biasNeuron = new BiasNeuron(0.0);

        try {
            biasNeuron.calculateValue();
        } catch (UnsupportedOperationException ex) {
            return;
        }

        fail();
    }

    @Test
    public void reset() {
        BiasNeuron biasNeuron = new BiasNeuron(0.0);

        try {
            biasNeuron.reset();
        } catch (UnsupportedOperationException ex) {
            return;
        }

        fail();
    }

    @Test
    public void addConnection() {
        BiasNeuron biasNeuron = new BiasNeuron(0.0);

        try {
            biasNeuron.addConnection(Mockito.mock(Connection.class));
        } catch (UnsupportedOperationException ex) {
            return;
        }

        fail();
    }

}
