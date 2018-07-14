package com.github.stefaniejaeger.neuralnet.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RouletteWheel<FieldType> {

    private List<FieldType> fields;

    public RouletteWheel() {
        fields = new ArrayList<>();
    }

    public void add(FieldType element) {
        fields.add(element);
    }

    public FieldType getRandomElement() {
        Random ran = new Random();
        return fields.get(ran.nextInt(fields.size()));
    }

}
