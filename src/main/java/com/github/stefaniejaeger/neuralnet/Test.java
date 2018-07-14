package com.github.stefaniejaeger.neuralnet;

import java.util.Arrays;
import java.util.List;

public class Test {
    List<Double> inputs;
    List<Integer> expectedOutputs;

    public Test(List<Double> inputs, List<Integer> expectedOutputs) {
        this.inputs = inputs;
        this.expectedOutputs = expectedOutputs;
    }

    public boolean isOutputCorrect(List<Integer> calculatedOutputs) {
        return Arrays.equals(expectedOutputs.toArray(), calculatedOutputs.toArray());
    }

    public List<Double> getInputs(){
        return this.inputs;
    }
}
