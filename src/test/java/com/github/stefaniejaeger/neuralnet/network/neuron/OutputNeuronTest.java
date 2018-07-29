package com.github.stefaniejaeger.neuralnet.network.neuron;

import com.github.stefaniejaeger.neuralnet.network.Connection;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class OutputNeuronTest {

    @Ignore
    @Test
    public void calculateValue() {
        // TODO
    }

    @Test
    public void addConnection() {
        OutputNeuron outputNeuron = new OutputNeuron();
        Connection connectionMock = Mockito.mock(Connection.class);

        outputNeuron.addConnection(connectionMock);

        Integer expectedResult = 1;
        assertEquals(expectedResult, outputNeuron.getNumberOfIncomingConnections());
    }

    @Test
    public void resetNullifiesValue() {
        OutputNeuron outputNeuron = new OutputNeuron();
        outputNeuron.value = 1.0;

        outputNeuron.reset();

        assertNull(outputNeuron.value);
    }

    @Test
    public void resetKeepsConnections() {
        OutputNeuron outputNeuron = new OutputNeuron();
        Connection connectionMock = Mockito.mock(Connection.class);

        outputNeuron.addConnection(connectionMock);

        outputNeuron.reset();

        Integer expectedResult = 1;
        assertEquals(expectedResult, outputNeuron.getNumberOfIncomingConnections());
    }

}