package Problems;

/**
 * Created by tinkie101 on 2015/02/25.
 */
public class Spherical extends Problem {

    private int Nx;

    //Hard code the problem function parameters
    public Spherical(int Nx) {
        //Pass in the number of variables (dimensions) for this problem
        super(Nx);
        this.Nx = Nx;

        for (int i = 0; i < Nx; i++) {
            constraints[i][0] = -100; //lower bounds
            constraints[i][1] = 100;  //upper bounds
        }
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

            Double result = 0.0d;
            //Calculate the result of the function when you plug in the variables
            for (int i = 0; i < Nx; i++) {
                result += Math.pow(solutionVector[i], 2.0d);
            }

            return result;
        } else
            throw new Exception("Invalid input variables for problem.");
    }

    @Override
    public int getNumDimensions() {
        return numDimensions;
    }
}