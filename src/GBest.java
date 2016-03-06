import Problems.Problem;

/**
 * Created by tinkie101 on 2015/02/22.
 */
public class GBest extends PSO {

    int gBest;
    double overallGBestVal;
    Double[] overallGBest;

    boolean first = true;
    int NUM_EPOCHS = 1000;

    public GBest(boolean minimisation, Problem problem, int numParticles) throws Exception {
        //create PSO
        super(minimisation, problem, numParticles);

        //get initial gBest
        for (int i = 0; i < numParticles; i++) {
            if (minimisation) {
                if (first || swarm[gBest].getPBestValue() > swarm[i].getPBestValue()) {
                    first = false;
                    gBest = i;
                }
            } else {
                if (first || swarm[gBest].getPBestValue() < swarm[i].getPBestValue()) {
                    first = false;
                    gBest = i;
                }
            }
        }

        overallGBestVal = swarm[gBest].getPBestValue();
        overallGBest = swarm[gBest].getPBestPosition();
    }


    @Override
    public double runPSO() throws Exception {
        Double[][] constraints = problem.getConstraints();

        //TODO Stopping condition
        int count = 0;
        while (count < NUM_EPOCHS) {
            //  1) set each particle's best pBest
            for (int i = 0; i < numParticles; i++) {
                Particle particle = swarm[i];
                Double[] position = particle.getPosition();
                double newFitness = problem.calculateFitness(position);
                double pBestFitness = particle.getPBestValue();

                if (minimisation) {
                    if (particle.isInSearchSpace() && pBestFitness > newFitness) {
                        particle.setPBestPosition(position);
                        particle.setPBestValue(newFitness);
                    }
                } else //maximization
                {
                    if (particle.isInSearchSpace() && pBestFitness < newFitness) {
                        particle.setPBestPosition(position);
                        particle.setPBestValue(newFitness);
                    }
                }
            }

            //  2) update gBest
            double gBestVal = swarm[gBest].getPBestValue();
            int gBestPos = gBest;

            for (int i = 0; i < numParticles; i++) {
                Particle particle = swarm[i];
                Double[] position = particle.getPosition();
                double newFitness = problem.calculateFitness(position);

                if (minimisation) {
                    if (swarm[gBest].getPBestValue() > newFitness) {
                        gBestPos = i;
                    }
                } else //maximization
                {
                    if (swarm[gBest].getPBestValue() < newFitness) {
                        gBestPos = i;
                    }
                }
            }
            gBest = gBestPos;

            // 2.1 update overall gBest
            if (minimisation) {
                if (swarm[gBest].getPBestValue() < overallGBestVal) {
                    overallGBestVal = swarm[gBest].getPBestValue();
                    overallGBest = swarm[gBest].getPBestPosition();
                }
            } else //maximization
            {
                if (swarm[gBest].getPBestValue() > overallGBestVal) {
                    overallGBestVal = swarm[gBest].getPBestValue();
                    overallGBest = swarm[gBest].getPBestPosition();
                }
            }


            for (int i = 0; i < numParticles; i++) {
                //  3) Update particle velocity
                swarm[i].updateVelocity(swarm[gBest].getPBestPosition(), overallGBest, count, NUM_EPOCHS);

                //  4) update particle position
                swarm[i].updatePosition();
            }
            count++;

            if(count == NUM_EPOCHS)
                stringBuilder.append(swarm[gBest].getPBestValue() + "\n");
            else
                stringBuilder.append(swarm[gBest].getPBestValue() + ", ");
        }
        return swarm[gBest].getPBestValue();
    }


}
