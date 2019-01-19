package sprites;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import main.Main;

public class Background extends Sprite {

    private Player player = null;
    
    public Background(int width, int height) {

        Rectangle background = new Rectangle(0, 0, width + 10, height + 10);
        Stop[] points = new Stop[]{new Stop(0,Color.BLACK), new Stop(1,Color.DARKBLUE)};
        LinearGradient lg = new LinearGradient(0.5,0.0,0.5,1.0,true, CycleMethod.NO_CYCLE, points);
        background.setFill(lg);

        getChildren().addAll(background);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void update() {
        if (player.getCamera().isPlayerCameraON()) {
            setTranslateX(player.getTranslateX() - Main.WINDOW_WIDTH / 2);
            setTranslateY(player.getTranslateY() - 9*Main.WINDOW_HEIGHT / 10);
        }
        else{
            setTranslateX(0);
            setTranslateY(0);
        }

    }
}
