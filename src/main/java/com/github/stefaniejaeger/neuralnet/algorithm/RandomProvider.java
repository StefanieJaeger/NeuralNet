package com.github.stefaniejaeger.neuralnet.algorithm;

public interface RandomProvider {

    public double getDoubleInRange(double start, double end);

    public int getIntegerInRange(int start, int end);

}
