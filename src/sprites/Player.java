package sprites;

import java.util.LinkedList;
import java.util.List;

import cameras.Camera;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import main.Main;

public class Player extends Sprite implements EventHandler<KeyEvent> {
    
    private static enum States {LEFT, RIGHT, STALL};
    private static final double PLAYER_VELOCITY = 10;
    
    private List<Shot> shots = new LinkedList<>();
    
    private double velocity = 0;
    private States state = States.STALL;
    
    private Rectangle body;
    private Rectangle gun;

    private Camera camera = null;

    public Player() {

        body = new Rectangle(0, 0, 0, 0);
        body.setTranslateX(-25);
        body.setFill(Color.SKYBLUE);
        gun = new Rectangle(0, 0, 0, 0);
        gun.setTranslateX(-3);
        gun.setTranslateY(-10);
        gun.setFill(Color.SKYBLUE);
        getChildren().addAll(body, gun);

        Ellipse e = new Ellipse(25, 30, 30, 20);
        Ellipse e1 = new Ellipse(25, 40, 30, 15);
        Shape s = Shape.subtract(e, e1);


        Ellipse e3 = new Ellipse(25, 10, 15, 30);
        Rectangle r = new Rectangle(0, 20, 40, 40);
        Shape s1 = Shape.subtract(e3, r);

        Ellipse e4 = new Ellipse(25, 10, 5, 15);
        Rectangle r1 = new Rectangle(0, 10, 40, 40);
        Shape s2 = Shape.subtract(e4, r1);

        s2.setFill(Color.BLACK);
        s1.setFill(Color.SKYBLUE);
        s.setFill(Color.SKYBLUE);

        getChildren().addAll(s,s1,s2);
    }
    
    private void setVelocity() {
        switch (state) {
            case STALL:
                velocity = 0;
                break;
            case RIGHT:
                velocity = PLAYER_VELOCITY;
                break;
            case LEFT:
                velocity = - PLAYER_VELOCITY;
                break;
            default:
                break;
        }
    }
    
    public List<Shot> getShots() {
        return shots;
    }
    
    public void setShots(List<Shot> s) {
        shots = s;
    }
    
    private void makeShot() {
        Shot shot = new Shot();
        shot.setTranslateX(getTranslateX());
        shot.setTranslateY(getTranslateY() - 10);
        shots.add(shot);
    }
    
    @Override
    public void update() {
        setTranslateX(getTranslateX() + velocity);
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.RIGHT && event.getEventType() == KeyEvent.KEY_PRESSED) {
            state = States.RIGHT;
            setVelocity();
        } else if (event.getCode() == KeyCode.LEFT && event.getEventType() == KeyEvent.KEY_PRESSED) {
            state = States.LEFT;
            setVelocity();
        } else if (event.getCode() == KeyCode.RIGHT && event.getEventType() == KeyEvent.KEY_RELEASED) {
            state = States.STALL;
            setVelocity();
        } else if (event.getCode() == KeyCode.LEFT && event.getEventType() == KeyEvent.KEY_RELEASED) {
            state = States.STALL;
            setVelocity();
        } else if (event.getCode() == KeyCode.SPACE && event.getEventType() == KeyEvent.KEY_PRESSED) {
            makeShot();
        } else if (event.getCode() == KeyCode.DIGIT1 && event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (camera != null)
                camera.changeCamera(false);
        } else if (event.getCode() == KeyCode.DIGIT2 && event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (camera != null)
                camera.changeCamera(true);
        }
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Camera getCamera() {
        return camera;
    }
}
