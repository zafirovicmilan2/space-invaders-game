package sprites;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Shot extends Sprite {

    private static final double SHOT_VELOCITY = -5;
    
    private Circle body;
    
    public Shot() {
        body = new Circle(0, 0, 3);
        body.setFill(Color.RED);
        getChildren().addAll(body);
    }
    
    @Override
    public void update() {
        setTranslateY(getTranslateY() + SHOT_VELOCITY);
    }
    
}
