package com.github.stefaniejaeger.neuralnet.algorithm;

/**
 *
 * @author Stefanie
 */
public class Genome {
    private double fitness;
    private Chromosome chromosome;

    public Genome(Chromosome chromosome){
        this.chromosome = chromosome;
    }
    
    public double getFitness(){
        return fitness;
    }
    
    public void setFitness(double score){
        fitness = score;
    }

    public void increaseFitness(){
        fitness++;
    }

    public Chromosome getChromosome(){
        return chromosome;
    }
    
    public void setChromosome(Chromosome chromosome){
        this.chromosome = chromosome;
    }

    @Override
    public String toString(){
        return "has fitness " + fitness + " and chromosome " + chromosome.toString();
    }

}
