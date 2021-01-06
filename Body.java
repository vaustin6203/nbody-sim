/** The Body Class */
public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	String imgFileName;
	public static final double gForce = 6.67e-11;

	/** A Body constructor that takes in instance variables of 
	the Body class and initializes a Body object with these attributes. */
	public Body(double xP, double yP, double xV,
					double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = "images/" + img;
	}

	/** A Body contructor that takes in a Body object and intializes an 
	identical Body object. */
	public Body(Body b) {
		this(b.xxPos, b.yyPos, b.xxVel, b.yyVel, 
				b.mass, b.imgFileName);
	}

	/** A Body method that takes in a Body object and returns the 
	distance between this Body and the given Body. */
	public double calcDistance(Body b) {
		double dx = b.xxPos - this.xxPos;
		double dy = b.yyPos - this.yyPos;
		return java.lang.Math.sqrt(dx * dx + dy * dy);
	}

	/** A Body method that takes in a Body object and returns the
	net force exerted on this Body by the given Body. */
	public double calcForceExertedBy(Body b) {
		double r = this.calcDistance(b);
		return (gForce * this.mass * b.mass) / (r * r);
	}

	/** A Body method that takes in a Body object and returns the 
	x-force exerted on this Body by the given Body. */
	public double calcForceExertedByX(Body b) {
		double dx = b.xxPos - this.xxPos;
		return (this.calcForceExertedBy(b) * dx) / this.calcDistance(b);
	}

	/** A Body method that takes in a Body object and returns the 
	y-force exerted on this Body by the given Body. */
	public double calcForceExertedByY(Body b) {
		double dy = b.yyPos - this.yyPos;
		return (this.calcForceExertedBy(b) * dy) / this.calcDistance(b);
	}

	/** A Body method that takes in an array of Bodys and returns the 
	net x-force exerted by all the Bodys upon this Body. */
	public double calcNetForceExertedByX(Body[] allBodys) {
		int i = 0;
		double fNetX = 0;
		while (i < allBodys.length) {
			if (this.equals(allBodys[i])) {
				i += 1;
			} else {
				fNetX += this.calcForceExertedByX(allBodys[i]);
				i += 1;
			  }
		}
		return fNetX;
	}

	/** A Body method that takes in an array of Bodys and returns the 
	net y-force exerted by all the Bodys upon this Body. */
	public double calcNetForceExertedByY(Body[] allBodys) {
		int i = 0;
		double fNetY = 0;
		while (i < allBodys.length) {
			if (this.equals(allBodys[i])) {
				i += 1;
			} else { 
				fNetY += this.calcForceExertedByY(allBodys[i]);
				i += 1;
              }
		}
		return fNetY;
	}

	/** A Body method that takes in the amount of time, the net x-force, 
	and the net y-force being exerted on this Body and updates its 
	current x-velocity, y-velocity, x-position, and y-position. */
	public void update(double dt, double fX, double fY) {
		double xxAccel = fX / this.mass; 
		double yyAccel = fY / this.mass;
		this.xxVel = this.xxVel + dt * xxAccel;
		this.yyVel = this.yyVel + dt * yyAccel;
		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;
	}

	/** A Body nethod that allows this Body to draw itself at the 
	appropriate position. */
	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
	}
}