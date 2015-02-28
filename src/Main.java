import Problems.Problem;
import Problems.Spherical;

/**
 * Created by tinkie101 on 2015/02/22.
 */
public class Main {

    public static void main(String[] args) {
        try {
            Problem problem = new Spherical(5);
            boolean minimisation = true;
            PSO pso = new GBest(minimisation, problem, 20);
            pso.runPSO();
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}
