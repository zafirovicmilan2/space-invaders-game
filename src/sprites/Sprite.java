package sprites;

import javafx.scene.Group;

public abstract class Sprite extends Group {

    public Sprite() {
        setTranslateZ(getTranslateZ() + getZLevel());
    }

    public abstract void update();

    public abstract double getZLevel();


}
