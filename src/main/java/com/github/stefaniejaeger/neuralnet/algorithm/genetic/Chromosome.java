package com.github.stefaniejaeger.neuralnet.algorithm.genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chromosome {

    private List<Molecule> dna;

    /**
     * 
     * @param another The chromosome of another Genome, its DNA will be copied to this chromosome 
     */
    public Chromosome(Chromosome another) {
        this.dna = new ArrayList<>();

        for (Molecule molecule : another.dna) {
            this.dna.add(new Molecule(molecule));
        }
    }

    public Chromosome(List<Molecule> dna) {
        this.dna = dna;
    }

    /**
     * Sets own DNA to a combination of own DNA and DNA of otherChromosome and vice versa
     * @param index At what index to cross DNAs
     * @param otherChromosome Second chromosome to take DNA from and change its DNA
     */
    public void crossover(int index, Chromosome otherChromosome) {
        List<Molecule> startOfDNA = dna.subList(0, index);
        List<Molecule> endOfDNA = dna.subList(index, dna.size());

        List<Molecule> startOfOtherDNA = otherChromosome.dna.subList(0, index);
        List<Molecule> endOfOtherDNA = otherChromosome.dna.subList(index, otherChromosome.getDNA().size());

        this.dna = Stream.concat(startOfDNA.stream(), endOfOtherDNA.stream()).collect(Collectors.toList());
        otherChromosome.dna = Stream.concat(startOfOtherDNA.stream(), endOfDNA.stream()).collect(Collectors.toList());
    }
    
    /**
     * Returns list of Molecules, the DNA
     * @return 
     */
    public List<Molecule> getDNA() {
        return dna;
    }

    @Override
    public String toString() {
        return dna.stream().map(Object::toString).collect(Collectors.joining(","));
    }

}
