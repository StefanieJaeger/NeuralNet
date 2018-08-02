package com.github.stefaniejaeger.neuralnet.algorithm.genetic;

public interface RandomProvider {

    public double getDoubleInRange(double start, double end);

    public int getIntegerInRange(int start, int end);

}
