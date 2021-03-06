package sprites;

import java.util.LinkedList;
import java.util.List;

import cameras.Camera;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import main.Main;

public class Player extends Shooter implements EventHandler<KeyEvent> {
    
    private static enum VerticalStates {LEFT, RIGHT, STALL};
    private static enum HorizontalStates {UP, DOWN, STALL};
    private static final double PLAYER_VELOCITY = 10;
    
    private double verticalVelocity = 0;
    private VerticalStates verticalState = VerticalStates.STALL;

    private double horizontalVelocity = 0;
    private HorizontalStates horizontalState = HorizontalStates.STALL;

    private Camera camera = null;

    public Player() {

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

        Rectangle leftTube = new Rectangle(10,20);
        Rectangle rightTube = new Rectangle(10,20);
        leftTube.setFill(Color.SKYBLUE);
        rightTube.setFill(Color.SKYBLUE);
        double middle = (getBoundsInLocal().getMinX() + getBoundsInLocal().getMaxX())/2;
        leftTube.setTranslateX(middle - 20);
        leftTube.setTranslateY(20);
        rightTube.setTranslateX(middle + 10);
        rightTube.setTranslateY(20);

        Rectangle leftFlame = new Rectangle(20,20);
        Rectangle rightFlame = new Rectangle(20,20);
        leftFlame.setTranslateX(middle - 25);
        rightFlame.setTranslateX(middle + 5);
        leftFlame.setTranslateY(40);
        rightFlame.setTranslateY(40);
        leftFlame.setFill(new ImagePattern(new Image("flame.png"))); //  TODO move flame.png to new folder (images)
        rightFlame.setFill(new ImagePattern(new Image("flame.png")));

        getChildren().addAll(leftFlame, rightFlame, leftTube, rightTube);
    }
    
    private void setVerticalVelocity() {
        switch (verticalState) {
            case STALL:
                verticalVelocity = 0;
                break;
            case RIGHT:
                verticalVelocity = PLAYER_VELOCITY;
                break;
            case LEFT:
                verticalVelocity = - PLAYER_VELOCITY;
                break;
            default:
                break;
        }
    }

    private void setHorizontalVelocity() {
        switch (horizontalState) {
            case STALL:
                horizontalVelocity = 0;
                break;
            case UP:
                horizontalVelocity = - PLAYER_VELOCITY;
                break;
            case DOWN:
                horizontalVelocity = PLAYER_VELOCITY;
                break;
            default:
                break;
        }
    }

    @Override
    public PlayerShot makeShot() {
        PlayerShot shot = new PlayerShot();
        shot.setTranslateX(getTranslateX() + getBoundsInLocal().getWidth()*0.5 - shot.getBoundsInLocal().getWidth()*0.5);
        shot.setTranslateY(getTranslateY() - 20);
        shots.add(shot);
        return shot;
    }
    
    @Override
    public void update() {
        setTranslateX(getTranslateX() + verticalVelocity);
        setTranslateY(getTranslateY() + horizontalVelocity);
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.RIGHT && event.getEventType() == KeyEvent.KEY_PRESSED) {
            verticalState = VerticalStates.RIGHT;
            setVerticalVelocity();
        } else if (event.getCode() == KeyCode.LEFT && event.getEventType() == KeyEvent.KEY_PRESSED) {
            verticalState = VerticalStates.LEFT;
            setVerticalVelocity();
        } else if (event.getCode() == KeyCode.RIGHT && event.getEventType() == KeyEvent.KEY_RELEASED) {
            verticalState = VerticalStates.STALL;
            setVerticalVelocity();
        } else if (event.getCode() == KeyCode.LEFT && event.getEventType() == KeyEvent.KEY_RELEASED) {
            verticalState = VerticalStates.STALL;
            setVerticalVelocity();
        } else if (event.getCode() == KeyCode.SPACE && event.getEventType() == KeyEvent.KEY_PRESSED) {
            makeShot();
        }if (event.getCode() == KeyCode.UP && event.getEventType() == KeyEvent.KEY_PRESSED) {
            horizontalState = HorizontalStates.UP;
            setHorizontalVelocity();
        } else if (event.getCode() == KeyCode.DOWN && event.getEventType() == KeyEvent.KEY_PRESSED) {
            horizontalState = HorizontalStates.DOWN;
            setHorizontalVelocity();
        } else if (event.getCode() == KeyCode.UP && event.getEventType() == KeyEvent.KEY_RELEASED) {
            horizontalState = HorizontalStates.STALL;
            setHorizontalVelocity();
        } else if (event.getCode() == KeyCode.DOWN && event.getEventType() == KeyEvent.KEY_RELEASED) {
            horizontalState = HorizontalStates.STALL;
            setHorizontalVelocity();
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

    @Override
    public double getZLevel() {
        return Main.Z_LEVEL_3;
    }
}
