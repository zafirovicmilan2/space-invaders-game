package mathematics;

import sprites.Updatable;

/**
 * Triggers on each timerPeriod i.e. isTriggered returns true on timerPeriod, 2*timerPeriod, 3*timerPeriod,...
 */
public class Trigger implements Updatable {

    private int timerPeriod;
    private boolean trigger = false;

    public Trigger(int timerPeriod) {
        this.timerPeriod = timerPeriod;
    }

    public boolean isTriggered(){
        return trigger;
    }

    @Override
    public final void update() {
        trigger = false;
        if (--timerPeriod == 0){
            trigger = true;
            timerPeriod = getNewTimePeriod();
        }
    }

    protected int getNewTimePeriod(){
        return timerPeriod;
    }
}
