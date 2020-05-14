# Lab 5: HugLife

<a href="https://sp19.datastructur.es/materials/lab/lab5/lab5" target="_blank">Curriculum Website</a>

## Introduction
In this lab, you’ll create a package named `creature` that will implement two creatures (or more, if you’d like) that will inhabit a world simulated by the `huglife` package. (See chapter 7 of the textbook for an introduction to what a package is.) Today’s lab is meant to be a soft and fun introduction to Maps after your midterm.

### HugLife
First, pull the Lab 5 starter files:
```
git pull skeleton master
```
Start the lab by booting up the HugLife simulator. To do this:

* Import the project into IntelliJ
* In the sidebar of IntelliJ, look in the `huglife` package/folder and open the `HugLife.java` file.
* Run the file by clicking on the green play button next to line number 10. It will print out `Usage: java huglife.HugLife [worldname]`.
* The usage message tells us that we need to pass in a command line argument in order to run the file (remember LeapYear from Lab 1?). We can do this in IntelliJ by clicking on the dropdown menu at the top of IntelliJ where it says `HugLife` (next to the other run and debug button in the top right corner). From that menu, select `Edit Configurations...` and add to `Program Arguments`: the argument `samplesolo`. Press `OK` and run the file again. This time, you should see our world open with a lonely red square wandering around randomly.

In this assignment you will create the following two creature files that will go in the `creatures/` directory:

* `Plip.java` (skeleton provided)
* `Clorus.java` (you’ll need to create this)

These classes will extend the huglife.Creature class, which provides a template that all creatures should follow.

Eventually these two types of creatures will also inhabit the world, and they will interact with one another <a href="https://i.imgur.com/E2Kdowq.gifv" target="_blank">in an interesting way</a>.

### How the simulator works
Creatures live on an NxN grid, with no wraparound. Each square may be empty, impassible, or contain exactly one creature. At each timestep, exactly one creature takes a single action. Creatures choose actions in a round-robin fashion; they take turns one after another.

There is a global queue of all creatures in the world, waiting their turn to take an action. When a creature is at the front of the queue, the world simulator tells that creature who its four neighbors are and requests a choice of action from the creature. More specifically, the world simulator calls the creature’s `chooseAction()` method, which takes as an argument a collection of all four neighbors. Based on the identity of the four neighbors, the creature then chooses one of exactly five actions: MOVE, REPLICATE, ATTACK, STAY, or DIE.

MOVE, REPLICATE, and ATTACK are directional actions, and STAY and DIE are stationary actions. If a creature takes a directional action, it must specify either a direction or a location. For example, if the acting creature sees a creature to its LEFT that it can eat, it might choose to ATTACK LEFT.

One of your main tasks for this lab is to write the code that makes Creature decisions. Actions are returned as objects of type `Action`, which are fully described in `huglife/Action.java`.

After a creature chooses an `Action`, the simulator enacts the changes to the world grid. You’ll be responsible for writing the code that tracks the state of each creature. For example, after the acting creature eats another creature, the acting Creature might become stronger, die, change colors, etc.

This will be accomplished by a callback to the creature, which is required to provide `move()`, `replicate()`, `attack()`, and `stay()` methods that describe how the state of the acting creature will evolve after each of these respective actions.

For example, if your creature chooses to replicate upwards by returning `new Action(Action.ActionType.REPLICATE, Direction.TOP)`, then the game simulator is guaranteed to later call the `replicate()` method of the creature that made this choice.

## Experimenting with the Sample Creature
Open up `Occupant.java`, `Creature.java`, and `SampleCreature.java`, which you’ll find in the `huglife/` directory.

* `Occupant` is a general class for all possible things that can inhabit the grid of the HugLife universe. You’ll see that every `Occupant` inherits a name, shared by all instances of that `Occupant` subtype. Furthermore, every `Occupant` must provide a method that returns a color (more on this later). There are two special `Occupant` types, with names “empty” and “impassible”. These represent unoccupied and unoccupiable squares, respectively.
* `Creature` is a general class for all living things that can inhabit the grid of the HugLife universe. Every `Creature` has an energy level, and if that energy level ever falls below zero, the universe will choose the DIE action for them.

   * Every creature must implement four callback methods: `move()`, `replicate()`, `attack()`, and `stay()`. These describe what the creature should do when each of these actions occurs. There is no `die()` method since the creature is simply removed from the world entirely.

   * Creatures must also implement a `chooseAction()` method.

* `SampleCreature` is a sample `Creature`; in fact, it’s the lonely red square we saw earlier. The two creatures you implement for this lab will look somewhat similar to the `SampleCreature`, so you’ll want to consult this class later.

Make some changes to the sample creature and observe how they affect the HugLife simulation. You can run the simulator the same way you did at the beginning of this lab.

** Creating a Plip
Now it’s time to add a new type of creature to the world. Go into the `creatures/` directory, and you’ll see a file named `Plip.java` there, waiting to be filled out.

Basic Plip functionality
`Plips` will be lazy (but motile) photosynthesizing creatures. They mostly just stand around and grow and replicate, but they’ll flee if they happen to see their mortal enemy, the `Clorus`.

Open `TestPlip.java`, which is also in the `creatures/` directory. You’ll see that we’ve provided a test file `creatures.TestPlip` that you can run to test your Plip class.

Try it out and you’ll see that initially the `testBasics` test fails.

Now modify the `Plip` class according to the specifications below until all tests pass. When you’re done, you’ll be well on your way to having a fully functional Plip!

Let’s start with just a few of the properties that we’ll eventually need for our `Plip` class.

* The `name()` method (inherited from `Occupant`) should return exactly `"plip"` with no spaces or capitalization. This is important, since creatures only know how to react to each other based on this name string. (Do you actually have to change anything to ensure this?)
* Plips should lose `0.15` units of energy on a MOVE action, and gain `0.2` units of energy on a `STAY` action.
* Plips should never have energy greater than `2`. If an action would cause the Plip to have energy greater than `2`, then it should be set to `2` instead.
* Plips should also never have energy less than `0`. If an action would cause the Plip to have energy less than `0`, then it should be set to `0` instead.
* The `color()` method for Plips should return a color with `red = 99`, `blue = 76`, and `green = 96*e+63`, where ‘e’ is the plip’s energy such that: If the plip has zero energy, it should have a `green` value of `63`. If it has max energy, it should have a `green` value of `255`.

We could test our `Plip` class by sticking a bunch of Plips on a HugLife world grid and watching what they do as they run amok. However, it would be hard to determine whether everything was working correctly. Instead, let’s perform testing on the `Plip` class directly.

*Note on testing:* It’s not necessarily desirable to test everything! Use tests only when you think they might reveal something useful, i.e. there is some chance you’ll get something wrong. Figuring out what to test is a bit of an art!

### The Plip replicate method
Now we’ll work on adding the correct replication property to our Plips. Before your start, read the specifications below and write an appropriate test in the `testReplicate()` method. Be sure to check that the returned `Plip` is not the same `Plip` as the `Plip` whose `replicate()` method was called.

The replication behavior of Plips is:

* When a Plip replicates, it keeps 50% of its energy. The other 50% goes to its offspring. No energy is lost in the replication process.

Implement this logic in the `replicate()` method in `Plip.java`.

### The Plip chooseAction() method
All that’s left is giving the Plip a brain. To do this, you’ll be filling out the `chooseAction()` method. This time, we’ve provided a JUnit test for you.

The Plip should obey the following behavioral rules, in order of preference:

1. If there are no empty spaces, the Plip should `STAY`.
2. Otherwise, if the Plip has energy greater than or equal to 1.0, it should replicate to an available space.
3. Otherwise, if it sees a neighbor with `name()` equal to “clorus”, it will move to any available empty square with probability 50%. It should choose the empty square randomly. As an example, if it sees a Clorus to the `BOTTOM`, and “empty” to the `TOP`, `LEFT`, and `RIGHT`, there is a 50% chance it will move (due to fear of Cloruses), and if it does move, it will pick uniformly at random between `TOP`, `LEFT`, and `RIGHT`.
4. Otherwise, it will stay.

These rules must be obeyed in this strict order! Example: If it has energy greater than 1, it will always replicate, even if there are neighboring Cloruses.

### Testing for `chooseAction()`
This time around, we’ve provided tests for you so you can focus on figuring out how the map works for the implementation. Before you start on `chooseAction()`, uncomment the `@Test` annotation tag for the `testChoose()` method in `TestPlip.java`. This will allow the testChoose test to run. Read through the tests before you begin to make sure you agree with the expected results.

For now, `testChoose()` will only test the first two options (`STAY` and `REPLICATE`) Don’t worry (yet) about testing the 50% rule if a Clorus is nearby since you haven’t created a `Clorus` class yet.

Later, once you write the `Clorus` class, you might find it interesting to come back and try to write a randomness test since running away from the clorus only happens with a 50% chance. One possibility is to simply test that both choices are possible by making many calls and ensuring that each happens at least once. Performing a statistical test is probably a bit too much for lab today (though you’re welcome if you want the extra challenge).

### Writing `chooseAction()`
Edit the `Plip` class so that it makes the right choices. You’ll want to look carefully at the `SampleCreature` class as a guide to get a feel for the `huglife` functions. You also will need to figure out how to work with the `neighbors` parameter, which is a `Map`. To learn how to use it, take a look at the Java API for `Map`. You also may want to Google for how to use an enhanced for-loop to go through the keys of a map. What is a map? It is like a Python dictionary. Each key is mapped to a corresponding value, so in this case, each `Direction` is mapped to an `Occupant` (and remember that an `Creature`, `Empty`, and `Impassible` extend `Occupant`).

## Simulating Plips
Assuming your tests worked, you can now see how your Plips fare in the real HugLife world. Run `huglife.Huglife` with the command line argument `sampleplip`. (You can do this by editing the configurations of the program arguments again, as before.)

You should see your Plips happily growing along. If something goes wrong, it’s probably because your tests are not thorough enough. If this is the case, think about what actions the plip did and, using the error messages, add new tests to `TestPlip.java` until something finally breaks.

## Introducing the Clorus
Now we’ll implement the Clorus, a fierce blue-colored predator that enjoys nothing more than snacking on hapless Plips.

To begin, create `TestClorus.java` and `Clorus.java` in the `creatures` package. Unlike before, you’ll be writing these classes from scratch.

The Clorus should obey the following rules exactly:

* All Cloruses must have a name equal to exactly “clorus” (no capitalization or spaces).
* Cloruses should lose `0.03` units of energy on a `MOVE` action.
* Cloruses should lose `0.01` units of energy on a `STAY` action.
* Cloruses have no restrictions on their maximum energy.
* The `color()` method for Cloruses should always return the color `red = 34`, `green = 0`, `blue = 231`.
* If a Clorus attacks another creature, it should gain that creature’s energy. This should happen in the `attack()` method, not in `chooseAction()`. You do not need to worry about making sure the attacked creature dies—the simulator does that for you.
* Like a Plip, when a Clorus replicates, it keeps 50% of its energy. The other 50% goes to its offspring. No energy is lost in the replication process.
* Cloruses should obey exactly the following behavioral rules:
   1. If there are no `empty` squares, the Clorus will `STAY` (even if there are Plips nearby they could attack since plip squares do not count as empty squares).
   2. Otherwise, if any Plips are seen, the Clorus will `ATTACK` one of them randomly.
   3. Otherwise, if the Clorus has energy greater than or equal to one, it will `REPLICATE` to a random empty square.
   4. Otherwise, the Clorus will `MOVE` to a random empty square.
   
As before, write a `TestClorus` class first. You probably don’t need to test the `move()`, `stay()`, or `color()` methods, but you’re welcome to. You should include at least one test for each type of action.

Once you’re done writing *thorough* tests, write the `Clorus` class itself.

## Showtime
We did it.

Now it’s time to watch Cloruses and Plips battling it out. Go into `HugLife.java` and uncomment the lines in `readWorld()`. Then, kick off a Manichaean struggle that will end either in eternal oscillations or in lonely immortals wandering the wastes forever by running `huglife.Huglife` with the command line argument `strugggz`. (Again, you can do this by Editing Configurations and entering this into the Program Arguments.)

If you did everything right, it should hopefully look cool. You might consider tweaking the HugLife simulator parameters, including the world size and the pause time between simulation steps. Be warned that world sizes above 50x50 are probably going to run fairly slowly.
