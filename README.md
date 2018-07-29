# NeuralNet

[![Build Status](https://travis-ci.com/StefanieJaeger/NeuralNet.svg?token=U9MukhwMxwoLzPLAc6vx&branch=master)](https://travis-ci.com/StefanieJaeger/NeuralNet)

#### This is a base structure of a neural net that uses *genetic algorithm*.

---

It's mainly thought as a practical exercise for me to understand how a neural net works but will hopefully still be usable in later projects.

---

Number of input and output neurons, hidden layers and neurons in hiddenlayers plus bias can be specified.
It tries to find the perfect weight as a double through "evolution".

## Explanation

**Imagine you're a GOD!**

Over time you have grown lonely and are hoping to find someone to talk to, a living creature with even a spec of inteligence would suffice.
Some time ago, you created humans, although they should little potential at their current state.
You set yourself a goal: Try to breed a single human, who could tell you wheter two bits are the same or not.

You start with a little village, you give everyone the same four tests: is 0 and 0, 0 and 1, 1 and 0, 1 and 1 the same?

Through your booming voice coming from the skies they hear your question (*it enters their input neuron*), they think very hard about it (*in their hidden neurons*), 
each member thinks about it in a different way, which was determined at birth through their *DNA*. Then each one answers each of your four tests (*with their ouput neuron*). 
For each right answer you give them a "*fitness point*", which helps you keep track of everyone's score. 

After they all answered, you randomly choose two of them, the higher their fitness the higher the chances of getting chosen (*roulette*). You put them in a room, put some 
romantic music on and hope they create an offspring (*crossover rate*). If they do, they must die (you cruel god), if they don't, they become part of the next generation. 
There is also a small change that some background radiation will swap one random part of a new kids DNA with a random new one (*mutation rate*). Kids will think in a mixture 
of their parents way of thinking.

You repeat this step (only make adults ~~fuck~~ copulate, not the kids) until your new population has the same amount of members as your old one. 
Then ask them the questions.
Then make a new generation.
Repeat until you find someone capable of solving all your tests.

Congrats, you found a new soulmate!



