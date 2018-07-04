/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Stefanie
 */
public class GenomeAlgorithm {
    private List<Genome> population;
    private List<Genome> newPopulation;
    private int generationCount;
    private double crossoverRate;
    private double mutationRate;
    private NeuralNet network;
    private List<Genome> rouletteWheel;
    
    public GenomeAlgorithm(NeuralNet network, double crossoverRate, double mutationRate){
        this.network = network;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
    }
    
    private void populateRouletteWheel(){
    rouletteWheel = new ArrayList<>();
        for(Genome pop : population){
        for(int i = 0; i < pop.getFitness(); i++){
            rouletteWheel.add(pop);
        }
    }
    }
    
    private List<Genome> getMomAndDad(){
    Random ran = new Random();
    List<Genome> parents = new ArrayList<>();
    parents.add(rouletteWheel.get(ran.nextInt(rouletteWheel.size())));
    parents.add(rouletteWheel.get(ran.nextInt(rouletteWheel.size())));
    return parents;
    }
    
    private void makeBabies(Genome mom, Genome dad){
        Random ran = new Random();
        int index = ran.nextInt(rouletteWheel.size());
        Genome kid1 = mom.getDNA().subList(0, index-1);
        Genome kid1 = dad.getDNA().subList(index, dad.getDNA().size());
        Genome kid1 = dad.getDNA().subList(0, index-1);
        Genome kid1 = mom.getDNA().subList(index, mom.getDNA().size());
        
    }
    
    private void mutate(Genome gen){
    
    }
}
