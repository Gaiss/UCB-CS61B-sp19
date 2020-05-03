public class Planet {
  //The constant g
  private final static double g = 6.67e-11;
  //Its current x position
  public double xxPos;
  //Its current y position
  public double yyPos;
  //Its current velocity in the x direction
  public double xxVel;
  //Its current velocity in the y direction
  public double yyVel;
  //Its mass
  public double mass;
  //The name of the file that corresponds to the image that depicts the planet
  public String imgFileName;

  //First constructor
  public Planet(double xP, double yP, double xV,
                double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }
  //Second constructor, a copy constructor
  public Planet(Planet p) {
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }

  /**
   * Return distance between current planet and one other planet.
   * @param other a Planet object, indicates the other planet
   * @return a double, indicates the distance between two planets
   */
  public double calcDistance(Planet other) {
    return Math.sqrt((xxPos - other.xxPos) * (xxPos - other.xxPos)
            + (yyPos - other.yyPos) * (yyPos - other.yyPos));
  }

  /**
   * Return the exerted force caused by the other planet to the current planet.
   * @param other a Planet object, indicates the other planet
   * @return a double, indicates the exerted force
   */
  public double calcForceExertedBy(Planet other) {
    return ((g * mass * other.mass) /((this.calcDistance(other)) * (this.calcDistance(other))));
  }

  /**
   * Return the exerted force in x direction caused by the other planet to the current planet.
   * @param other a Planet object, indicates the other planet
   * @return a double, indicates the exerted force in x direction
   */
  public double calcForceExertedByX(Planet other) {
    return ((other.xxPos - xxPos) * this.calcForceExertedBy(other) / this.calcDistance(other));
  }

  /**
   * Return the exerted force in y direction caused by the other planet to the current planet.
   * @param other a Planet object, indicates the other planet
   * @return a double, indicates the exerted force in y direction
   */
  public double calcForceExertedByY(Planet other) {
    return ((other.yyPos - yyPos) * this.calcForceExertedBy(other) / this.calcDistance(other));
  }

  /**
   * Return the sum of all exerted force in x direction caused
   * by the other planet to the current planet.
   * @param others a Planet array, indicates several planet elements
   * @return a double, indicates the sum of all exerted force
   */
  public double calcNetForceExertedByX(Planet[] others) {
    //Initialize a double to hold the sum of all exerted forces in x direction
    double netForceX = 0;
    //Check each planet
    for(Planet other: others) {
      //If the planet is not the same as the current one
      if(!other.equals(this)) {
        //Add its exerted force in x direction together
        netForceX += this.calcForceExertedByX(other);
      }
    }
    return netForceX;
  }

  /**
   * Return the sum of all exerted force in y direction caused
   * by the other planet to the current planet.
   * @param others a Planet array, indicates several planet elements
   * @return a double, indicates the sum of all exerted force
   */
  public double calcNetForceExertedByY(Planet[] others) {
    //Initialize a double to hold the sum of all exerted forces in y direction
    double netForceY = 0;
    //Check each planet
    for(Planet other: others) {
      //If the planet is not the same as the current one
      if(!other.equals(this)) {
        //Add its exerted force in y direction together
        netForceY += this.calcForceExertedByY(other);
      }
    }
    return netForceY;
  }

  /**
   * Change the current planet's position according to the added force in dt time.
   * @param dt a double, indicates the dt time
   * @param fx a double, indicates the added force in x direction
   * @param fy a double, indicates the added force in y direction
   */
  public void update(double dt, double fx, double fy) {
    //calculate the net acceleration
    double ax = fx / mass;
    double ay = fy / mass;
    //calculate the new velocity:
    xxVel += dt * ax;
    yyVel += dt * ay;
    //calculate the new position
    xxPos += dt * xxVel;
    yyPos += dt * yyVel;
  }

  /**
   *
   */
  public void draw(){
    StdDraw.picture(xxPos,yyPos,"images/" + imgFileName);
  }
}
