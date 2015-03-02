import Problems.Problem;

/**
 * Created by tinkie101 on 2015/02/22.
 */
public class Particle {

    private Double[] position;
    private Double[] velocity;
    private Double[] pBestPosition;
    private double pBestValue;
    private int numDimensions;
    private Double[][] constraints;
    private double Vmax;
    private double c1, c2;

    public Particle(Problem problem) throws Exception {

        this.numDimensions = problem.getNumDimensions();
        this.constraints = problem.getConstraints();
        this.Vmax = problem.getVmax();
        this.c1 = problem.getC1();
        this.c2 = problem.getC2();

        //second length must be 2 (upper and lower bounds)
        if (constraints.length != numDimensions && constraints[0].length != 2)
            throw new Exception("Invalid particle constraints. (Must be [numDimensions][2])");

        position = new Double[numDimensions];
        pBestPosition = new Double[numDimensions];
        for (int i = 0; i < numDimensions; i++) {
            double tempVal = RandomGenerator.getInstance().getRandomRangedDoubleValue(constraints[i][0], constraints[i][1]);
            position[i] = tempVal;
            pBestPosition[i] = tempVal;
        }

        velocity = new Double[numDimensions];
        for (int i = 0; i < numDimensions; i++) {
            velocity[i] = 0.0d;
        }


        pBestValue = problem.calculateFitness(position);

    }

    public void setPBestPosition(Double[] pBest) {
        for (int i = 0; i < pBest.length; i++) {
            this.pBestPosition[i] = pBest[i];
        }
    }

    public void setPBestValue(double pBest) {
        this.pBestValue = pBest;
    }

    public double getPBestValue() {
        return pBestValue;
    }

    public Double[] getPBestPosition() {
        return pBestPosition;
    }

    public Double[] getPosition() {
        return position;
    }

    public void updateVelocity(Double[] gBest) throws Exception {
        for (int i = 0; i < velocity.length; i++) {
            double r1 = RandomGenerator.getInstance().getRandomDoubleValue();
            double r2 = RandomGenerator.getInstance().getRandomDoubleValue();

            velocity[i] = velocity[i] + c1 * r1 * (pBestPosition[i] - position[i]) + c2 * r2 * (gBest[i] - position[i]);

            //TODO This is what the research is all about!!
            if (Math.abs(velocity[i]) > Vmax) {
//                /***Clamp to xMax*/
//                velocity[i] = Vmax;
//
                /***Reset to 0.0d*/
                velocity[i] = 0.0d;
//
//                /**Reset to random value*/
//                velocity[i] = RandomGenerator.getInstance().getRandomRangedDoubleValue(-Vmax, Vmax);
//
//                /**Reset all to 0*/
//                for (int v = 0; v < velocity.length; v++) {
//                    velocity[v] = 0.0d;
//
//                }
//                break;
//
//                /**Reset all to random*/
//                for (int v = 0; v < velocity.length; v++) {
//                    velocity[v] = RandomGenerator.getInstance().getRandomRangedDoubleValue(-Vmax, Vmax);
//                }
//                break;
            }
        }
    }

    private double getDistance(Double[] from, Double[] to) throws Exception {
        if (from.length != to.length)
            throw new Exception("From and To must have the same Dimension!");

        double result = 0.0d;
        for (int i = 0; i < from.length; i++) {
            result += Math.pow((from[i] - to[i]), 2);
        }

        result = Math.sqrt(result);

        return result;
    }

    public boolean isInSearchSpace() {
        for (int i = 0; i < position.length; i++) {
            //Particle is outside search space
            if (position[i] < constraints[i][0] || position[i] > constraints[i][1]) {
                return false;
            }
        }
        return true;
    }

    public void updatePosition() throws Exception {
        position = vectorAdd(position, velocity);
    }

    private Double[] vectorAdd(Double[] first, Double[] second) throws Exception {
        if (first.length != second.length)
            throw new Exception("Vector addition must have the same dimensions");

        Double[] result = new Double[first.length];
        for (int i = 0; i < first.length; i++) {
            result[i] = first[i] + second[i];
        }

        return result;
    }

    @Override
    public String toString() {
        String result = "[";


        for (int i = 0; i < pBestPosition.length; i++) {
            //TODO
            if (i + 1 >= pBestPosition.length) {
                result = result + pBestPosition[i] + ";" + position[i];
            } else {
                result = result + pBestPosition[i] + ";" + position[i] + ",";
            }
        }

        result = result + "]";
        return result;
    }
}
