package sprites;

import javafx.scene.Group;

public abstract class Sprite extends Group implements Updatable {

    public Sprite() {
        setTranslateZ(getTranslateZ() + getZLevel());
    }

    public abstract double getZLevel();


}
