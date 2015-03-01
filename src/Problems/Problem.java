package Problems;

/**
 * Created by tinkie101 on 2015/02/25.
 */
public abstract class Problem {
    protected final int numDimensions;

    //double[dimension][upper and lower bound value]
    protected final Double[][] constraints;

    protected final double c1 = 2.8;
    protected final double c2 = 1.3;
    protected final double Vmax;

    public Problem(int numDimensions, double Vmax) {
        this.numDimensions = numDimensions;
        constraints = new Double[numDimensions][2];
        this.Vmax = Vmax / 4;
    }

    //Abstract methods
    public abstract double calculateFitness(Double[] solutionVector) throws Exception;

    //Implemented Methods
    public Double[][] getConstraints() {
        return constraints;
    }

    public int getNumDimensions() {
        return numDimensions;
    }

    public double getVmax() {
        return Vmax;
    }

    public double getC1() {
        return c1;
    }

    public double getC2() {
        return c2;
    }
}
