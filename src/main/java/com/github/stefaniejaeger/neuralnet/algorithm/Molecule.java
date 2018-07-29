package com.github.stefaniejaeger.neuralnet.algorithm;

public class Molecule {

    private Double value;

    public Molecule(Double value) {
        this.value = value;
    }

    public Molecule(Molecule molecule) {
        this.value = molecule.value;
    }

    public Double getValue() {
        return value;
    }

    public void mutate(RandomProvider randomProvider) {
        value = randomProvider.getDoubleInRange(-1, 1);
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
