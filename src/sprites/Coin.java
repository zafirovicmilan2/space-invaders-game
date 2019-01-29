package sprites;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.Main;

public class Coin extends Sprite {

    public static double VELOCITY = 5;

    private int value;
    private int angle;

    public Coin(double x, double y, int value, int angle) {
        this.value = value;
        this.angle = angle;
        Circle c1 = new Circle(x,y,15.0, Color.GOLD);
        Circle c2 = new Circle(x,y,13.0, Color.BLACK);
        Circle c3 = new Circle(x,y,10.0, Color.GOLD);
        getChildren().addAll(c1, c2, c3);
    }

    public Coin(double x, double y) {
        this(x, y, 5);
    }

    public Coin(double x, double y, int value) {
        this(x, y, value, Math.random()<0.5 ? 15 : -15);
    }

    public int getValue() {
        return value;
    }

    @Override
    public void update() {
        if(isVisible()){
            double xMove = Math.sin(Math.toRadians(angle)) * Coin.VELOCITY;
            double yMove = Math.cos(Math.toRadians(angle)) * Coin.VELOCITY;
            setTranslateX(getTranslateX() + xMove);
            setTranslateY(getTranslateY() + yMove);
        }
    }

    @Override
    public double getZLevel() {
        return Main.Z_LEVEL_3;
    }
}
