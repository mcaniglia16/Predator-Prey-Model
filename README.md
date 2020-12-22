
## Predator Prey Model Project 
### Authored by: Josh Rosenfeld, Marco Caniglia 

### Introduction to the problem 📑
When things have the right balance and are in the right proportions, the world is known
to be in equilibrium. Such is the case with populations of different species coexisting in an
environment. This project will consider how populations of foxes,
rabbits and carrots interact with each other and how their population sizes vary over time on an
isolated island. Under ideal conditions, the population of all species should grow and decline
steadily, in a cyclical manner. Of course, nature does not always behave ideally; natural disasters
occur and humans also inevitably interfere with the balance in nature. Can a model be created
that most accurately represents a real-life situation and that incorporates realistic disruptions in
real time? How might human interferences affect the balance of these populations? This project
will feature a real-time graph that demonstrates the changes in three species’ populations over a
century while incorporating the effect of two human disruptions that will be later discussed.

### Mathematical Model 📈📊
The project relies on the Lotka-Volterra Equations, and modifies them to make our model
more representative of real life. These equations are the most common way to describe and
model the basic ecological predator-prey relationship. Lotka-Volterra equations state that in
order for the model to be accurate, it must be representative of a closed ecosystem where no
migration occurs, like on our island. Next, there may only be two animals: predators and prey, in
a very simple food chain: the predator consumes prey, and the prey consumes some kind of
vegetation (which has an endless supply). The following 4 variables must be considered:
![alt text](https://user-images.githubusercontent.com/50206147/102924257-35324180-445f-11eb-8d76-1ef6f1f917fd.jpg)

Using these variables, we can find the non-linear differential equations of each species:
* Prey, x: dx/ dt = Ax - Bxy → dx = Ax dt - Bxy dt
* Predator, y: dy/ dt = Dxy - Cy → dy = Dxy dt - Cy dt

As said in the introduction, we’d like to make it as realistic as possible: in order to make
the populations’ growth more representative of real life, we can implement logistic growth
instead of exponential growth. In exponential growth, the growth rate allows the population to
growth endlessly, growing quicker as the population increases. In logistic growth, the carrying
capacity, or maximum size of a population in certain conditions, restricts the size of the
population, and the growth rate decreases as the population grows (essentially, the growth rate
depends on the size of the population). Having a population ceiling is true in real life, so it
should be incorporated into the differential equations. To get things started, the basic differential
equation for population at a given time will look something like:
* dP/dt = R*P

Where dP/dt is the growth rate of a population at a given time, P is population size, t is
time and R is the rate of increase per capita. If R is always at its maximum, Rmax, then the
growth will be limitless, turning into exponential growth:
* dP/dt =(Rmax)*P

Logistic growth, as explained above will alter the differential equation for population growth:
* dP/dt = Rmax*((K-P)/K)*P

Where dP/dt is the growth rate of a population at a given time, P is population size, t is
time, Rmax is the maximum per capita rate of increase and K is the maximum population, or
carrying capacity. As the population approaches the carrying capacity, dP/dt heads towards zero.
This should produce an a curve that closely resembles periodic motion. The improved
differential equations using the same variables as before, K as the carrying capacity of the prey
and Q as the carrying capacity of the predators now looks like:
* Prey, x: dx/ dt = A*((K-x)/K)*x - Bxy
* Predator, y: dy/ dt = Dxy - C*((Q-y)/Q)*y

Of course, the previous models, involving only two populations were more simple. New,
more complex differential equations will be needed to try to model a predator/prey model with 3
species. In a three-species system, it is important to note that it is still an isolated environment
and a simple feeding chain, where the carnivore consumes the herbivore while the herbivore
consumes the vegetation. Our island features Foxes (carnivores), Rabbits (herbivores) and
Carrots (vegetation). The food chain will look like:
* Foxes → Rabbits → Carrots

The resulting differential equations are the following: 
![alt text](https://user-images.githubusercontent.com/50206147/102924315-4d09c580-445f-11eb-945e-f1436d937048.png)
![alt text](https://user-images.githubusercontent.com/50206147/102924334-55620080-445f-11eb-9c4f-bfc49f119f4a.png)

## Results and Discussion 🎯
As we can observe from the plot obtained from the code, the populations are balanced
and reach a dynamic equilibrium: rising and declining in a cyclical manner. In fact, the graphs
shows that the period of each cycle is approximately 10 years. The graph also demonstrates that
the size of each population affects each other: the number of foxes depends on the number of
rabbits, the number of rabbits depends on the number of carrots as well as the number of foxes,
and the number of carrots depends on the number of rabbits.
![alt text](https://user-images.githubusercontent.com/50206147/102924347-5eeb6880-445f-11eb-9726-dd66358b1515.png)
As we take a closer look, we can notice that the population of the rabbits closely follows
the population of the carrots: as the number of carrots rise, the rabbits have more food and
therefore can grow more. The same is true for the foxes, as their population relies on the number
of rabbits, but to a slightly less extent.

When the human disruption is first introduced, at year 50, the chemicals in the soil
eliminates the carrots. This disruption lowers the population of carrots quite significantly, which
has a drastic effect on the population of rabbits, as the carrots are the only food source available
for the preys. We can observe the population of rabbits decrease to its second lowest point in the
simulation. The population of the foxes is also affected, but to a lesser extent as they are less
dependant on the number of rabbits. Following this decrease, the populations come back to the
dynamic equilibrium and continue to cycle with the same 10 year period as they did previously.
When the second human disruption is introduced, at year 80, the population of foxes drop
at a very rapid rate. This has some interesting effects; allowing the rabbits to grow, which
prevents the population of the carrots to rise (as they normally would have in the cycle). After
this, the excess of rabbits allows the population of foxes to grow relatively quickly, as the whole
equilibrium becomes balanced again by the end of the simulation.

As shown, human interferences do have significant effects on the cycle of the populations
residing on the island. In fact, when the simulation was run with disruptions that were too lethal
to a certain species, that species’ population went to zero, and the balance was completely
ruined. However, in a perfectly balanced ecosystem with manageable interferences, nature
balances everything out and the populations will reach an equilibrium once these disruptions are
over.

### To observe the above graph being plotted in real-time, simply run the term_project.java file
