package sprites;


import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import main.Main;
import mathematics.Mathematics;
import mathematics.RandomTrigger;

public class EnemyShot extends Shot{
    private static final double SPEED_LOWER_BOUND = 2;
    private static final double SPEED_UPPER_BOUND = 7;
    private static final int CHANGE_DIRECTION_PERIOD_LOWER_BOUND = 20;
    private static final int CHANGE_DIRECTION_PERIOD_UPPER_BOUND = 100;


    private double angle;
    private RandomTrigger trigger = new RandomTrigger(EnemyShot.CHANGE_DIRECTION_PERIOD_LOWER_BOUND, EnemyShot.CHANGE_DIRECTION_PERIOD_UPPER_BOUND);
    private double velocity = Mathematics.getRandom(EnemyShot.SPEED_LOWER_BOUND, EnemyShot.SPEED_UPPER_BOUND);

    public EnemyShot(double angle) {
        this.angle = angle;
        Polygon body = new Polygon();
        body.getPoints().addAll(new Double[]{
                -7.0, 0.0,
                 7.0, 0.0,
                 0.0, 14.0
        });
        body.setFill(Color.PINK);
        getChildren().add(body);
    }

    public EnemyShot(){
        this(30);
    }

    @Override
    public void update() {
        if(trigger.isTriggered())
            angle = -angle;
        setTranslateX(getTranslateX() + Math.sin(Math.toRadians(angle)) * velocity);
        setTranslateY(getTranslateY() + Math.cos(Math.toRadians(angle)) * velocity);
        trigger.update();
    }

    @Override
    public double getZLevel() {
        return Main.Z_LEVEL_3;
    }
}
