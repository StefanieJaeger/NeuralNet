package com.github.stefaniejaeger.neuralnet.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chromosome {

    private List<Molecule> dna;

    // TODO
    public Chromosome(Chromosome another) {
        this.dna = new ArrayList<>();

        for (Molecule molecule : another.dna) {
            this.dna.add(new Molecule(molecule));
        }
    }

    public Chromosome(List<Molecule> dna) {
        this.dna = dna;
    }

    public void crossover(int index, Chromosome otherChromosome) {
        List<Molecule> startOfDNA = dna.subList(0, index);
        List<Molecule> endOfDNA = dna.subList(index, dna.size());

        List<Molecule> startOfOtherDNA = otherChromosome.dna.subList(0, index);
        List<Molecule> endOfOtherDNA = otherChromosome.dna.subList(index, otherChromosome.getDNA().size());

        this.dna = Stream.concat(startOfDNA.stream(), endOfOtherDNA.stream()).collect(Collectors.toList());
        otherChromosome.dna = Stream.concat(startOfOtherDNA.stream(), endOfDNA.stream()).collect(Collectors.toList());
    }

    public List<Molecule> getDNA() {
        return dna;
    }

    @Override
    public String toString() {
        return dna.stream().map(Object::toString).collect(Collectors.joining(","));
    }

}
