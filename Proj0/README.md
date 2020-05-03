# NBody Simulation

<a href="https://sp19.datastructur.es/materials/proj/proj0/proj0#calcdistance" target="_blank">Website</a>

<a href="https://www.cs.princeton.edu/courses/archive/spring20/cos126/assignments/nbody/checklist.html" target="_blank">Probably useful reference</a>
## The Body Class and Its Constructor
You’ll start by creating a Body class. In your favorite text editor, create a new file called Body.java. If you haven’t picked a text editor, we recommend Sublime Text. Remember that your .java files should have the same name as the class it contains.

Begin by creating a basic version of the Body class with the following 6 instance variables:

* `double xxPos`: Its current x position
* `double yyPos`: Its current y position
* `double xxVel`: Its current velocity in the x direction
* `double yyVel`: Its current velocity in the y direction
* `double mass`: Its mass
* `String imgFileName`: The name of the file that corresponds to the image that depicts the body (for example, `jupiter.gif`)
Your instance variables must be named exactly as above, and they must be explicitly set to `public` via the public keyword. The reason we call them by double letters, e.g. `xxPos` rather than `xPos` is to reduce the chance of typos. In past semesters, students have accidentally pressed x when they meant y, and this has caused significant debugging hassle. After adding the 6 instance variables above, add in two Body constructors that can initialize an instance of the Body class. Later on, an instance of the Body class can represent a planet, star, or various objects in this universe. The signature of the first constructor should be:
```
public Body(double xP, double yP, double xV,
              double yV, double m, String img)
```
Note: We have given parameter names which are different than the corresponding instance variable name. If you insist on making the parameter names the same as the instance variable names for aesthetic reasons, make sure to use the "this" keyword appropriately (mentioned only briefly in lecture).

The second constructor should take in a Body object and initialize an identical Body object (i.e. a copy). The signature of the second constructor should be:
```
public Body(Body b)
```
Your Body class should NOT have a main method, because we’ll never run the Body class directly (i.e. we will never run `java Body`). Also, all methods should be non-static.

All of the numbers for this project will be doubles. We’ll go over what exactly a `double` is later in the course, but for now, think of it is a real number, e.g. `double x = 3.5`. In addition, all instance variables and methods will be declared using the public keyword.

Once you have filled in the constructors, you can test it out by compiling your `Body.java` file and the `TestBodyConstructor.java` file we have provided.

You can compile with the command:
```
javac Body.java TestBodyConstructor.java
```
Don’t worry if you get warnings about deprecation when compiling for this project.

You can run our provided test with the command
```
java TestBodyConstructor
```
If you pass this test, you’re ready to move on to the next step. **Do not proceed until you have passed this test.**

## Understanding the Physics
Let’s take a step back now and look at the physics behind our simulations. Our Body objects will obey the laws of Newtonian physics. In particular, they will be subject to:

* Pairwise Force: Newton’s law of universal gravitation asserts that the strength of the gravitational force between two particles is given by the product of their masses divided by the square of the distance between them, scaled by the gravitational constant $G=6.67⋅10^{−11}\frac_{Nm^2}{kg^2}$. The gravitational force exerted on a particle is along the straight line between them (we are ignoring here strange effects like the curvature of space). Since we are using Cartesian coordinates to represent the position of a particle, it is convenient to break up the force into its x- and y-components $(F_x, F_y)$. The relevant equations are shown below. We have not derived these equations, and you should just trust us.

   * $F=\frac_{G⋅m1⋅m2}{r^2}$
   * $r^2=dx^2+dy^2$
   * $F_x=\frac_{F⋅dx}{r}
   * $F_y=\frac_{F⋅dy}{r}
Look at the image below and make sure you understand what each variable represents!

Note that force is a vector (i.e., it has direction). In particular, be aware that dx and dy are signed (positive or negative).

* Net Force: The *principle of superposition* says that the net force acting on a particle in the x- or y-direction is the sum of the pairwise forces acting on the particle in that direction.
In addition, all bodies have:

* Acceleration: Newton’s second law of motion says that the accelerations in the x- and y-directions are given by:
   * $a_x=\frac_{F_x}{m}$
   * $a_y=\frac_{F_y}{m}$

## Writing the Body Class
In our program, we’ll have instances of the `Body` class do the job of calculating all the numbers we learned about in the previous example. We’ll write helper methods, one by one, until our Body class is complete.

### calcDistance
Start by adding a method called `calcDistance` that calculates the distance between two `Body`s. This method will take in a single Body and should return a double equal to the distance between the supplied body and the body that is doing the calculation, e.g.
```
samh.calcDistance(rocinante);
```
It is up to you this time to figure out the signature of the method. Once you have completed this method, go ahead and recompile and run the next unit test to see if your code is correct.

Compile and run with:
```
javac Body.java TestCalcDistance.java
java TestCalcDistance
```
Note that in Java, there is no built-in operator that does squaring or exponentiation.

### calcForceExertedBy
The next method that you will implement is `calcForceExertedBy`. The `calcForceExertedBy` method takes in a `Body`, and returns a double describing the force exerted on this body by the given body. You should be calling the `calcDistance` method inside this method. As an example, `samh.calcForceExertedBy(rocinante)` for the numbers in “Double Check Your Understanding” return $1.334⋅10^{−9}$.

Once you’ve finished `calcForceExertedBy`, re-compile and run the next unit test.
```
javac Body.java TestCalcForceExertedBy.java
java TestCalcForceExertedBy
```
Hint: It is good practice to declare any constants as a ‘static final’ variable in your class, and to use that variable anytime you wish to use the constant.

Hint 2: Java supports scientific notation. For example, I can write `double someNumber = $1.03e^{-7}$`;.

### calcForceExertedByX and calcForceExertedByY
The next two methods that you should write are calcForceExertedByX and calcForceExertedByY. Unlike the calcForceExertedBy method, which returns the total force, these two methods describe the force exerted in the X and Y directions, respectively. Remember to check your signs! Once you’ve finished, you can recompile and run the next unit test. As an example, samh.calcForceExertedByX(rocinante) in “Double Check Your Understanding” should return 1.0672⋅10−9.

NOTE: Do not use Math.abs to fix sign issues with these methods. This will cause issues later when drawing planets.
```
javac Body.java TestCalcForceExertedByXY.java
java TestCalcForceExertedByXY
```

### calcNetForceExertedByX and calcNetForceExertedByY
Write methods `calcNetForceExertedByX` and `calcNetForceExertedByY` that each take in an array of Bodys and calculates the net X and net Y force exerted by all bodies in that array upon the current `Body`. For example, consider the code snippet below:
```
Body[] allBodys = {samh, rocinante, aegir};
samh.calcNetForceExertedByX(allBodys);
samh.calcNetForceExertedByY(allBodys);
```
The two calls here would return the values given in “Double Check Your Understanding.”

As you implement these methods, remember that `Body`s cannot exert gravitational forces on themselves! Can you think of why that is the case (hint: the universe will possibly collapse in on itself, destroying everything including you)? To avoid this problem, ignore any body in the array that is equal to the current body. To compare two bodies, use the `.equals` method instead of `==`: `samh.equals(samh)` (which would return `true`). In week 2, we will explain the difference.

When you are done go ahead and run:
```
javac Body.java TestCalcNetForceExertedByXY.java
java TestCalcNetForceExertedByXY
```
If you’re tired of the verbosity of for loops, you might consider reading about less verbose looping constructs (for and the ‘enhanced for’) given on page 114-116 of HFJ, or online at this link. HW0 also goes into detail about enhanced for loops. This is not necessary to complete the project.

### update
Next, you’ll add a method that determines how much the forces exerted on the body will cause that body to accelerate, and the resulting change in the body’s velocity and position in a small period of time dt. For example, ·samh.update(0.005, 10, 3)· would adjust the velocity and position if an x-force of 10 Newtons and a y-force of 3 Newtons were applied for 0.005 seconds.

You must compute the movement of the Body using the following steps:

   1. Calculate the acceleration using the provided x- and y-forces.
   2. Calculate the new velocity by using the acceleration and current velocity. Recall that acceleration describes the change in velocity per unit time, so the new velocity is $(v_x+dt⋅a_x,v_y+dt⋅a_y)$.
   3. Calculate the new position by using the velocity computed in step 2 and the current position. The new position is $(p_x+dt⋅v_x,p_y+dt⋅v_y)$.

Write a method `update(dt, fX, fY)` that uses the steps above to update the body’s position and velocity instance variables (this method does not need to return anything).

Once you’re done, recompile and test your method with:
```
javac Body.java TestUpdate.java
java TestUpdate
```
Once you’ve done this, you’ve finished implementing the physics. Hoorah! You’re halfway there.


## (Optional) Testing Your Body Class
As the semester progresses, we’ll be giving you fewer and fewer tests, and it will be your responsibility to write your own tests. Writing tests is a good way to improve your workflow and be more efficient.

Go ahead and try writing your own test for the Body class. Make a `TestBody.java` file and write a test that creates two bodies and prints out the pairwise force between them. This is optional and we will not be grading this part of the assignment.

## Getting Started with the Simulator (NBody.java)
Create a file named `NBody.java`. NBody is a class that will actually run your simulation. This class will have NO constructor. The goal of this class is to simulate a universe specified in one of the data files. For example, if we look inside data/planets.txt (using the command line `more` command), we see the following:
```
$ more planets.txt
5
2.50e+11
1.4960e+11  0.0000e+00  0.0000e+00  2.9800e+04  5.9740e+24    earth.gif
2.2790e+11  0.0000e+00  0.0000e+00  2.4100e+04  6.4190e+23     mars.gif
5.7900e+10  0.0000e+00  0.0000e+00  4.7900e+04  3.3020e+23  mercury.gif
0.0000e+00  0.0000e+00  0.0000e+00  0.0000e+00  1.9890e+30      sun.gif
1.0820e+11  0.0000e+00  0.0000e+00  3.5000e+04  4.8690e+24    venus.gif
```
The input format is a text file that contains the information for a particular universe (in SI units). The first value is an integer `N` which represents the number of planets. The second value is a real number `R` which represents the radius of the universe, used to determine the scaling of the drawing window. Finally, there are `N` rows, and each row contains 6 values. The first two values are the x- and y-coordinates of the initial position; the next pair of values are the x- and y-components of the initial velocity; the fifth value is the mass; the last value is a String that is the name of an image file used to display the planets. Image files can be found in the `images` directory. The file above contains data for our own solar system (up to Mars).

### readRadius
Your first method is `readRadius`. Given a file name as a `String`, it should return a double corresponding to the radius of the universe in that file, e.g. `readRadius("./data/planets.txt")` should return 2.50e+11.

To help you understand the `In` class, we’ve provided a few examples for you in the `examples` folder given in the skeleton. The first one is called `BasicInDemo.java`. Take a look at the code, and its input file, `BasicInDemo_input_file.txt`. This program should output: `The file contained 5, 9.0, ketchup, brass, and 5.0`.

There’s a slightly more complicated example called `InDemo.java`, which you can also find in the examples folder. While this demo does not perfectly match what you’ll be doing in this project, every method that you need is somewhere in this file. You’re also welcome to search the web for other examples (though it might be tricky to find since the class name `In` is such a common English word).

NOTE: Do not use System.exit(0) in your code, despite the example using it. This will break the autograder, and you will not obtain a score.

Alternately, you can consult the full documentation for the In class, though it can be found a bit intimidating.

We encourage you (and your partner, if applicable) to do your best to figure out this part of the assignment on your own. In the long run, you’ll need to gain the skills to independently figure out this sort of thing. However, if you start getting frustrated, don’t hesitate to ask for help!

You can test this method using the supplied TestReadRadius.
```
javac NBody.java TestReadRadius.java
java TestReadRadius
```
### readBodies
Your next method is readBodies. Given a file name, it should return an array of `Body`s corresponding to the bodies in the file, e.g. `readBodies("./data/planets.txt")` should return an array of five planets. You will find the `readInt()`, `readDouble()`, and `readString()` methods in the `In` class to be useful.

You can test this method using the supplied TestReadBodies.
```
javac Body.java NBody.java TestReadBodies.java
java TestReadBodies
```

## Drawing the Initial Universe State (main)
Next, build the functionality to draw the universe in its starting position. You’ll do this in four steps. Because all code for this part of the assignment is in main, this part of the assignment will NOT have automated tests to check each little piece.

### Collecting All Needed Input
Create a `main` method in the NBody class. Write code so that your NBody class performs the following steps:

* Store the 0th and 1st command line arguments as doubles named `T` and `dt`. Hint: the arguments come in as Strings. You will have to Google around in order to learn how to convert the Strings to doubles!
* Store the 2nd command line argument as a String named `filename`.
* Read in the bodies and the universe radius from the file described by `filename` using your methods from earlier in this assignment.

### Drawing the Background
After your main method has read everything from the files, it’s time to get drawing. First, set the scale so that it matches the radius of the universe. Then draw the image `starfield.jpg` as the background. To do these, you’ll need to figure out how to use the StdDraw library.

See `StdDrawDemo.java` in the examples folder for a demonstration of StdDraw. This example, like `InDemo.java`, does not perfectly match what you’re doing.

In addition, make sure to check out the StdDraw section of this mini-tutorial, and if you’re feeling bold, the full StdDraw documentation. This will probably take some trial and error. This may seem slightly frustrating, but it’s good practice!

Note that, you may notice that putting `starfield.jpg` as a parameter into `StdDraw.picture()` results in a blank screen. This is because our `starfield.jpg` is inside the `images` folder. ** Thus, you will need to use the full relative path from the proj0 directory**, i.e. `images/starfield.jpg` in order to get your image. This applies to any other images you may use in the future.

### Drawing One Body
Next, we’ll want a Body, such as a planet, to be able to draw itself at its appropriate position. To do this, take a brief detour back to the Body.java file. Add one last method to the Body class, `draw`, that uses the StdDraw API mentioned above to draw the Body’s image at the Body’s position. The `draw` method should return nothing and take in no parameters.

### Drawing More than One Body
Return to the main method in NBody.java and use the `draw` method you just wrote to draw each one of the bodies in the `Body`s array you created. Be sure to do this after drawing the `starfield.jpg` file so that the planets don’t get covered up by the background.

Test that your main method works by compiling:
```
javac NBody.java
```
And running the following command:
```
java NBody 157788000.0 25000.0 data/planets.txt
```
You should see the sun and four planets sitting motionless. You are almost done.

## Creating an Animation
Everything you’ve done so far is leading up to this moment. With only a bit more code, we’ll get something very cool.

To create our simulation, we will discretize time (please do not mention this to Stephen Hawking). The idea is that at every discrete interval, we will be doing our calculations and once we have done our calculations for that time step, we will then update the values of our `Body`s and then redraw the universe.

Finish your `main` method by adding the following:

* Enable double buffering by calling `enableDoubleBuffering()`. This is a graphics technique to prevent flickering in the animation. This should be just a single method call, so you shouldn’t do anything complicated here. You can see an example in `StdDrawDemo.java`. Here’s the official documentation that explains it in a few sentences. You don’t have to understand this for CS61B. Just know that if you don’t call this function, any attempt at smooth animation will look bad and flickery (remove it and see what happens!).
   * When double buffering is enabled by calling `enableDoubleBuffering()`, all drawing takes place on the offscreen canvas. The offscreen canvas is not displayed. Only when you call `show()` does your drawing get copied from the offscreen canvas to the onscreen canvas, where it is displayed in the standard drawing window. You can think of double buffering as collecting all of the lines, points, shapes, and text that you tell it to draw, and then drawing them all simultaneously, upon request.
* Create a variable that represents time. Set it to 0. Set up a loop to loop until this time variable reaches (and includes) the T from above.
* For each time through the loop, do the following:
   * Create an `xForces` array and `yForces` array.
   * Calculate the net x and y forces for each Body, storing these in the `xForces` and `yForces` arrays respectively.
   * After calculating the net forces for every Body, call `update` on each of the `Body`s. This will update each body’s position, velocity, and acceleration.
   * Draw the background image.
   * Draw all of the `Body`s.
   * Show the offscreen buffer (see the `show` method of StdDraw).
   * Pause the animation for 10 milliseconds (see the `pause` method of StdDraw). You may need to tweak this on your computer.
   * Increase your time variable by `dt`.
**Important**: For each time through the main loop, do not make any calls to `update` until all forces have been calculated and safely stored in `xForces` and `yForces`. For example, don’t call bodies[0].update() until after the entire xForces and yForces arrays are done! The difference is subtle, but the autograder will be upset if you call `bodies[0].update` before you calculate `xForces[1]` and `yForces[1]`.

Compile and test your program:
```
javac NBody.java
java NBody 157788000.0 25000.0 data/planets.txt
```

## Printing the Universe
When the simulation is over, i.e. when you’ve reached time `T`, you should print out the final state of the universe in the same format as the input, e.g.:
```
5
2.50e11
1.4925e+11 -1.0467e+10  2.0872e+03  2.9723e+04  5.9740e+24    earth.gif
-1.1055e+11 -1.9868e+11  2.1060e+04 -1.1827e+04  6.4190e+23    mars.gif
-1.1708e+10 -5.7384e+10  4.6276e+04 -9.9541e+03  3.3020e+23 mercury.gif
2.1709e+05  3.0029e+07  4.5087e-02  5.1823e-02  1.9890e+30      sun.gif
6.9283e+10  8.2658e+10 -2.6894e+04  2.2585e+04  4.8690e+24    venus.gif
```
You are welcome to try to figure this out on your own, but if you’d prefer not to, the solution is right below:
```
StdOut.printf("%d\n", bodies.length);
StdOut.printf("%.2e\n", radius);
for (int i = 0; i < bodies.length; i++) {
    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
}
```
Here, `bodies` is our filler variable name for reading in the bodies, as per the third bullet under Collecting All Needed Input. You may have a different variable name.

This isn’t all that exciting (which is why we’ve provided a solution), but we’ll need this method to work correctly to autograde your assignment.
Make sure to also try out some of the other simulations, which can all be found in the data directory. Some of them are very cool.
