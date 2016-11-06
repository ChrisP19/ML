import java.util.Random;

public class NArms {
    private Random r = new Random();
    private double[] means;

    public NArms (int n) {
        means = new double[n];
        for (int i = 0; i < n; i++) {
            //mean = 0, variance = std dev = 1
            double sample = r.nextGaussian();
            means[i] = sample;
        }
    }
    public double getSample(int actionNum) {
        double mean = means[actionNum];
        //mean generated earlier, variance = std dev = 1
        double sample = r.nextGaussian() + mean;
        return sample;
    }


}
