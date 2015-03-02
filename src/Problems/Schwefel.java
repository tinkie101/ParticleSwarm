package Problems;

/**
 * Created by tinkie101 on 2015/02/25.
 */
public class Schwefel extends Problem {
    private int Nx;

    //Hard code the problem function parameters
    public Schwefel(int Nx) {
        super(Nx, 500.0d);
        this.Nx = Nx;

        for (int i = 0; i < Nx; i++) {
            constraints[i][0] = -500.0d; //lower bounds
            constraints[i][1] = 500.0d;  //upper bounds
        }
    }

    @Override
    public double calculateFitness(Double[] solutionVector) throws Exception {
        if (solutionVector.length == numDimensions) {
            //map to solution values to more mathematically readable variables
            Double[] x = solutionVector;

            Double result = 0.0d;
            //Calculate the result of the function when you plug in the variables
            for (int j = 0; j < Nx; j++) {
                result += x[j] * Math.sin(Math.sqrt(Math.abs(x[j])));
            }
            result += (418.9829d * Nx);

            return result;
        } else
            throw new Exception("Invalid input variables for problem.");
    }
}
