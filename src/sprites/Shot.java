package sprites;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Shot extends Sprite {

    private static final double SHOT_VELOCITY = -5;

    private Polygon body;

    public Shot() {
        body = new Polygon();
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
        // TODO add rotation
    }

}
