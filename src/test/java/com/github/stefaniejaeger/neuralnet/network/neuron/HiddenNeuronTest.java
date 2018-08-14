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
        double weight1 = 0.81;
        double weight2 = 0.63;
        InputNeuron neuron1 = new InputNeuron();
        InputNeuron neuron2 = new InputNeuron();
        neuron1.setValue(1);
        neuron2.setValue(0);
        List<Connection> connections = new ArrayList<>();
        connections.add(new Connection(weight1, neuron1));
        connections.add(new Connection(weight2, neuron2));
        Helper helper = new Helper();

        double result = helper.calculateNeuronValue(connections);
        double e = (neuron1.getValue() * weight1 + neuron2.getValue() * weight2);
        double expectedResult = (e / (1 + Math.abs(e)) - 0.5) * 2;

        assertEquals(expectedResult, result);
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