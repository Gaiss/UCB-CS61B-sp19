public class NBody {
    public static double readRadius(String filename){
        In i = new In(filename);
        int numBody =  i.readInt(); // read past the first data
        double radius  = i.readDouble();
        return radius;
    }

    public static Body[] readBodies(String filename){
        In i = new In(filename);
        int Num = i.readInt(); // first data: the number of planets
        double radius = i.readDouble(); // second data: the radius of the universe
        Body[] bodies = new Body[Num];
        for(int j = 0; j < Num; j++){
            double xxPos = i.readDouble();
            double yyPos = i.readDouble();
            double xxVel = i.readDouble();
            double yyVel = i.readDouble();
            double mass = i.readDouble();
            String imgFileName = i.readString();
            bodies[j] = new Body(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
        }
        return bodies;
    }

    public static void main(String[] args){
        // double T = Double.parseDouble(args[0]);
        // double dt = Double.parseDouble(args[1]);
        // String filename = args[2];
        double T = 157788000.0;
        double dt = 25000.0;
        String filename = "data/planets.txt";
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);
        double[] xForces = new double[bodies.length];
        double[] yForces = new double[bodies.length];
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.enableDoubleBuffering();
        StdAudio.play("audio/2001.mid"); // seems could not work...
        for(double t =0; t < T; t += dt){
            for(int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(int j = 0; j < bodies.length; j++){
                bodies[j].update(dt,xForces[j],yForces[j]);
                bodies[j].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (Body body: bodies) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    body.xxPos, body.yyPos, body.xxVel,
                    body.yyVel, body.mass, body.imgFileName);
        }
    }
}