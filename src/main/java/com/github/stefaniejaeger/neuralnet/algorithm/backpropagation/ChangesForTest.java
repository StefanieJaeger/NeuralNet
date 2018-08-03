/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.stefaniejaeger.neuralnet.algorithm.backpropagation;

import com.github.stefaniejaeger.neuralnet.Test;
import java.util.List;

/**
 *
 * @author jaeger
 */
public class ChangesForTest {
    List<Double> weightChanges;
    List<Double> biasChanges;
    Test test;
    
    public ChangesForTest(Test test) {
        
    }
    
    public void setWeightChanges(List<Double> changes) {
        weightChanges = changes;
    }
    
    public void setBiasChanges(List<Double> changes) {
        weightChanges = changes;
    }
        
    public Test getTest() {
        return test;
    }
    
    public List<Double> getWeightChanges() {
        return weightChanges;
    }
    
    public List<Double> getBiasChanges() {
        return biasChanges;
    }
}
