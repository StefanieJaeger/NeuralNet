package com.github.stefaniejaeger.neuralnet.network.neuron;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class InputNeuronTest {

    @Test
    public void calculateValue() {
        InputNeuron inputNeuron = new InputNeuron();

        try {
            inputNeuron.calculateValue();
        } catch (UnsupportedOperationException ex) {
            return;
        }

        fail();
    }

    @Test
    public void addConnection() {
        InputNeuron inputNeuron = new InputNeuron();

        try {
            inputNeuron.calculateValue();
        } catch (UnsupportedOperationException ex) {
            return;
        }

        fail();
    }

    @Test
    public void reset() {
        HiddenNeuron hiddenNeuron = Mockito.spy(new HiddenNeuron());
        hiddenNeuron.value = 1.0;

        hiddenNeuron.reset();

        assertNull(hiddenNeuron.value);
    }

}