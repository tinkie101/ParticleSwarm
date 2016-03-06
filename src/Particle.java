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
    private double c1, c2, w;

    public Particle(Problem problem) throws Exception {

        this.numDimensions = problem.getNumDimensions();
        this.constraints = problem.getConstraints();
        this.Vmax = problem.getVmax();
        this.c1 = problem.getC1();
        this.c2 = problem.getC2();
        this.w = problem.getW();

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

    public Double[] getRandomParticle(){
        Double[] result = new Double[numDimensions];

        for (int i = 0; i < numDimensions; i++) {
            double tempVal = RandomGenerator.getInstance().getRandomRangedDoubleValue(constraints[i][0], constraints[i][1]);
            result[i] = tempVal;
        }
        return result;
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
        Double[] result = new Double[pBestPosition.length];

        for (int i = 0; i < pBestPosition.length; i++) {
            result[i] = pBestPosition[i].doubleValue();
        }
        return result;
    }

    public Double[] getPosition() {
        return position;
    }

    public void updateVelocity(Double[] gBest, Double[] overallGBest, int numIteration, int maxIterations) throws Exception {
        //TODO Every update step, or just once?
        Double[] randomParticle = getRandomParticle();
        //double a = RandomGenerator.getInstance().getRandomDoubleValue(); TODO for each iteration

        for (int i = 0; i < velocity.length; i++) {
            double r1 = RandomGenerator.getInstance().getRandomDoubleValue();
            double r2 = RandomGenerator.getInstance().getRandomDoubleValue();

            //TODO Alternative Guide Selection Strategies for PSO
            double a = RandomGenerator.getInstance().getRandomDoubleValue();    //For each dimension
            double guide = averageGuide(gBest[i], overallGBest[i], a);
            double guide2 = randomGuide(randomParticle[i], overallGBest[i], a);

            velocity[i] = w*velocity[i] + c1 * r1 * (pBestPosition[i] - position[i]) + c2 * r2 * (guide - position[i]);

            //TODO Clamping
            if (Math.abs(velocity[i]) > Vmax) {
//                /***Clamp to xMax*/
//            if(velocity[i] < Math.abs(velocity[i]))
//                velocity[i] = -Vmax;
//            else
//                velocity[i] = Vmax;

//                /***Reset to 0.0d*/
//                velocity[i] = 0.0d;
//
//                /**Reset to random value*/
//                velocity[i] = RandomGenerator.getInstance().getRandomRangedDoubleValue(-Vmax, Vmax);
//
                /**Reset all to 0*/
                for (int v = 0; v < velocity.length; v++) {
                    velocity[v] = 0.0d;

                }
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

    private double averageGuide(double x_i, double y_i, double a){
        double result = 0.0d;

        result = a*x_i + (1.0d - a)*y_i;

        return result;
    }

    private double randomGuide(double x_r, double y_i, double a){
        double result = 0.0d;

        result = a*x_r + (1.0d - a)*y_i;

        return result;
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
                result = result + pBestPosition[i];
            } else {
                result = result + pBestPosition[i] + ",";
            }
        }

        result = result + "]";
        return result;
    }
}
