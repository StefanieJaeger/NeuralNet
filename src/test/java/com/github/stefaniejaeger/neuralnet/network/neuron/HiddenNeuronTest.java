package com.github.stefaniejaeger.neuralnet.network.neuron;

import com.github.stefaniejaeger.neuralnet.network.Connection;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class HiddenNeuronTest {

    @Ignore
    @Test
    public void calculateValue() {
        // TODO
    }

    @Test
    public void addConnection() {
        HiddenNeuron hiddenNeuron = new HiddenNeuron();
        Connection connectionMock = Mockito.mock(Connection.class);

        hiddenNeuron.addConnection(connectionMock);

        Integer expectedResult = 1;
        assertEquals(expectedResult, hiddenNeuron.getNumberOfIncomingConnections());
    }

    @Test
    public void resetNullifiesValue() {
        HiddenNeuron hiddenNeuron = new HiddenNeuron();
        hiddenNeuron.value = 1.0;

        hiddenNeuron.reset();

        assertNull(hiddenNeuron.value);
    }

    @Test
    public void resetKeepsConnections() {
        HiddenNeuron hiddenNeuron = new HiddenNeuron();
        Connection connectionMock = Mockito.mock(Connection.class);

        hiddenNeuron.addConnection(connectionMock);

        hiddenNeuron.reset();

        Integer expectedResult = 1;
        assertEquals(expectedResult, hiddenNeuron.getNumberOfIncomingConnections());
    }

}