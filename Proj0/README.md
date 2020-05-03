# NBody Simulation


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
