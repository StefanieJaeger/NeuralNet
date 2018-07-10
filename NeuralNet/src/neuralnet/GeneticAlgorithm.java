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
public class GeneticAlgorithm {
    private List<Genome> population;
    private List<Genome> newPopulation;
    private int generationCount = 1;
    private double crossoverRate;
    private double mutationRate;
    private NeuralNet network;
    private List<Genome> rouletteWheel;
    
    public GeneticAlgorithm(NeuralNet network, double crossoverRate, double mutationRate, int populationSize, int chromoLenght){
        this.network = network;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.population = new ArrayList<>();
        //create the first generation
        makeGeneration(populationSize, chromoLenght);
    }
    
    private void makeGeneration(int size, int chromoLength){
        Random ran = new Random();
        for(int i = 0; i < size; i++){
            List<Double> dna = new ArrayList<>();
            //populate the DNA with random doubles
            for(int j = 0; j < chromoLength; j++){
                dna.add(ran.nextDouble());
            }
            //create the genome with DNA and name
            Genome gen = new Genome(dna, "generation " + generationCount + ", member " + i);
            population.add(gen);
        }
    }
    
    public void makeNextGeneration(){
        //make new generation (cannot be the first one)
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
        //add population member to the list as many times as his fitness
        rouletteWheel = new ArrayList<>();
            for(Genome gen : population){
                for(int i = 0; i < gen.getFitness(); i++)
                    rouletteWheel.add(gen);
            }
    }
    
    private List<Genome> getMomAndDad(){
        //randomly chooses two genomes from the roulette wheel
        Random ran = new Random();
        List<Genome> parents = new ArrayList<>();
        parents.add(rouletteWheel.get(ran.nextInt(rouletteWheel.size())));
        parents.add(rouletteWheel.get(ran.nextInt(rouletteWheel.size())));
        return parents;
    }
    
    private void makeBabies(Genome mom, Genome dad){
        Random ran = new Random();
        //if crossoverRate gets matched, make two new genomes from mom and dad
        if(crossoverRate >= ran.nextDouble()){
            int index = ran.nextInt(rouletteWheel.size());
            //take random amount from mom's and dad's DNA for kids' DNA
            List<Double> dna1 = mom.getDNA().subList(0, index-1);
            List<Double> dna2 = dad.getDNA().subList(index, dad.getDNA().size());
            dna1.addAll(dad.getDNA().subList(0, index-1));
            dna2.addAll(mom.getDNA().subList(index, mom.getDNA().size()));
            //Create new genomes with DNA and name
            Genome kid1 = new Genome(dna1, "generation " + (generationCount+1) + ", member " + newPopulation.size()+1);
            Genome kid2 = new Genome(dna2, "generation " + (generationCount+1) + ", member " + newPopulation.size()+2);   
            //Try to mutate kids to get some diversity
            kid1 = mutate(kid1);
            kid2 = mutate(kid2);
            
            newPopulation.add(kid1);
            newPopulation.add(kid2);
        } else {
            //mom and dad get to live on if they don't fuck
            newPopulation.add(mom);
            newPopulation.add(dad);
        }
    }
    
    private Genome mutate(Genome gen){
        Random ran = new Random();
        for(Double chromosone : gen.getDNA())
            //change a chromosone if mutation rate was matched (low chance)
            if(mutationRate >= ran.nextDouble()){
                chromosone = 9 - chromosone;
            }
        return gen;
    }
    
    public void testPrintAndScorePopulation(List<Double> inputs, List<Double> expOutputs){
        for(Genome gen : population){
            testGenome(gen, inputs, expOutputs);
            List<Double> outputs = network.outputs;
            //set fitness of member
            scoreGenome(gen, expOutputs, outputs);
            //print out structure and values in neural net for this member
            network.printNet();
            //print test results
            System.out.println("Genome " + gen.getName() + ", with dna " + gen.getDNA().toString() + " has fitness " + gen.getFitness());
        }
    }
    
    private void testGenome(Genome gen, List<Double> inputs, List<Double> expOutputs){
        //set the network values for the member
        network.setWeights(gen.getDNA());
        //get outputs calculated with the members DNA as weights
        network.calculateOutputs(inputs);
        
    }
    
    private void scoreGenome(Genome gen, List<Double> expOutputs, List<Double> actOutputs){
        //if output guess correctly, increase fitness by 1
        if(expOutputs.equals(actOutputs))
            gen.setFitness(gen.getFitness()+1);
    }
}
