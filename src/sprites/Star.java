package sprites;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import main.Main;

public class Star extends Sprite {

    private static final double VELOCITY = 3;
    private static final double ROTATION_ANGLE = 5.0;
    private static final int CHANGE_DIRECTION_PERIOD = 50;

    private static final double SCALE_VALUE_LOWER_BOUND = 0.5;
    private static final double SCALE_VALUE_UPPER_BOUND = 2.0;

    public static double getRandom(double lower, double upper){ // TODO replace to other class
        return Math.random() * (upper - lower) + lower;
    }
    public static int getRandom(int lower, int upper){
        return (int)(Math.random() * (upper - lower) + lower);
    }

    private double horizontalVelocity = getRandom(-Star.VELOCITY, Star.VELOCITY);
    private double verticalVelocity = getRandom(-Star.VELOCITY, Star.VELOCITY);
    private double rotationAngle = getRandom(-Star.ROTATION_ANGLE, Star.ROTATION_ANGLE);
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
        body.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        this.getChildren().add(body);
        setTranslateX(x);
        setTranslateY(y);
        double scaleValue = getRandom(SCALE_VALUE_LOWER_BOUND, SCALE_VALUE_UPPER_BOUND);
        setScaleX(scaleValue);
        setScaleY(scaleValue);
    }

    @Override
    public void update() {
        setRotate(getRotate() + rotationAngle);
        setTranslateX(getTranslateX() + horizontalVelocity);
        setTranslateY(getTranslateY() + verticalVelocity);
        if(--directionTimer == 0){
            directionTimer = CHANGE_DIRECTION_PERIOD;
            horizontalVelocity = getRandom(-Star.VELOCITY, Star.VELOCITY);
            verticalVelocity = getRandom(-Star.VELOCITY, Star.VELOCITY);
            rotationAngle = getRandom(-Star.ROTATION_ANGLE, Star.ROTATION_ANGLE);
        }
    }

    @Override
    public double getZLevel() {
        return Main.Z_LEVEL_2;
    }
}
