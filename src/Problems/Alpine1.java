package Problems;

/**
 * Created by tinkie101 on 2016/03/06.
 */
public class Alpine1 extends Problem{
    private int Nx;

    //Hard code the problem function parameters
    public Alpine1(int Nx) {
        super(Nx, 10.0d);
        this.Nx = Nx;

        for (int i = 0; i < Nx; i++) {
            constraints[i][0] = -10.0d; //lower bounds
            constraints[i][1] = 10.0d;  //upper bounds
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
//                result += Math.pow(j, 2.0d) * Math.pow(x[j], 2.0d);
                result += Math.abs( x[j]*Math.sin(x[j]) + 0.1d*x[j] );
            }

            return result;
        } else
            throw new Exception("Invalid input variables for problem.");
    }
}
