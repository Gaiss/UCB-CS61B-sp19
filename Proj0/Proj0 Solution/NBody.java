public class NBody {
  /**
   * Return a double corresponding to the radius of the universe in that file
   * @param filename a String, indicates the string form of the filename
   * @return a double, indicates the radius of the universe
   */
  public static double readRadius(String filename) {
    //Create a new In class
    In in = new In(filename);
    //Read the first data, an integer indicates the number of planets
    int planetNum = in.readInt();
    //Read the second data, a double indicates the radius of the universe
    double radius = in.readDouble();
    return radius;
  }

  /**
   * Return an array of Planets corresponding to the planets in the file
   * @param filename a String, indicates the string form of the filename
   * @return an array of Planets
   */
  public static Planet[] readPlanets(String filename) {
    //Create a new In class
    In in = new In(filename);
    //Read the first data, an integer indicates the number of planets
    int planetNum = in.readInt();
    //Read the second data, a double indicates the radius of the universe
    double radius = in.readDouble();
    //Initialize a new Planet array
    Planet[] planets = new Planet[planetNum];
    //Use for loop to initialize each Planet object
    for(int i = 0; i < planetNum; i++) {
      //Read each data
      double xxPos = in.readDouble();
      double yyPos = in.readDouble();
      double xxVel = in.readDouble();
      double yyVel = in.readDouble();
      double mass = in.readDouble();
      String imgFileName = in.readString();
      //Initialize a Planet object according to data read before
      Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
      //Append each Planet object to the array
      planets[i] = planet;
    }
    return planets;
  }

  /**
   * Main function
   * @param args
   */
  public static void main(String[] args) {
    //Read first and second command line arguments as two double
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    //Read third command line argument as a String
    String filename = args[2];
    //Call readRadius and readPlanets methods to get radius and the array of Planet objects
    double radius = readRadius(filename);
    Planet[] planets = readPlanets(filename);
    //Draw planets
    StdDraw.setScale(-radius,radius);
    // Clears the drawing window//
    StdDraw.clear();
    //Prevent flickering in the animation
    StdDraw.enableDoubleBuffering();
    //Initialize a double time to zero
    double time = 0;
    //loop if time is smaller than T
    while (time < T) {
      //Initialize two double array to store net x and y forces for each planet
      double[] xForces = new double[planets.length];
      double[] yForces= new double[planets.length];
      //Calculate each planet's netforce in x and y directions
      for(int i = 0; i < planets.length; i++) {
        xForces[i] = planets[i].calcNetForceExertedByX(planets);
        yForces[i] = planets[i].calcNetForceExertedByY(planets);
      }
      //Update each planet's condition
      for(int j = 0; j < planets.length; j++) {
        planets[j].update(dt, xForces[j], yForces[j]);
      }
      //Draw updated planets
      StdDraw.picture(0,0,"images/starfield.jpg");
      //Use for loop to draw each planet
      for(Planet planet:planets) {
        planet.draw();
      }
      //Show the offscreen buffer
      StdDraw.show();
      //Pause the screen for 10 milliseconds
      StdDraw.pause(10);
      //Increase the T
      time += dt;
    }
    //Print out the whole universe
    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
              planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
              planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }
  }
}
