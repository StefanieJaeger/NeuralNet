package com.github.stefaniejaeger.neuralnet.algorithm;

import java.util.List;

/**
 *
 * @author Stefanie
 */
public class Genome {
    private double fitness;
    private List<Double> chromosomes;
    
    public Genome(List<Double> chromosomes){
        this.chromosomes = chromosomes;
    }
    
    //says how close the value calculated with these weights got to the expected result
    public double getFitness(){
        return fitness;
    }
    
    public void setFitness(double score){
        fitness = score;
    }
    
    public List<Double> getDNA(){
        return this.chromosomes;
    }
    
    public void setDNA(List<Double> dna){
        chromosomes = dna;
    }
}
