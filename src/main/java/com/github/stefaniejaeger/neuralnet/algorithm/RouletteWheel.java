package com.github.stefaniejaeger.neuralnet.algorithm;

import java.util.ArrayList;
import java.util.List;

class RouletteWheel<FieldType> {

    private RandomProvider randomProvider;
    private List<FieldType> fields;

    RouletteWheel(RandomProvider randomProvider) {
        this.randomProvider = randomProvider;
        this.fields = new ArrayList<>();
    }

    void add(FieldType element) {
        fields.add(element);
    }

    FieldType getRandomElement() {
        return fields.get(randomProvider.getIntegerInRange(0, fields.size()));
    }

}
