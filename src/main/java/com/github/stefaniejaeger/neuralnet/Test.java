package com.github.stefaniejaeger.neuralnet;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    
    public List<Integer> getExpectedOutputs(){
        return expectedOutputs;
    }
    
    @Override
    public String toString(){
        return "Inputs " 
                + inputs.stream().map(Object::toString).collect(Collectors.joining(",")) 
                + " should lead to ouputs " 
                + expectedOutputs.stream().map(Object::toString).collect(Collectors.joining(","));
    }
}
