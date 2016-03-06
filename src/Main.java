import Problems.*;

/**
 * Created by tinkie101 on 2015/02/22.
 */
public class Main {

    public static final int NUM_PARTICLES = 40;
    public static final int NX = 20;
    public static final int NUM_RUNS = 50;

    public static void main(String[] args) {
        try {
            boolean minimisation = true;

//            Problem problem = new BasicProblem();
//            PSO pso = new GBest(minimisation, problem, NUM_PARTICLES);
//            pso.runPSO();

            Problem hyperEllipsoidProblem = new HyperEllipsoid(NX);
            StringBuilder hyperEllipsoid = new StringBuilder();
            StringBuilder hyperEllipsoidEpoch = new StringBuilder();

            Problem qingProblem = new Qing(NX);
            StringBuilder qing = new StringBuilder();
            StringBuilder qingEpoch = new StringBuilder();

            Problem alpine1Problem = new Alpine1(NX);
            StringBuilder alpine1 = new StringBuilder();
            StringBuilder alpine1Epoch = new StringBuilder();

            Problem quadricProblem = new Quadric(NX);
            StringBuilder quadric = new StringBuilder();
            StringBuilder quadricEpoch = new StringBuilder();

            Problem rastriginProblem = new Rastrigin(NX);
            StringBuilder rastrigin = new StringBuilder();
            StringBuilder rastriginEpoch = new StringBuilder();

//            Problem schwefelProblem = new Schwefel(NX);
//            StringBuilder schwefel = new StringBuilder();
//            StringBuilder schwefelEpoch = new StringBuilder();

            Problem sphericalProblem = new Spherical(NX);
            StringBuilder spherical = new StringBuilder();
            StringBuilder sphericalEpoch = new StringBuilder();

            for (int i = 0; i < NUM_RUNS; i++) {
                PSO pso = new GBest(minimisation, hyperEllipsoidProblem, NUM_PARTICLES);
                if( (i+1) < NUM_RUNS)
                    hyperEllipsoid.append(pso.runPSO() + ", ");
                else
                    hyperEllipsoid.append(pso.runPSO());
                hyperEllipsoidEpoch.append(pso.stringBuilder.toString());

                pso = new GBest(minimisation, qingProblem, NUM_PARTICLES);
                if( (i+1) < NUM_RUNS)
                    qing.append(pso.runPSO() + ", ");
                else
                    qing.append(pso.runPSO());
                qingEpoch.append(pso.stringBuilder.toString());

                pso = new GBest(minimisation, alpine1Problem, NUM_PARTICLES);
                if( (i+1) < NUM_RUNS)
                    alpine1.append(pso.runPSO() + ", ");
                else
                    alpine1.append(pso.runPSO());
                alpine1Epoch.append(pso.stringBuilder.toString());

                pso = new GBest(minimisation, quadricProblem, NUM_PARTICLES);
                if( (i+1) < NUM_RUNS)
                    quadric.append(pso.runPSO() + ", ");
                else
                    quadric.append(pso.runPSO());
                quadricEpoch.append(pso.stringBuilder.toString());

                pso = new GBest(minimisation, rastriginProblem, NUM_PARTICLES);
                if( (i+1) < NUM_RUNS)
                    rastrigin.append(pso.runPSO() + ", ");
                else
                    rastrigin.append(pso.runPSO());
                rastriginEpoch.append(pso.stringBuilder.toString());

//                pso = new GBest(minimisation, schwefelProblem, NUM_PARTICLES);
//                if( (i+1) < NUM_RUNS)
//                    schwefel.append(pso.runPSO() + ", ");
//                else
//                    schwefel.append(pso.runPSO());
//                schwefelEpoch.append(pso.stringBuilder.toString());

                pso = new GBest(minimisation, sphericalProblem, NUM_PARTICLES);
                if( (i+1) < NUM_RUNS)
                    spherical.append(pso.runPSO() + ", ");
                else
                    spherical.append(pso.runPSO());
                sphericalEpoch.append(pso.stringBuilder.toString());

                System.out.println("Run " + i + " completed");
            }

            FileHandler.writeFile("files/Alter_guide/" + hyperEllipsoidProblem.getClass().toString() + "-epoch.csv", hyperEllipsoidEpoch.toString());
            FileHandler.writeFile("files/Alter_guide/" + qingProblem.getClass().toString() + "-epoch.csv", qingEpoch.toString());
            FileHandler.writeFile("files/Alter_guide/" + alpine1Problem.getClass().toString() + "-epoch.csv", alpine1Epoch.toString());
            FileHandler.writeFile("files/Alter_guide/" + quadricProblem.getClass().toString() + "-epoch.csv", quadricEpoch.toString());
            FileHandler.writeFile("files/Alter_guide/" + rastriginProblem.getClass().toString() + "-epoch.csv", rastriginEpoch.toString());
//            FileHandler.writeFile("files/Alter_guide/" + schwefelProblem.getClass().toString() + "-epoch.csv", schwefelEpoch.toString());
            FileHandler.writeFile("files/Alter_guide/" + sphericalProblem.getClass().toString() + "-epoch.csv", sphericalEpoch.toString());

            FileHandler.writeFile("files/Alter_guide/" + hyperEllipsoidProblem.getClass().toString() + ".csv", hyperEllipsoid.toString());
            FileHandler.writeFile("files/Alter_guide/" + qingProblem.getClass().toString() + ".csv", qing.toString());
            FileHandler.writeFile("files/Alter_guide/" + alpine1Problem.getClass().toString() + ".csv", alpine1.toString());
            FileHandler.writeFile("files/Alter_guide/" + quadricProblem.getClass().toString() + ".csv", quadric.toString());
            FileHandler.writeFile("files/Alter_guide/" + rastriginProblem.getClass().toString() + ".csv", rastrigin.toString());
//            FileHandler.writeFile("files/Alter_guide/" + schwefelProblem.getClass().toString() + ".csv", schwefel.toString());
            FileHandler.writeFile("files/Alter_guide/" + sphericalProblem.getClass().toString() + ".csv", spherical.toString());

        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}
