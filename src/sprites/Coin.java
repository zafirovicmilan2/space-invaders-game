package sprites;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import main.Main;
import mathematics.Mathematics;

public class Coin extends Sprite {

    private static double VELOCITY = 5;
    private static double ANGLE_OPTION_1 = 15;
    private static double ANGLE_OPTION_2 = -15;

    private int value;
    private double angle;

    public Coin(int value, double angle) {
        this.value = value;
        this.angle = angle;
        Circle c1 = new Circle(15.0, Color.GOLD);
        Circle c2 = new Circle(13.0, Color.BLACK);
        Circle c3 = new Circle(10.0, Color.GOLD);
        getChildren().addAll(c1, c2, c3);
    }

    public Coin(int value) {
        this(value, Mathematics.getTrueOrFalse() ? ANGLE_OPTION_1 : ANGLE_OPTION_2);
    }

    public int getValue() {
        return value;
    }

    @Override
    public void update() {
        double xMove = Math.sin(Math.toRadians(angle)) * Coin.VELOCITY;
        double yMove = Math.cos(Math.toRadians(angle)) * Coin.VELOCITY;
        setTranslateX(getTranslateX() + xMove);
        setTranslateY(getTranslateY() + yMove);
    }

    @Override
    public double getZLevel() {
        return Main.Z_LEVEL_3;
    }
}
