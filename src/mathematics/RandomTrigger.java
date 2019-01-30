package mathematics;

/**
 * Triggers on a random period between minTimerPeriod and maxTimerPeriod
 */
public class RandomTrigger extends Trigger {

    private int minTimerPeriod;
    private int maxTimerPeriod;

    public RandomTrigger(int minTimerPeriod, int maxTimerPeriod) {
        super(Mathematics.getRandom(minTimerPeriod, maxTimerPeriod));
        this.minTimerPeriod = minTimerPeriod;
        this.maxTimerPeriod = maxTimerPeriod;
    }

    @Override
    protected int getNewTimePeriod(){
        return Mathematics.getRandom(minTimerPeriod, maxTimerPeriod);
    }
}
