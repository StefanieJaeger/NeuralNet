package com.github.stefaniejaeger.neuralnet;

import java.util.List;

/**
 *
 * @author Stefanie
 */
public class Genome {
    private double fitness;
    private List<Double> chromosones;
    private String name;
    
    public Genome(List<Double> chromosones, String name){
        this.chromosones = chromosones;
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    //says how close the value calculated with these weights got to the expected result
    public double getFitness(){
        return fitness;
    }
    
    public void setFitness(double score){
        fitness = score;
    }
    
    public List<Double> getDNA(){
        return this.chromosones;
    }
    
    public void setDNA(List<Double> dna){
        chromosones = dna;
    }
}
