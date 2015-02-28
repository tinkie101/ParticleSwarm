import Problems.*;

/**
 * Created by tinkie101 on 2015/02/22.
 */
public class Main {

    public static void main(String[] args) {
        try {
            boolean minimisation = true;

            Problem problem = new BasicProblem();
            PSO pso = new GBest(minimisation, problem, 20);
            pso.runPSO();

            problem = new HyperEllipsoid(5);
            pso = new GBest(minimisation, problem, 20);
            pso.runPSO();

            problem = new Quadric(5);
            pso = new GBest(minimisation, problem, 20);
            pso.runPSO();

            problem = new Rastrigin(5);
            pso = new GBest(minimisation, problem, 20);
            pso.runPSO();

            problem = new Schwefel(5);
            pso = new GBest(minimisation, problem, 20);
            pso.runPSO();

            problem = new Spherical(5);
            pso = new GBest(minimisation, problem, 20);
            pso.runPSO();

        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}
