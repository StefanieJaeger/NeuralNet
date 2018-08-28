package com.github.stefaniejaeger.neuralnet.algorithm;

import com.github.stefaniejaeger.neuralnet.algorithm.genetic.RandomProvider;
import com.github.stefaniejaeger.neuralnet.algorithm.genetic.RouletteWheel;
import org.junit.Test;

import static org.junit.Assert.*;

public class RouletteWheelTest {

    @Test
    public void getRandomElementWorksWhenEmpty() {
        RandomProvider randomProvider = new RandomProvider() {
            @Override
            public double getDoubleInRange(double start, double end) {
                return 0;
            }

            @Override
            public int getIntegerInRange(int start, int end) {
                return 0;
            }
        };
        RouletteWheel<Integer> rouletteWheel = new RouletteWheel<>(randomProvider);

        Integer expectedResult = null;
        Integer result = rouletteWheel.getRandomElement();

        assertEquals(expectedResult, result);
    }

    @Test
    public void getRandomElementReturnsExpectedElement() {
        RandomProvider randomProvider = new RandomProvider() {
            @Override
            public double getDoubleInRange(double start, double end) {
                return 0;
            }

            @Override
            public int getIntegerInRange(int start, int end) {
                return 1;
            }
        };
        RouletteWheel<Integer> rouletteWheel = new RouletteWheel<>(randomProvider);

        rouletteWheel.add(0);
        rouletteWheel.add(1);
        rouletteWheel.add(2);

        Integer expectedResult = 1;
        Integer result = rouletteWheel.getRandomElement();

        assertEquals(expectedResult, result);
    }

}