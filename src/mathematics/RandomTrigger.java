package mathematics;

import sprites.Updatable;

public class RandomTrigger implements Updatable {

    private int minTimerPeriod;
    private int maxTimerPeriod;
    private int timerPeriod;
    private boolean trigger = false;

    public RandomTrigger(int minTimerPeriod, int maxTimerPeriod) {
        this.minTimerPeriod = minTimerPeriod;
        this.maxTimerPeriod = maxTimerPeriod;
        timerPeriod = Mathematics.getRandom(minTimerPeriod, maxTimerPeriod);
    }

    public boolean isTriggered(){
        return trigger;
    }

    @Override
    public void update() {
        trigger = false;
        if (--timerPeriod == 0){
            timerPeriod = Mathematics.getRandom(minTimerPeriod, maxTimerPeriod);
            trigger = true;
        }
    }
}
