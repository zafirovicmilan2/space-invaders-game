package sprites;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import main.Main;

public class Background extends Sprite {

    public Background(int width, int height) {

        Rectangle background = new Rectangle(0, 0, width + 10, height + 10);
        Stop[] points = new Stop[]{new Stop(0,Color.BLACK), new Stop(1,Color.DARKBLUE)};
        LinearGradient lg = new LinearGradient(0.5,0.0,0.5,1.0,true, CycleMethod.NO_CYCLE, points);
        background.setFill(lg);

        getChildren().addAll(background);
    }

    @Override
    public void update() {
    }

    @Override
    public double getZLevel() {
        return Main.Z_LEVEL_1;
    }
}
