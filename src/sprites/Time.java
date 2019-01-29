package sprites;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import main.Main;

public class Time extends Sprite {

    private Text timeLabel;
    private double time=0;
    private Player player;

    public Time(Player player) {
        this.player = player;
        timeLabel = new Text();
        timeLabel.setStroke(Color.RED);
        getChildren().add(timeLabel);
        setTranslateY(Main.WINDOW_HEIGHT/10);
    }

    @Override
    public void update() {
        if (player.getCamera().isPlayerCameraON()){
            setTranslateX(player.getTranslateX());
            setTranslateY(player.getTranslateY() - 8*Main.WINDOW_HEIGHT/10);
        }
        else {
            setTranslateX(Main.WINDOW_WIDTH/2);
            setTranslateY(Main.WINDOW_HEIGHT/10);
        }
        timeLabel.setText("TIME: " + (int)time);
        time += 1.0 / 60;
    }

    @Override
    public double getZLevel() {
        return Main.Z_LEVEL_4;
    }
}
