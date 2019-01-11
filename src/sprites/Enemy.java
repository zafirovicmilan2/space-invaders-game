package sprites;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Enemy extends Sprite {
    
    Rectangle body;
    
    public Enemy() {
        body = new Rectangle(0, 0, 50, 40);
        
        body.setFill(Color.YELLOW);
        getChildren().addAll(body);
    }
    
    @Override
    public void update() {
        
    }
    
}
