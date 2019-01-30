package sprites;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import main.Main;
import mathematics.Mathematics;

public class Coin extends Sprite {

    public static double VELOCITY = 5;

    private int value;
    private int angle;

    public Coin(int value, int angle) {
        // TODO add possibility for different sizes
        this.value = value;
        this.angle = angle;
        Circle c1 = new Circle(15.0, Color.GOLD);
        Circle c2 = new Circle(13.0, Color.BLACK);
        Circle c3 = new Circle(10.0, Color.GOLD);
        getChildren().addAll(c1, c2, c3);
    }

    public Coin() {
        this(Mathematics.getRandom(1,10));
    }

    public Coin(int value) {
        this(value, Math.random()<0.5 ? 15 : -15);
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
