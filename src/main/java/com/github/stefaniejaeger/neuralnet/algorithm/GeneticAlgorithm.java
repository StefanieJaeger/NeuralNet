package com.github.stefaniejaeger.neuralnet.algorithm;

import com.github.stefaniejaeger.neuralnet.Test;
import com.github.stefaniejaeger.neuralnet.network.NeuralNet;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private RouletteWheel<Genome> rouletteWheel;

    public List<Genome> winner;

    public PrintWriter writer;

    private RandomProvider randomProvider;

    public GeneticAlgorithm(GeneticAlgorithmConfiguration geneticAlgorithmConfiguration, RandomProvider randomProvider) {
        this.network = geneticAlgorithmConfiguration.neuralNet;
        this.crossoverRate = geneticAlgorithmConfiguration.crossoverRate;
        this.mutationRate = geneticAlgorithmConfiguration.mutationRate;

        this.randomProvider = randomProvider;

        this.population = new ArrayList<>();
        this.winner = new ArrayList<>();

        try {
            writer = new PrintWriter("statistics/generationAverage.csv", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //create the first generation
        initializePopulation(geneticAlgorithmConfiguration.populationSize, geneticAlgorithmConfiguration.chromosomeLengthOfGenome);
    }

    /**
     * Initialize population with first generation
     *
     * @param size             number of members in one generation/population
     * @param chromosomeLength number of doubles in dna
     */
    private void initializePopulation(int size, int chromosomeLength) {
        for (int i = 0; i < size; i++) {
            List<Molecule> dna = new ArrayList<>();
            for (int j = 0; j < chromosomeLength; j++) {
                dna.add(new Molecule(randomProvider.getDoubleInRange(-1, 1)));
            }

            //add new genome with new chromosome to population
            population.add(new Genome(new Chromosome(dna)));
        }
    }

    /**
     * Make additional generation, members are generated by members of previous generation
     */
    public void makeNextGeneration() {
        //make new generation
        newPopulation = new ArrayList<>();
        populateRouletteWheel();

        while (newPopulation.size() < population.size()) {
            List<Genome> parents = getMomAndDad();
            makeBabies(parents.get(0), parents.get(1));
        }

        generationCount++;
        population = newPopulation;
    }

    /**
     * Fill roulette wheel with members of current generation, give each as many seats as their fitness
     */
    private void populateRouletteWheel() {
        //add population member to the list as many times as his fitness
        rouletteWheel = new RouletteWheel<>(randomProvider);
        for (Genome gen : population) {
            for (int i = 0; i < gen.getFitness() + 1; i++) {
                rouletteWheel.add(gen);
            }
        }
    }

    /**
     * Randomly take two genomes from roulette wheel as mom and dad
     *
     * @return Two genomes as mom and dad
     */
    private List<Genome> getMomAndDad() {
        List<Genome> parents = new ArrayList<>();

        parents.add(rouletteWheel.getRandomElement());
        parents.add(rouletteWheel.getRandomElement());

        while (parents.get(0).getChromosome() == parents.get(1).getChromosome())
            parents.set(1, rouletteWheel.getRandomElement());

        return parents;
    }

    /**
     * Generate two genomes with random mix of mom and dad's DNA then add them to newPopulation
     * @param mom
     * @param dad 
     */
    private void makeBabies(Genome mom, Genome dad) {
        if (crossoverRate < randomProvider.getDoubleInRange(0, 1)) {
            //mom and dad get to live on if they don't fuck
            newPopulation.add(new Genome(mom.getChromosome()));
            newPopulation.add(new Genome(dad.getChromosome()));

            return;
        }

        // if crossoverRate gets matched, make two new genomes from mom and dad
        int length = mom.getChromosome().getDNA().size();
        int index = randomProvider.getIntegerInRange(0, length);

        Chromosome kiddo1Chromosome = new Chromosome(mom.getChromosome());
        Chromosome kiddo2Chromosome = new Chromosome(dad.getChromosome());

        kiddo1Chromosome.crossover(index, kiddo2Chromosome);

        //Create new genomes with Chromosome
        Genome kid1 = new Genome(kiddo1Chromosome);
        Genome kid2 = new Genome(kiddo2Chromosome);

        //Try to mutate kids to get some diversity
        mutate(kid1);
        mutate(kid2);

        newPopulation.add(kid1);
        newPopulation.add(kid2);
    }

    /**
     * For each molecule in DNA, get random double, if it matches mutationRate, 
     * switch molecule for random double
     * @param genome 
     */
    private void mutate(Genome genome) {
        for (Molecule molecule : genome.getChromosome().getDNA()) {
            if (mutationRate > randomProvider.getDoubleInRange(0, 1)) {
                molecule.mutate(randomProvider);
            }
        }
    }

    public void testPrintAndScorePopulation(List<Test> testCases) {
        int genomeCounter = 0;
        for (Genome genome : population) {
            network.setWeights(genome.getChromosome().getDNA().stream().map(Molecule::getValue).collect(Collectors.toList())); // TODO: WTF Jeremy
            for (Test test : testCases) {
                if (test.isOutputCorrect(network.calculateOutputs(test.getInputs()))) {
                    genome.increaseFitness();
                    if (genome.getFitness() > 3.0 && !winner.contains(genome))
                        winner.add(genome);
                }
            }
            String text = generationCount + ";" + genomeCounter + "; " + genome.getFitness() + "; " + genome.getChromosome().getDNA();
            //System.out.println("Genome Generation " + generationCount + ", Member " + genomeCounter + " " + genome.toString());
            System.out.println(text);
            /*try {
                Files.write(Paths.get("statistics.txt"), "the text".getBytes(), StandardOpenOption.APPEND);
            }catch (IOException e) {
                //exception handling left as an exercise for the reader
            }*/

            genomeCounter++;
        }
        System.out.println(generationCount + "," + getPopulationAverageFitness());
        writer.println(generationCount + "," + getPopulationAverageFitness());
        writer.flush();
    }
    
    /**
     * Get the average fitness of all members of a population
     * @return 
     */
    private double getPopulationAverageFitness(){
        double fit = 0.0;
        for(Genome gen : population)
            fit += gen.getFitness();
        return fit/population.size();
    }
    
    /**
     * Is true if at least one genome has reached full fitness
     * @return 
     */
    public boolean isDone() {
        return winner.size() >= 1;
    }
}
