package sprites;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Background extends Sprite {
    
    public Background(int width, int height) {
        Rectangle background = new Rectangle(0, 0, width + 10, height + 10);
        background.setFill(Color.BLACK);
        
        getChildren().addAll(background);
    }

    @Override
    public void update() {
        
    }
}
