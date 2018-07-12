package com.github.stefaniejaeger.neuralnet.algorithm;

import com.github.stefaniejaeger.neuralnet.network.NeuralNet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
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
    public List<Genome> winner;

    public GeneticAlgorithm(GeneticAlgorithmConfiguration geneticAlgorithmConfiguration) {
        this.network = geneticAlgorithmConfiguration.neuralNet;
        this.crossoverRate = geneticAlgorithmConfiguration.crossoverRate;
        this.mutationRate = geneticAlgorithmConfiguration.mutationRate;
        this.population = new ArrayList<>();
        this.winner = new ArrayList<>();
        //create the first generation
        makeGeneration(geneticAlgorithmConfiguration.populationSize, geneticAlgorithmConfiguration.chromosomeLengthOfGenome);
    }

    private void makeGeneration(int size, int chromosomeLength) {
        Random ran = new Random();
        for (int i = 0; i < size; i++) {
            Chromosome chromo = new Chromosome(new ArrayList<Double>());
            //populate the DNA with random doubles
            for (int j = 0; j < chromosomeLength; j++) {
                chromo.dna.add(ran.nextDouble());
            }
            
            //create the genome with DNA and name
            Genome gen = new Genome(chromo);
            population.add(gen);
        }
    }

    public void makeNextGeneration() {
        //make new generation (cannot be the first one)
        newPopulation = new ArrayList<>();
        populateRouletteWheel();

        while (newPopulation.size() < population.size()) {
            List<Genome> parents = getMomAndDad();
            while(parents.get(0).getDNA() == parents.get(1).getDNA()){
                parents = getMomAndDad();
                System.out.println("Mom and dad was the same person");
            }
            makeBabies(parents.get(0), parents.get(1));
        }

        generationCount++;
        population = newPopulation;
    }

    private void populateRouletteWheel() {
        //add population member to the list as many times as his fitness
        rouletteWheel = new ArrayList<>();
        for (Genome gen : population) {
            for (int i = 0; i < gen.getFitness(); i++){
                rouletteWheel.add(gen);
                //System.out.println(gen.toString() + " in roulette");
            }
        }
    }

    private List<Genome> getMomAndDad() {
        //randomly chooses two genomes from the roulette wheel
        Random ran = new Random();
        List<Genome> parents = new ArrayList<>();
        parents.add(rouletteWheel.get(ran.nextInt(rouletteWheel.size())));
        parents.add(rouletteWheel.get(ran.nextInt(rouletteWheel.size())));
        return parents;
    }

    private void makeBabies(Genome mom, Genome dad) {
        Random ran = new Random();
        if (crossoverRate < ran.nextDouble()) {
            //mom and dad get to live on if they don't fuck
            Chromosome momChromo = new Chromosome(mom.getDNA());
            Chromosome dadChromo = new Chromosome(dad.getDNA());
            
            Genome newMom = new Genome(momChromo);
            Genome newDad = new Genome(dadChromo);
            
            newPopulation.add(newMom);
            newPopulation.add(newDad);
            
            return;
        }

        //if crossoverRate gets matched, make two new genomes from mom and dad
        int length = mom.getDNA().size();
        int index = ran.nextInt(length);
        
        List<Double> dnaMom = new ArrayList<>(mom.getDNA().subList(0, index));
        dnaMom.addAll(dad.getDNA().subList(index, length));
        List<Double> dnaDad = new ArrayList<>(dad.getDNA().subList(0, index));
        dnaDad.addAll(mom.getDNA().subList(index, length));
        //take random amount from mom's and dad's DNA for kids' DNA
        Chromosome chromo1 = new Chromosome(dnaMom);
        Chromosome chromo2 = new Chromosome(dnaDad);

        //Create new genomes with DNA and name
        Genome kid1 = new Genome(chromo1);
        Genome kid2 = new Genome(chromo2);

        //Try to mutate kids to get some diversity
        kid1 = mutate(kid1);
        kid2 = mutate(kid2);

        newPopulation.add(kid1);
        newPopulation.add(kid2);

    }

    private Genome mutate(Genome gen) {
        Random ran = new Random();
        List<Double> dna = new ArrayList<>(gen.getDNA());//change a chromosome if mutation rate was matched (low chance)

        for (int i = 0; i < dna.size(); i++) {
            Double chromosome = dna.get(i);
            
            //TODO go through numbers in double
            if (mutationRate >= ran.nextDouble())
                gen.getDNA().set(i,9 - chromosome);
        }

        return gen;
    }

    public void testPrintAndScorePopulation(List<Double> inputs, List<Double> expOutputs) {
        int genomeCountr = 0;
        for (Genome gen : population) {
            testGenome(gen, inputs);
            List<Double> outputs = network.outputs;
            //set fitness of member
            scoreGenome(gen, expOutputs, outputs);
            //print out structure and values in neural net for this member
            //network.printNet();
            //print test results
            System.out.println("Genome Generation " + generationCount +", Member " + genomeCountr + " " + gen.toString());
           // System.out.println(network.toString());
            genomeCountr++;
        }
    }

    public void testAndScorePopulation(List<Double> inputs, List<Double> expOutputs) {
    int genomeCountr = 0;
        for (Genome gen : population) {
            testGenome(gen, inputs);
            List<Double> outputs = network.outputs;
            //set fitness of member
            scoreGenome(gen, expOutputs, outputs);
            //print out structure and values in neural net for this member
            //network.printNet();
            //print test results
            genomeCountr++;
        }
    }
    
    
    //TODO better name
    private void testGenome(Genome gen, List<Double> inputs) {
        //set the network values for the member
        network.setWeights(gen.getDNA());

        //get outputs calculated with the members DNA as weights
        network.calculateOutputs(inputs);
    }

    private void scoreGenome(Genome gen, List<Double> expOutputs, List<Double> actOutputs) {
        //if output guess correctly, increase fitness by 1
        List<Double> rounded = new ArrayList<>();
        
        for(Double d : actOutputs){
            rounded.add((double)Math.round(d));
        }
        
        if (expOutputs.equals(rounded)){
            gen.setFitness(gen.getFitness() + 1);
        }
        if(gen.getFitness() >2)
            winner.add(gen);
    }
    
    public boolean isDone(){
        return winner.size() >=1;
    }
}
