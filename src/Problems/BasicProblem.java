package Problems;

/**
 * Created by tinkie101 on 2015/02/25.
 */
public class BasicProblem extends Problem {

    //Hard code the problem function parameters
    public BasicProblem() {
        //Pass in the number of variables (dimensions) for this problem
        super(1);

        constraints[0][0] = 0;
        constraints[0][1] = 20;
    }

    @Override
    public double[][] getConstraints() {
        return constraints;
    }

    @Override
    public double calculateFitness(double[] solutionVector) throws Exception {
        if (solutionVector.length == numDimensions) {
            //map to solution values to more mathematically readable variables
            double x = solutionVector[0];

            //Calculate the result of the function when you plug in the variables
            Double result = 30.0d + (25.0d * x) - (4.0d * Math.pow(x, 2.0d)) + (0.15d * Math.pow(x, 3.0d));
            return result;
        } else
            throw new Exception("Invalid input variables for problem.");
    }

    @Override
    public int getNumDimensions() {
        return numDimensions;
    }
}
