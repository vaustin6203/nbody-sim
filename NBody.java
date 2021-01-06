/** The NBody Class */
public class NBody {

	/** A NBody method that takes a file name and returns 
	the radius of the universe in that file. */
	public static double readRadius(String file) {
		In in = new In(file);
		in.readInt();
		return in.readDouble(); 
	}

	/** A NBody method that takes a file name and returns 
	an array of Bodys in the file. */
	public static Body[] readBodies(String file) {
		In in = new In(file);
		int numBodies = in.readInt();
		in.readDouble();
		Body bodies[] = new Body[numBodies];
		for (int i = 0; i < numBodies; i += 1) {
			bodies[i] = new Body(in.readDouble(), in.readDouble(), 
								in.readDouble(), in.readDouble(), 
								in.readDouble(), in.readString());
		}
		return bodies; 
	}

	/** The NBody main method takes an array of Strings and simulates the
	universe in time (T), by the time increment (dt), from the universe 
	specifications stored in filename.
	@source 
	Used source to learn how to convert a string to a double. 
	https://stackoverflow.com/questions/5769669/convert-string-to-double-in-java */
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]); 
		double dt = Double.parseDouble(args[1]); 
		String filename = args[2];
		double rUniverse = NBody.readRadius(filename);
		Body nRows[] = NBody.readBodies(filename);
		int nBodys = nRows.length;
		StdDraw.enableDoubleBuffering();
		double t = 0.0;
		while (t <= T) {
			double xForces[] = new double[nBodys];
			double yForces[] = new double[nBodys];
			for (int a = 0; a < nBodys; a += 1) {
				xForces[a] = nRows[a].calcNetForceExertedByX(nRows);
				yForces[a] = nRows[a].calcNetForceExertedByY(nRows);
			}
			for (int b = 0; b < nBodys; b += 1) {
				nRows[b].update(dt, xForces[b], yForces[b]);
			}
			NBody.drawBackground(rUniverse);
			for (int c = 0; c < nBodys; c += 1) {
				nRows[c].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			t += dt;
		}
		NBody.printUniverse(nRows, rUniverse);
	}

	/** A NBody method that takes in a radius and draws 
	the background of the universe. */
	public static void drawBackground(double radius) {
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");
	}

	/** A NBody method that takes in an array of Bodys and a raduis 
	and prints the final state of the universe after time (T). */
	public static void printUniverse(Body[] bodies, double radius) {
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  		  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                          bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
	}
}