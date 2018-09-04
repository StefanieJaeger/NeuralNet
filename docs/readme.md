# Welcome to this Github Page

## What is this project about?

It's about a neural network and machine learning. 
But mostly just meant as a training exercise.

## How does it work?

### The Neural Network
Neural networks are inspired by the human brain (it's in the name). 
There are neurons responsible for taking inputs from outside (the problem they have to solve), some responsible for taking that input and some other values (getting to these shortly) and calculating new ones and some responsible for taking the last calculated value of the previous neurons and forming an answer for the problem from it. They are all grouped into layers. There is typically one input layer, one output layer and one or multiple hidden layers.

Now these neurons are connected with the ones in the layers before and after them. These connections have a value to them called *weight*. Look at it like a value saying how important the value of the neuron it comes from is, how much weight it gets.

The ultimate goal of machine learning is to find the weights, values for the connections between neurons, that produce the correct output for all tests. There are multiple sets of weights that will achieve the goal, we only need to find one.

Here is a picture to try to explain it better:
[]

### What is *genetic algorithm*?
In an attempt to explain the process of genetic algorithm, I have created a short comic. It should help understanding that part of genetic algorithm, but skips completely over how a neural network actually computes its output.

#### Words
As the name hints, genetic algorithm is a way to figure out the perfect weights by imitating genetics.

You start by making a population, consisting of genomes. A genome is simply something that stores your DNA. DNA is simply all the weights this genomes has in its neural network (brain).

You then randomly choose two genomes with a roulette wheel and try to make them produce an offspring. The closer the genomes got to giving the right answer for all tests, the more likely  they are to be chosen on the roulette wheel. If they mate or not is determined by the crossover rate and if their baby has some small mutation in their DNA is determined by the mutation rate (All this to mimic actual evolution).

Once you have built a new generation of genomes, repeat the previous steps.
The whole process ends, when you have discovered a genome that produces the right output for all tests.

#### Comic


### What is *backpropagation*?
I will try to explain it in words, it's a bit of a complicated topic though and hard to grasp at first. It's really focused on math but it's not necessary to be able to calculate everything yourself, you only have to understand why we do it. That's why I also started making a comic about it, trying to explain it in a simple and visual way.

#### Words
Like really really complicated.

#### Comic
[]

## Who is a good boy?
- clearly Jeremy
- very clearly Jeremy
- You seen what a good boy Jeremy is?
- No? Then you're blind
- Yes? Good. Cause he good boy
- Jeremy