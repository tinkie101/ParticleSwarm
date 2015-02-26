import Problems.BasicProblem;
import Problems.Problem;

/**
 * Created by tinkie101 on 2015/02/22.
 */
public class Main {

    public static void main(String[] args) {

        try {
            Problem problem = new BasicProblem();
            boolean minimisation = false;
            PSO pso = new GBest(minimisation, problem, 20);
            pso.runPSO();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
