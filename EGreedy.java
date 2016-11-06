import java.lang.Math;

public class EGreedy {
    private static final int numTests = 2000;
    private static final int numPlays = 1000;
    private static final int numActions = 10;
    private double[] avgReward;

    public EGreedy(double epsilon) {
        avgReward = new double[numPlays];
        for (int j = 0; j < numTests; j++) {
            NArms bandit = new NArms(numActions);
            double[] rewards = new double[numActions];
            int[] chosen = new int[numActions];
            for (int i = 0; i < numPlays; i++) {
                int currAct = chooseAction(rewards, chosen, epsilon);
                double reward = bandit.getSample(currAct);
                chosen[currAct] = chosen[currAct] + 1;
                rewards[currAct] = addToAvg(reward, rewards[currAct], chosen[currAct]);
                avgReward[i] = addToAvg(avgReward[i], reward, j + 1);
            }
        }
    }
    public static double addToAvg(double newVal, double oldVal, int numTests) {
        double weightedOld = oldVal * (numTests - 1)/numTests;
        double weightedNew = newVal/numTests;
        return weightedOld + weightedNew;
    }
    /* use egreedy to return index of correct action */
    public static int chooseAction(double[] rewards, int[] chosen, double epsilon) {
        double r = Math.random();
        if (r < epsilon) {
            double ra = Math.random();
            ra = ra * rewards.length;
            return (int) ra;
        } else {
            double maxReward = 0;
            int currMax = 0;
            for (int i = 0; i < rewards.length; i++) {
                if (chosen[i] == 0) {
                    return i;
                } else {
                    if (rewards[i] > maxReward) {
                        maxReward = rewards[i];
                        currMax = i;
                    }
                }
            }
            return currMax;
        }
    }
    public double[] getAvgRewards() {
        return avgReward;
    }
    public static void main(String[] args) {
        EGreedy greed = new EGreedy(0);
        EGreedy explore = new EGreedy(0.1);
        double[] gRewards = greed.getAvgRewards();
        double[] eRewards = greed.getAvgRewards();
        double total = 0;
        for (int i = 0; i < gRewards.length; i++) {
            total += (eRewards[i] - gRewards[i]);
        }
        System.out.println("On average, exploring increased total reward by " + total);
    }
}
