package sprites;


import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class EnemyShot extends Sprite{
    private static final double SPEED_LOWER_BOUND = 2;
    private static final double SPEED_UPPER_BOUND = 7;
    private static final int CHANGE_DIRECTION_PERIOD_LOWER_BOUND = 20;
    private static final int CHANGE_DIRECTION_PERIOD_UPPER_BOUND = 100;


    private double angle;
    private Polygon body;
    private int directionTimer = Star.getRandom(EnemyShot.CHANGE_DIRECTION_PERIOD_LOWER_BOUND, EnemyShot.CHANGE_DIRECTION_PERIOD_UPPER_BOUND);
    private double velocity = Star.getRandom(EnemyShot.SPEED_LOWER_BOUND, EnemyShot.SPEED_UPPER_BOUND);

    public EnemyShot(double x, double y, double angle) {
        this.angle = angle;
        body = new Polygon();
        body.getPoints().addAll(new Double[]{
                -5.0, 0.0,
                 5.0, 0.0,
                 0.0, 10.0
        });
        body.setFill(Color.PINK);
        getChildren().add(body);
    }

    public EnemyShot(double x, double y){
        this(x, y, 30);
    }

    @Override
    public void update() {
        if(--directionTimer == 0){
            directionTimer = Star.getRandom(EnemyShot.CHANGE_DIRECTION_PERIOD_LOWER_BOUND, EnemyShot.CHANGE_DIRECTION_PERIOD_UPPER_BOUND);
            angle = -angle;
        }
        double xMove = Math.sin(Math.toRadians(angle)) * velocity;
        double yMove = Math.cos(Math.toRadians(angle)) * velocity;
        setTranslateX(getTranslateX() + xMove);
        setTranslateY(getTranslateY() + yMove);
    }
}
