package com.github.stefaniejaeger.neuralnet.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chromosome {

    private List<Double> dna;

    public Chromosome copy() {
        return new Chromosome(dna);
    }

    public Chromosome(List<Double> dna) {
        this.dna = dna;
    }

    public List<Double> getDNA() {
        return dna;
    }

    public void crossover(int index, Chromosome otherChromosome) {
        List<Double> startOfDNA = dna.subList(0, index);
        List<Double> endOfDNA = dna.subList(index, dna.size());

        List<Double> startOfOtherDNA = otherChromosome.dna.subList(0, index);
        List<Double> endOfOtherDNA = otherChromosome.dna.subList(index, otherChromosome.getDNA().size());

        this.dna = Stream.concat(startOfDNA.stream(), endOfOtherDNA.stream()).collect(Collectors.toList());
        otherChromosome.dna = Stream.concat(startOfOtherDNA.stream(), endOfDNA.stream()).collect(Collectors.toList());
    }

    public void mutate(double mutationRate) {
        Random ran = new Random();

        for (int i = 0; i < dna.size(); i++)
            if (mutationRate >= ran.nextDouble())
                dna.set(i, ran.nextDouble() * 2 - 1);
    }

    @Override
    public String toString() {
        String sDna = "";
        for(Double molecule : dna)
            sDna += ", " + molecule.toString();
        return sDna.replaceFirst(", ", "");
    }
}
