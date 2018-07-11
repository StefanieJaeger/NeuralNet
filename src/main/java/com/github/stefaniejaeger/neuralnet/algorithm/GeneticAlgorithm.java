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

    public GeneticAlgorithm(NeuralNet network, double crossoverRate, double mutationRate, int populationSize, int chromosomeLength) {
        this.network = network;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.population = new ArrayList<>();
        //create the first generation
        makeGeneration(populationSize, chromosomeLength);
    }

    private void makeGeneration(int size, int chromosomeLength) {
        Random ran = new Random();
        for (int i = 0; i < size; i++) {
            List<Double> dna = new ArrayList<>();
            //populate the DNA with random doubles
            for (int j = 0; j < chromosomeLength; j++) {
                dna.add(ran.nextDouble());
            }
            //create the genome with DNA and name
            Genome gen = new Genome(dna);
            population.add(gen);
        }
    }

    public void makeNextGeneration() {
        //make new generation (cannot be the first one)
        newPopulation = new ArrayList<>();
        populateRouletteWheel();

        while (newPopulation.size() < population.size()) {
            List<Genome> parents = getMomAndDad();
            makeBabies(parents.get(0), parents.get(1));
        }

        generationCount++;
        population = newPopulation;
    }

    private void populateRouletteWheel() {
        //add population member to the list as many times as his fitness
        rouletteWheel = new ArrayList<>();
        for (Genome gen : population) {
            for (int i = 0; i < gen.getFitness(); i++)
                rouletteWheel.add(gen);
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
            newPopulation.add(mom);
            newPopulation.add(dad);
            return;
        }

        //if crossoverRate gets matched, make two new genomes from mom and dad
        int index = ran.nextInt(mom.getDNA().size());

        //take random amount from mom's and dad's DNA for kids' DNA
        List<Double> dna1 = mom.getDNA().subList(0, index - 1);
        List<Double> dna2 = dad.getDNA().subList(0, index - 1);
        dna1.addAll(dad.getDNA().subList(index, dad.getDNA().size()));
        dna2.addAll(mom.getDNA().subList(index, mom.getDNA().size()));

        //Create new genomes with DNA and name
        Genome kid1 = new Genome(dna1);
        Genome kid2 = new Genome(dna2);

        //Try to mutate kids to get some diversity
        kid1 = mutate(kid1);
        kid2 = mutate(kid2);

        newPopulation.add(kid1);
        newPopulation.add(kid2);

    }

    private Genome mutate(Genome gen) {
        Random ran = new Random();
        List<Double> dna = gen.getDNA();//change a chromosome if mutation rate was matched (low chance)

        for (int i = 0; i < dna.size(); i++) {
            Double chromosome = dna.get(i);

            if (mutationRate >= ran.nextDouble())
                dna.set(i,9 - chromosome);
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
            network.printNet();
            //print test results
            System.out.println("Genome Generation " + generationCount +", Member " + genomeCountr + ", with dna " + gen.getDNA().toString() + " has fitness " + gen.getFitness());
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
        if (expOutputs.equals(actOutputs))
            gen.setFitness(gen.getFitness() + 1);
    }
}