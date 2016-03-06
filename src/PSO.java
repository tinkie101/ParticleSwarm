import Problems.Problem;

/**
 * Created by tinkie101 on 2015/02/22.
 */


abstract class PSO {
    int numParticles;
    Particle[] swarm;
    Problem problem;
    boolean minimisation;
    public StringBuilder stringBuilder = new StringBuilder();

    //  1) initialize n-dimensional swarm
    public PSO(boolean minimisation, Problem problem, int numParticles) throws Exception {
        int numDimensions = problem.getNumDimensions();
        Double[][] constraints = problem.getConstraints();

        if (numDimensions != constraints.length)
            throw new Exception("Invalid PSO Arguments");

        this.minimisation = minimisation;

        this.problem = problem;

        this.numParticles = numParticles;

        this.swarm = new Particle[numParticles];

        for (int i = 0; i < numParticles; i++) {
            Particle tempParticle = new Particle(problem);

            swarm[i] = tempParticle;
        }
    }

    //  2) run PSO
    public abstract double runPSO() throws Exception;
}
