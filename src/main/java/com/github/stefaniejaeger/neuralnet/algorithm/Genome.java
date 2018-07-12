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
        this.chromosome = chromosome;
    }
    
    //says how close the value calculated with these weights got to the expected result
    public double getFitness(){
        return fitness;
    }
    
    public void setFitness(double score){
        fitness = score;
    }
    
    public List<Double> getDNA(){
        return chromosome.dna;
    }
    
    public void setDNA(List<Double> dna){
        chromosome.dna = dna;
    }
    
    public void addToDNA(List<Double> dna){
        chromosome.dna.addAll(dna);
    }
    
    @Override
    public String toString(){
        return "has fitness " + fitness + " and dna " + chromosome.toString();
    }
}
