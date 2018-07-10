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
    private int generationCount = 1;
    private double crossoverRate;
    private double mutationRate;
    private NeuralNet network;
    private List<Genome> rouletteWheel;
    
    public GenomeAlgorithm(NeuralNet network, double crossoverRate, double mutationRate, int populationSize, int chromoLenght){
        this.network = network;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.population = new ArrayList<>();
        makeGeneration(populationSize, chromoLenght);
    }
    
    private void makeGeneration(int size, int chromoLength){
        Random ran = new Random();
        for(int i = 0; i < size; i++){
            List<Double> dna = new ArrayList<>();
            for(int j = 0; j < chromoLength; j++){
                dna.add(ran.nextDouble());
            }
            Genome gen = new Genome(dna, "generation " + generationCount + ", member " + i);
            population.add(gen);
        }
    }
    
    public void makeNextGeneration(){
    newPopulation = new ArrayList<>();
    populateRouletteWheel();
    
    while(newPopulation.size() < population.size()){
        List<Genome> parents = getMomAndDad();
        makeBabies(parents.get(0), parents.get(1));
    }
    generationCount++;
    population = newPopulation;
    
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
        if(crossoverRate >= ran.nextDouble()){
        int index = ran.nextInt(rouletteWheel.size());
        List<Double> dna1 = mom.getDNA().subList(0, index-1);
        List<Double> dna2 = dad.getDNA().subList(index, dad.getDNA().size());
        dna1.addAll(dad.getDNA().subList(0, index-1));
        dna2.addAll(mom.getDNA().subList(index, mom.getDNA().size()));
        Genome kid1 = new Genome(dna1, "generation " + (generationCount+1) + ", member " + newPopulation.size()+1);
        Genome kid2 = new Genome(dna2, "generation " + (generationCount+1) + ", member " + newPopulation.size()+2);        
        kid1 = mutate(kid1);
        kid2 = mutate(kid2);
        
        newPopulation.add(kid1);
        newPopulation.add(kid2);
        } else {
            newPopulation.add(mom);
            newPopulation.add(dad);
        }
    }
    
    private Genome mutate(Genome gen){
        Random ran = new Random();
        if(mutationRate >= ran.nextDouble()){
            //mutate
        }
        return gen;
    }
    
    public void testPopulation(List<Double> inputs, List<Double> expOutputs){
        for(Genome gen : population){
            network.setWeights(gen.getDNA());
            network.calculateOutputs(inputs);
            List<Double> outputs = network.outputs;
            scoreGenome(gen, expOutputs, outputs);
        network.printNet();
        }
    }
    
    private void scoreGenome(Genome gen, List<Double> expOutputs, List<Double> actOutputs){
        if(expOutputs.equals(actOutputs)){
            gen.setFitness(gen.getFitness()+1);
        }
        System.out.println("Genome " + gen.getName() + ", with dna " + gen.getDNA().toString() + " has fitness " + gen.getFitness());
    }
}
