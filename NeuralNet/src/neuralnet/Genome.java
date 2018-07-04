/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnet;

import java.util.List;

/**
 *
 * @author Stefanie
 */
public class Genome {
    private double fitness;
    private List<Double> chromosones;
    
    public Genome(){
    
    }
    
    public double getFitness(){
        return fitness;
    }
    
    public List<Double> getDNA(){
        return this.chromosones;
    }
}
