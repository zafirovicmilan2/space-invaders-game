package sprites;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import main.Main;

public class Result extends Sprite {

    private Text resultLabel;
    private int result=0;
    private Player player;

    public Result(Player player) {
        this.player = player;
        resultLabel = new Text();
        resultLabel.setStroke(Color.YELLOWGREEN);
        getChildren().add(resultLabel);
    }

    @Override
    public void update() {
        if (player.getCamera().isPlayerCameraON()){
            setTranslateX(player.getTranslateX() + Main.WINDOW_WIDTH * 0.3);
            setTranslateY(player.getTranslateY() - Main.WINDOW_HEIGHT * 0.8);
        }
        else {
            setTranslateX(Main.WINDOW_WIDTH * 0.8);
            setTranslateY(Main.WINDOW_HEIGHT *0.1);
        }
        resultLabel.setText("RESULT: " + result);
    }

    @Override
    public double getZLevel() {
        return Main.Z_LEVEL_4;
    }

    public void addPoints(int points){
        result += points;
    }
}
