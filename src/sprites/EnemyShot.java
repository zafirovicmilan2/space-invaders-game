package sprites;


import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import main.Main;
import mathematics.Mathematics;

public class EnemyShot extends Shot{
    private static final double SPEED_LOWER_BOUND = 2;
    private static final double SPEED_UPPER_BOUND = 7;
    private static final int CHANGE_DIRECTION_PERIOD_LOWER_BOUND = 20;
    private static final int CHANGE_DIRECTION_PERIOD_UPPER_BOUND = 100;


    private double angle;
    private int directionTimer = Mathematics.getRandom(EnemyShot.CHANGE_DIRECTION_PERIOD_LOWER_BOUND, EnemyShot.CHANGE_DIRECTION_PERIOD_UPPER_BOUND);
    private double velocity = Mathematics.getRandom(EnemyShot.SPEED_LOWER_BOUND, EnemyShot.SPEED_UPPER_BOUND);

    public EnemyShot(double angle) {
        this.angle = angle;
        Polygon body = new Polygon();
        body.getPoints().addAll(new Double[]{
                -5.0, 0.0,
                 5.0, 0.0,
                 0.0, 10.0
        });
        body.setFill(Color.PINK);
        getChildren().add(body);
    }

    public EnemyShot(){
        this(30);
    }

    @Override
    public void update() {
        if(--directionTimer == 0){
            directionTimer = Mathematics.getRandom(EnemyShot.CHANGE_DIRECTION_PERIOD_LOWER_BOUND, EnemyShot.CHANGE_DIRECTION_PERIOD_UPPER_BOUND);
            angle = -angle;
        }
        double xMove = Math.sin(Math.toRadians(angle)) * velocity;
        double yMove = Math.cos(Math.toRadians(angle)) * velocity;
        setTranslateX(getTranslateX() + xMove);
        setTranslateY(getTranslateY() + yMove);
    }

    @Override
    public double getZLevel() {
        return Main.Z_LEVEL_3;
    }
}
