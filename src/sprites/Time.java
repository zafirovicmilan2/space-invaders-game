package sprites;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import main.Main;

public class Time extends Sprite {

    private Text timeLabel;
    private double time=0;

    public Time() {
        timeLabel = new Text();
        timeLabel.setStroke(Color.RED);
        getChildren().add(timeLabel);
    }

    @Override
    public void update() {
        timeLabel.setText("TIME: " + (int)time);
        time += 1.0 / 60;
    }

    @Override
    public double getZLevel() {
        return Main.Z_LEVEL_4;
    }
}
