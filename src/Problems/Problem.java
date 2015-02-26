package Problems;

/**
 * Created by tinkie101 on 2015/02/25.
 */
public abstract class Problem {
    final int numDimensions;

    //double[dimension][upper and lower bound value]
    final double[][] constraints;

    public Problem(int numDimensions) {
        this.numDimensions = numDimensions;
        constraints = new double[numDimensions][2];
    }

    public abstract double calculateFitness(double[] solutionVector) throws Exception;

    public abstract int getNumDimensions();

    public abstract double[][] getConstraints();
}
