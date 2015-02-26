import Problems.Problem;

/**
 * Created by tinkie101 on 2015/02/22.
 */
public class GBest extends PSO {

    int gBest;
    boolean first = true;

    public GBest(boolean minimisation, Problem problem, int numParticles) throws Exception {
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
    }


    @Override
    public void runPSO() throws Exception {
        //TODO Stopping condition
        int count = 0;
        while (count < 1000) {
            //  1) set each particle's best pBest
            for (int i = 0; i < numParticles; i++) {
                Particle particle = swarm[i];
                double[] position = particle.getPosition();
                double newFitness = problem.calculateFitness(position);
                double pBestFitness = particle.getPBestValue();

                if (minimisation) {
                    if (pBestFitness > newFitness) {
                        particle.setPBestPosition(position);
                        particle.setPBestValue(newFitness);
                    }
                } else //maximization
                {
                    if (pBestFitness < newFitness) {
                        particle.setPBestPosition(position);
                        particle.setPBestValue(newFitness);
                    }
                }

                //  2) update gBest
                if (minimisation) {
                    if (swarm[gBest].getPBestValue() > newFitness) {
                        gBest = i;
                    }
                } else //maximization
                {
                    if (swarm[gBest].getPBestValue() < newFitness) {
                        gBest = i;
                    }
                }
            }

            for (int i = 0; i < numParticles; i++) {
                //  3) Update particle velocity
                swarm[i].updateVelocity(0.5d, 0.5d, swarm[gBest].getPBestPosition());

                //  4) update particle position
                swarm[i].updatePosition();
            }

            System.out.println("PSO run: " + count);
            System.out.println("Best solution vector: " + swarm[gBest].toString() + "; Fitness: " + swarm[gBest].getPBestValue());
            count++;
        }

        System.out.println("PSO Finished");
        System.out.println("Final Best solution vector: " + swarm[gBest].toString() + "; Fitness: " + swarm[gBest].getPBestValue());
    }


}
