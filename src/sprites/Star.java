package sprites;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import main.Main;
import mathematics.Mathematics;
import mathematics.Trigger;

public class Star extends Sprite {

    private static final double VELOCITY = 3;
    private static final double ROTATION_ANGLE = 5.0;
    private static final int CHANGE_DIRECTION_PERIOD = 50;

    private static final double SCALE_VALUE_LOWER_BOUND = 0.5;
    private static final double SCALE_VALUE_UPPER_BOUND = 2.0;

    private double horizontalVelocity = Mathematics.getRandom(-Star.VELOCITY, Star.VELOCITY);
    private double verticalVelocity = Mathematics.getRandom(-Star.VELOCITY, Star.VELOCITY);
    private double rotationAngle = Mathematics.getRandom(-Star.ROTATION_ANGLE, Star.ROTATION_ANGLE);
    private Trigger trigger = new Trigger(CHANGE_DIRECTION_PERIOD);

    public Star(double x, double y) {
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
        double scaleValue = Mathematics.getRandom(SCALE_VALUE_LOWER_BOUND, SCALE_VALUE_UPPER_BOUND);
        setScaleX(scaleValue);
        setScaleY(scaleValue);
    }

    @Override
    public void update() {
        trigger.update();
        setRotate(getRotate() + rotationAngle);
        setTranslateX(getTranslateX() + horizontalVelocity);
        setTranslateY(getTranslateY() + verticalVelocity);
        if(trigger.isTriggered()){
            horizontalVelocity = Mathematics.getRandom(-Star.VELOCITY, Star.VELOCITY);
            verticalVelocity = Mathematics.getRandom(-Star.VELOCITY, Star.VELOCITY);
            rotationAngle = Mathematics.getRandom(-Star.ROTATION_ANGLE, Star.ROTATION_ANGLE);
        }
    }

    @Override
    public double getZLevel() {
        return Main.Z_LEVEL_2;
    }
}
