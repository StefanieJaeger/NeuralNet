package com.github.stefaniejaeger.neuralnet.algorithm;

import java.util.List;

/**
 *
 * @author Stefanie
 */
public class Genome {
    private double fitness;
    private Chromosome chromosome;

    public Genome(Chromosome chromosome){
        this.chromosome = new Chromosome(chromosome);
    }
    
    /**
     * Returns how many times the genome calculated the correct result
     * @return 
     */
    public double getFitness(){
        return fitness;
    }
    
    /**
     * Sets the number of correctly calculated results for this genome
     * @param score 
     */
    public void setFitness(double score){
        fitness = score;
    }

    /**
     * Increases the number of correctly calculated results for this genome by one
     */
    public void increaseFitness(){
        fitness++;
    }

    /**
     * Returns the chromosome, containing the DNA
     * @return 
     */
    public Chromosome getChromosome(){
        return chromosome;
    }
    
    /**
     * Sets the chromosome
     * @param chromosome 
     */
    public void setChromosome(Chromosome chromosome){
        this.chromosome = chromosome;
    }

    @Override
    public String toString(){
        return "has fitness " + fitness + " and chromosome " + chromosome.toString();
    }

}
