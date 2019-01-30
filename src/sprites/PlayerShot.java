package sprites;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import main.Main;

public class PlayerShot extends Shot {

    private static final double SHOT_VELOCITY = -5;

    public PlayerShot() {
        Polygon body = new Polygon();
        body.getPoints().addAll(new Double[]{
                0.0, -8.0,
                5.0, -4.0,
                5.0,  4.0,
                0.0,  8.0,
                -5.0,  4.0,
                -5.0, -4.0
        });
        body.setFill(Color.RED);
        getChildren().addAll(body);
    }

    @Override
    public void update() {
        setTranslateY(getTranslateY() + SHOT_VELOCITY);
        setRotate(getRotate() + 10);
    }

    @Override
    public double getZLevel() {
        return Main.Z_LEVEL_3;
    }
}
