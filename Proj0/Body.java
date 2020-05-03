public class Body {
   //只读静态私有变量，final：只能使用,不能重载
   private final static double G = 6.67e-11;
   public double xxPos;
   public double yyPos;
   public double xxVel;
   public double yyVel;
   public double mass;
   public String imgFileName;
   
   public Body(double xP, double yP, double xV, double yV, double m, String img){
      xxPos = xP;
      yyPos = yP;
      xxVel = xV;
      yyVel = yV;
      mass = m;
      imgFileName = img;
   }
   
   public Body(Body b){
      xxPos = b.xxPos;
      yyPos = b.yyPos;
      xxVel = b.xxVel;
      yyVel = b.yyVel;
      mass = b.mass;
      imgFileName = b.imgFileName;
   }
   
   public double calcDistance(Body b){
      return Math.sqrt(Math.pow(xxPos - b.xxPos, 2) + Math.pow(yyPos - b.yyPos, 2));
   }
   
   public double calcForceExertedBy(Body b){
      return G * b.mass * mass / Math.pow(this.calcDistance(b), 2);
   }

   public double calcForceExertedByX(Body b){
      return this.calcForceExertedBy(b) * (b.xxPos - xxPos) / this.calcDistance(b);
   }

   public double calcForceExertedByY(Body b){
      return this.calcForceExertedBy(b) * (b.yyPos - yyPos) / this.calcDistance(b);
   }

   public double calcNetForceExertedByX(Body[] bodies){
      double NetForceX = 0.0;
      for(Body body : bodies){ // less verbose looping constructs
         if(!this.equals(body)){
            NetForceX += this.calcForceExertedByX(body);
         }
      }
      return NetForceX;
   }

   public double calcNetForceExertedByY(Body[] bodies) {
      double NetForceY = 0.0;
      for (Body body : bodies) { // less verbose looping constructs
         if (!this.equals(body)) {
            NetForceY += this.calcForceExertedByY(body);
         }
      }
      return NetForceY;
   }

   public void update(double dt, double fX, double fY){
      double ax = fX / mass;
      double ay = fY / mass;
      xxVel += dt * ax;
      yyVel += dt * ay;
      xxPos += dt * xxVel;
      yyPos += dt * yyVel;
   }

   public void draw(){
      StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
   }
}