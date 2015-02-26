import java.util.Random;

/**
 * Created by tinkie101 on 2015/02/26.
 */
public class RandomGenerator {
    private static RandomGenerator ourInstance = new RandomGenerator();

    public static RandomGenerator getInstance() {
        return ourInstance;
    }

    Random randomGenerator;

    private RandomGenerator() {
        randomGenerator = new Random(System.currentTimeMillis());
    }

    public float getRandomFloatValue() {
        return randomGenerator.nextFloat();
    }

    public double getRandomDoubleValue() {
        return randomGenerator.nextDouble();
    }

    public double getRandomRangedDoubleValue(double min, double max) {
        double result = min + (randomGenerator.nextDouble() * (max - min));
        return result;
    }
}
