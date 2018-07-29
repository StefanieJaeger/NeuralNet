package com.github.stefaniejaeger.neuralnet.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class RouletteWheel<FieldType> {

    private RandomProvider randomProvider;
    private List<FieldType> fields;

    private final Logger LOGGER = LoggerFactory.getLogger(RouletteWheel.class);

    public RouletteWheel(RandomProvider randomProvider) {
        this.randomProvider = randomProvider;
        this.fields = new ArrayList<>();
    }

    public void add(FieldType element) {
        fields.add(element);
    }

    public FieldType getRandomElement() {
        if (fields.size() == 0) {
            LOGGER.warn("Roulette wheel is empty, returning null");
            return null;
        }

        int index = randomProvider.getIntegerInRange(0, fields.size());
        return fields.get(index);
    }

}
