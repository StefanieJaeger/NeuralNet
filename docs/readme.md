<link href="css/neural-net.css" rel="stylesheet" type="text/css"></link>

# Welcome to this Github Page

## What is this project about?

It's about a neural network and machine learning. 
But mostly just meant as a training exercise.

## How does it work?

In the following I will try to explain how the neural network and the two algorithms I used work.

### The Neural Network
Neural networks are inspired by the human brain (it's in the name). 
There are `neurons` responsible for taking inputs from outside (the problem they have to solve), some responsible for taking that input and some other values (getting to these shortly) and calculating new ones and some responsible for taking the last calculated value of the previous neurons and forming an answer for the problem from it. They are all grouped into `layers`. There is typically one `input layer`, one `output layer` and one or multiple `hidden layers`. The amount of neurons in this layers depends; one `input neuron` for each input your problem has (XOR needs 2, a 64 pixel image needs 64), one `output neuron` for each output you need () and the `hidden neurons` can be chosen and tried out, until a good amount was found.

Now these neurons are connected with the ones in the layers before and after them. These `connections` have a value to them called`weight`. Look at it like a value saying how important the value of the neuron it comes from is, how much it weights.

The ultimate goal of *machine learning* is to find the weights, values for the connections between neurons, that produce the correct output for all tests. There are multiple sets of weights that will achieve the goal, we only need to find one.

Here is a picture to try to explain it better:
[network]

### What is *genetic algorithm*?
In an attempt to explain the process of `genetic algorithm`, I have created a short comic. It should help understanding that part of genetic algorithm, but skips completely over how a neural network actually computes its output.

#### Words
As the name hints, genetic algorithm is a way to figure out the perfect weights by imitating genetics.

You start by making a `population`, consisting of `genomes` (genomes are like primitive humans). A genome is simply something that stores `DNA`. DNA is simply all the weights this genomes has in its neural network (brain).

You then randomly choose two genomes with a `roulette wheel` and try to make them produce an offspring. The closer the genomes got to giving the right answer for all tests, the more likely  they are to be chosen on the roulette wheel. If they mate or not is determined by the `crossover rate` and if their baby has some small mutation in their DNA is determined by the `mutation rate` (All this to mimic actual evolution).

Once you have built a new generation of genomes, repeat the previous steps.
The whole process ends, when you have discovered a genome that produces the right output for all tests.

#### Comic

[geneticComic]


### What is *backpropagation*?
I will try to explain it in words, it's a bit of a complicated topic though and hard to grasp at first. It's really focused on math but it's not necessary to be able to calculate everything yourself, you only have to understand why we do it. That's why I also started making a comic about it, trying to explain it in a simple and visual way.

#### Words

First, *gradient descent*.
It's a mathematical way to try to get the output of a calculation closer to a wanted value in small steps.
Imagine you're on a hill. You want to get to a little valley. If you take huge steps and run down the hill, you might overshoot and run past it, but if you take small, careful steps, you'll get there slowly but won't run past it. 
In the same way we change the weights of the neural network just a little. We do these changes for every test, then sum them to try to slowly get to the right weights, producing the right outputs.

*Cost*
The `cost` is a value, describing how bad our network is in getting the right value. Our goal is to minimize the cost. The calculation of the cost is a bit tricky. Here's a picture describing it.

[costCalculation]

*Backpropagation* is a way to apply gradient descent.

For every test we have, we calculate the `changes` the weights need to solve it. The change is the cost calculated for the weight.  After we got those values for every test, we sum the changes for every weight and multiply by the `learning rate`. The learning rate makes sure to keep our steps small enough as it is between 0 and 1.
So Test<sub>1</sub> wants Weight<sub>1</sub> to increase by 0.2 and Weight<sub>2</sub> by 0.4. Test<sub>2</sub> wants Weight<sub>1</sub> to increase 0.1 and Weight<sub>2</sub> decrease by 0.8. So for Weight<sub>1</sub> the change would be (0.2 + 0.1) * learning rate and for Weight<sub>2</sub> (0.4 - 0.8) * learning rate.

#### Comic
[backpropComic]

## Who is a good boy?
- clearly Jeremy
- very clearly Jeremy
- You seen what a good boy Jeremy is?
- No? Then you're blind
- Yes? Good. Cause he good boy
- Jeremy

## Sources

Wondering where to read up on neural networks, AI and machine learning? 
Here are the links to some pages I found useful.

- https://www.google.com/AI
- https://www.ai-junkie.com (requires Flash, go to http://www.ai-junkie.com/ga/intro/gat1.html for genetic algorithm, to http://www.ai-junkie.com/ann/evolved/nnt1.html for neural nets)
- https://www.youtube.com/channel/UCYO_jab_esuFRV4b17AJtAw (Has great videos on backpropagation)
- http://neuralnetworksanddeeplearning.com/ (For backpropagation mostly)
- Jeremy Stucki
- https://stevenmiller888.github.io/mind-how-to-build-a-neural-network/ 



[network]: https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png
[geneticComic]: https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png
[costCalculation]: https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png
[backpropComic]: https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png

