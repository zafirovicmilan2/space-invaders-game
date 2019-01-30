package mathematics;

import sprites.Updatable;

public class Timer implements Updatable {

    private int minTimerPeriod;
    private int maxTimerPeriod;
    private int timerPeriod;
    private boolean trigger = false;

    public Timer(int minTimerPeriod, int maxTimerPeriod) {
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
