package com.github.stefaniejaeger.neuralnet.algorithm.genetic;

import java.util.concurrent.ThreadLocalRandom;

public class RealRandomProvider implements RandomProvider {

    @Override
    public double getDoubleInRange(double start, double end) {
        return ThreadLocalRandom.current().nextDouble(start, end);
    }

    @Override
    public int getIntegerInRange(int start, int end) {
        return ThreadLocalRandom.current().nextInt(start, end);
    }

}
