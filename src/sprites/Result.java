package sprites;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.Main;

public class Result extends Sprite {

    private Text resultLabel;
    private int result=0;

    public Result() {
        this(null, Color.YELLOWGREEN,null);
    }

    public Result(Color fill, Color stroke, Font font) {
        resultLabel = new Text();
        resultLabel.setStroke(stroke);
        resultLabel.setFont(font);
        resultLabel.setFill(fill);
        getChildren().add(resultLabel);
    }

    @Override
    public void update() {
        resultLabel.setText("RESULT: " + result);
    }

    @Override
    public double getZLevel() {
        return Main.Z_LEVEL_4;
    }

    public void addPoints(int points){
        result += points;
    }

    public int getResult() {
        return result;
    }
}
