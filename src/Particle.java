import Problems.Problem;

/**
 * Created by tinkie101 on 2015/02/22.
 */
public class Particle {

    private double[] position;
    private double[] velocity;
    private double[] pBestPosition;
    private double pBestValue;

    public Particle(Problem problem) throws Exception {
        int numDimensions = problem.getNumDimensions();
        double[][] constraints = problem.getConstraints();

        //second length must be 2 (upper and lower bounds)
        if (constraints.length != numDimensions && constraints[0].length != 2)
            throw new Exception("Invalid particle constraints. (Must be [numDimensions][2])");

        position = new double[numDimensions];
        for (int i = 0; i < numDimensions; i++) {
            position[i] = RandomGenerator.getInstance().getRandomRangedDoubleValue(constraints[i][0], constraints[i][1]);
        }

        velocity = new double[numDimensions];
        for (int i = 0; i < numDimensions; i++) {
            velocity[i] = 0.0d;
        }

        pBestPosition = position;
        pBestValue = problem.calculateFitness(position);
    }

    public void setPBestPosition(double[] pBest) {
        this.pBestPosition = pBest;
    }

    public void setPBestValue(double pBest) {
        this.pBestValue = pBest;
    }

    public double getPBestValue() {
        return pBestValue;
    }

    public double[] getPBestPosition() {
        return pBestPosition;
    }

    public double[] getPosition() {
        return position;
    }

    public void updateVelocity(double c1, double c2, double[] gBest) throws Exception {
        for (int i = 0; i < velocity.length; i++) {
            double r1 = RandomGenerator.getInstance().getRandomDoubleValue();
            double r2 = RandomGenerator.getInstance().getRandomDoubleValue();

//            System.out.println(gBest[i] + "; " + position[i]);
            velocity[i] = velocity[i] + c1 * r1 * (pBestPosition[i] - position[i]) + c2 * r2 * (gBest[i] - position[i]);
        }
    }

    private double getDistance(double[] from, double[] to) throws Exception {
        if (from.length != to.length)
            throw new Exception("From and To must have the same Dimension!");

        double result = 0.0d;
        for (int i = 0; i < from.length; i++) {
            result += Math.pow((from[i] - to[i]), 2);
        }

        result = Math.sqrt(result);

        return result;
    }

    public void updatePosition() throws Exception {
        position = vectorAdd(position, velocity);
    }

    private double[] vectorAdd(double[] first, double[] second) throws Exception {
        if (first.length != second.length)
            throw new Exception("Vector addition must have the same dimensions");

        double[] result = first;

        for (int i = 0; i < first.length; i++) {
            result[i] += second[i];
        }

        return result;
    }

    @Override
    public String toString() {
        String result = "[";


        int count = 0;
        for (double val : pBestPosition) {
            count++;
            if (count >= pBestPosition.length) {
                result = result + val;
            } else {
                result = result + val + ",";
            }
        }

        result = result + "]";
        return result;
    }
}
