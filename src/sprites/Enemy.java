package sprites;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Enemy extends Sprite {

    Rectangle body;
    Ellipse leftEye,rightEye;
    Circle leftPupil, rightPupil;

    Arc mouth;
    Polygon leftEar,rightEar;

    public Enemy(boolean leftOrRight) {
        body = new Rectangle(0, 0, 50, 40);
        body.setArcHeight(30);
        body.setArcWidth(30);

        leftEye = new Ellipse(15,15 ,8 ,8 );
        leftEye.setFill(Color.WHITE);
        leftEye.setStroke(Color.BLACK);
        leftPupil = new Circle(15,15,2);

        rightEye = new Ellipse(35,15 ,8 ,8 );
        rightEye.setFill(Color.WHITE);
        rightEye.setStroke(Color.BLACK);
        rightPupil = new Circle(35,15,2);

        mouth = new Arc(25,30,15 ,8 ,180 ,180 );


        rightEar = new Polygon(50.0,20.0,90.0,40.0,90.0,0.0);
        rightEar.setFill(Color.ANTIQUEWHITE);

        leftEar = new Polygon(0.0,20.0,-40.0,40.0,-40.0,0.0);
        leftEar.setFill(Color.ANTIQUEWHITE);


        mouth.setType(ArcType.ROUND);
        mouth.setFill(Color.BLACK);

        body.setFill(Color.YELLOW);
        getChildren().addAll(body,leftEye,rightEye,leftPupil,rightPupil,mouth,leftEar,rightEar);

        startWinking(leftOrRight);  // TODO remove these procedures, and do rotations and
        startEarRotation();         // winking from update method(manually)
    }

    private void startWinking(boolean leftOfRight){
        Ellipse eye;
        if (leftOfRight) eye = leftEye;
        else eye = rightEye;

        ScaleTransition winking = new ScaleTransition(Duration.millis(1000), eye);
        winking.setFromY(1);
        winking.setToY(0.3);
        winking.setCycleCount(Timeline.INDEFINITE);
        winking.setAutoReverse(true);
        winking.play();
    }

    private void startEarRotation(){
        Rotate rightEarRotation = new Rotate();
        rightEarRotation.setPivotX(50);
        rightEarRotation.setPivotY(20);

        rightEar.getTransforms().add(rightEarRotation);

        Timeline rightEarRotationProcess = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(rightEarRotation.angleProperty(), -20)),
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(rightEarRotation.angleProperty(), 20)));
        rightEarRotationProcess.setAutoReverse(true);
        rightEarRotationProcess.setCycleCount(Timeline.INDEFINITE);


        Rotate leftEarRotation = new Rotate();
        leftEarRotation.setPivotX(0);
        leftEarRotation.setPivotY(20);

        leftEar.getTransforms().add(leftEarRotation);
        Timeline leftEarRotationProcess = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(leftEarRotation.angleProperty(), 20)),
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(leftEarRotation.angleProperty(), -20)));
        leftEarRotationProcess.setAutoReverse(true);
        leftEarRotationProcess.setCycleCount(Timeline.INDEFINITE);

        leftEarRotationProcess.play();
        rightEarRotationProcess.play();
    }


    @Override
    public void update() {

    }

}
