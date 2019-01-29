package sprites;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import main.Main;

public class Star extends Sprite {

    private static final double VELOCITY = 3;
    private static final int CHANGE_DIRECTION_PERIOD = 50;

    public static double getRandom(double lower, double upper){ // TODO replace to other class
        return Math.random() * (upper - lower) + lower;
    }
    public static int getRandom(int lower, int upper){
        return (int)(Math.random() * (upper - lower) + lower);
    }

    private double horizontalVelocity = getRandom(-Star.VELOCITY, Star.VELOCITY);
    private double verticalVelocity = getRandom(-Star.VELOCITY, Star.VELOCITY);
    private int directionTimer = CHANGE_DIRECTION_PERIOD;

    public Star(double x, double y) {
        // TODO possibility for different sizes
        Polygon body = new Polygon();
        body.getPoints().addAll(new Double[]{
                0.0, -50.0,
                15.0, -15.0,
                50.0, 0.0,
                15.0, 15.0,
                0.0, 50.0,
                -15.0, 15.0,
                -50.0, 0.0,
                -15.0, -15.0
        });
        body.setTranslateX(x);
        body.setTranslateY(y);
        body.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        this.getChildren().add(body);
    }

    @Override
    public void update() {
        // TODO add star rotation
        setTranslateX(getTranslateX() + horizontalVelocity);
        setTranslateY(getTranslateY() + verticalVelocity);
        if(--directionTimer == 0){
            directionTimer = CHANGE_DIRECTION_PERIOD;
            horizontalVelocity = getRandom(-Star.VELOCITY, Star.VELOCITY);
            verticalVelocity = getRandom(-Star.VELOCITY, Star.VELOCITY);
        }
    }

    @Override
    public double getZLevel() {
        return Main.Z_LEVEL_2;
    }
}
