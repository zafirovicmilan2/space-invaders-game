package sprites;

import java.util.LinkedList;
import java.util.List;

public abstract class Shooter extends Sprite {

    protected List<Shot> shots = new LinkedList<>();

    public List<Shot> getShots() {
        return shots;
    }

    public void setShots(List<Shot> s) {
        shots = s;
    }

    public abstract void makeShot();
}
