package main;

import cameras.Camera;

import java.util.LinkedList;
import java.util.List;

import geometry.Geometry;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sprites.*;

public class Main extends Application {
    
    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 700;
    
    public static final int ENEMIES_IN_A_ROW = 6;
    public static final int ENEMIES_IN_A_COLUMN = 3;

    public static final int SIMULTANEOUS_STARS_NUM = 3;

    public static final int Z_LEVEL_1 = -1; // used for background
    public static final int Z_LEVEL_2 = -2; // used for stars
    public static final int Z_LEVEL_3 = -3; // used for player, shot, enemy, enemy-shot, coin
    public static final int Z_LEVEL_4 = -4; // used for time, result and final result

    public static final double FINAL_RESULT_FONT = 100;

    public static final int POINTS_COIN = 5;

    public enum EnemyStates {LIVE, SHOT, DEAD};
    
    private Background background;
    private Player player;
    private List<Enemy> enemies;
    private List<Shot> playerShots;
    private List<Star> stars;
    private List<Coin> coins;
    
    private Camera camera;
    
    private Group root;
    private boolean theEnd = false;

    private Time time;
    private Result result;

    @Override
    public void start(Stage primaryStage) {
        enemies = new LinkedList<>();
        stars = new LinkedList<>();
        coins = new LinkedList<>();
        root = new Group();

        
        background = new Background(WINDOW_WIDTH, WINDOW_HEIGHT);
        root.getChildren().add(background);
        
        player = new Player();
        player.setTranslateX(WINDOW_WIDTH * 0.5);
        player.setTranslateY(WINDOW_HEIGHT * 0.9);

        camera = new Camera(player);
        background.setPlayer(player);
        player.setCamera(camera);
        camera.getChildren().add(player);
        
        for (int i = 0; i < ENEMIES_IN_A_COLUMN; i++) 
            for (int j = 0; j < ENEMIES_IN_A_ROW; j++) {
                Enemy enemy = new Enemy((i+j)%2 == 0);
                enemy.setTranslateX((j+1) * WINDOW_WIDTH / (ENEMIES_IN_A_ROW + 1));
                enemy.setTranslateY((i+1) * 100);
                camera.getChildren().add(enemy);
                enemies.add(enemy);
            }

        for (int i = 0; i < SIMULTANEOUS_STARS_NUM; i++) {
            Point2D randomPosition = Geometry.getRandomPoint(background.getBoundsInParent());
            Star star = new Star(randomPosition.getX(), randomPosition.getY());
            camera.getChildren().add(star);
            stars.add(star);
        }
        
        root.getChildren().add(camera);
        time = new Time(player);
        result = new Result(player);
        root.getChildren().addAll(time, result);

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, true);
        camera.setScene(scene);
        scene.setOnKeyPressed(player);
        scene.setOnKeyReleased(player);
        
        primaryStage.setTitle("Svemirci");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                update();
            }
        }.start();
    }

    public void update() {
        if (theEnd == false) {
            playerShots = player.getShots();
            
            for (int i = 0; i < playerShots.size(); i++) {
                Shot currentPlayerShot = playerShots.get(i);
                
                if (currentPlayerShot.getTranslateY() < 50) {
                    playerShots.remove(currentPlayerShot);
                    continue;
                }
                
                for (int j = 0; j < enemies.size(); j++) {
                    Enemy currentEnemy = enemies.get(j);
                    if (currentPlayerShot.getBoundsInParent().intersects(currentEnemy.getBoundsInParent())) {
                        playerShots.remove(currentPlayerShot);
                        if (currentEnemy.getState() == EnemyStates.LIVE)
                            currentEnemy.setState(EnemyStates.SHOT);
                        break;
                    }
                }
            }

            for (int i = 0; i < enemies.size(); i++) {
                Enemy currentEnemy = enemies.get(i);
                if (currentEnemy.getState() == EnemyStates.DEAD) {
                    enemies.remove(currentEnemy);
                    Coin coin = new Coin(POINTS_COIN);
                    coin.setTranslateX(currentEnemy.getBoundsInParent().getMinX() + currentEnemy.getBoundsInLocal().getWidth() * 0.5);
                    coin.setTranslateY(currentEnemy.getBoundsInParent().getMaxY() - currentEnemy.getBoundsInLocal().getHeight() * 0.5);
                    camera.getChildren().add(coin);
                    coins.add(coin);
                }
            }

            int currentStarsNum = 0;
            for (int i = 0; i < stars.size(); i++) {
                if(Geometry.containsXY(background.getBoundsInParent(), stars.get(i).getBoundsInParent())){
                    ++currentStarsNum;
                }
                // TODO remove stars that are far away from player
            }

            for (int i = 0; i < SIMULTANEOUS_STARS_NUM - currentStarsNum; i++) {
                Point2D randomPosition = Geometry.getRandomPoint(background.getBoundsInParent());
                Star star = new Star(randomPosition.getX(), randomPosition.getY());
                stars.add(star);
            }

            for (int i = 0; i < coins.size(); i++) {
                Coin currentCoin = coins.get(i);
                if(currentCoin.getBoundsInParent().intersects(player.getBoundsInParent())){
                    coins.remove(currentCoin);
                    result.addPoints(currentCoin.getValue());
                }
            }
            
            camera.getChildren().clear();
            camera.getChildren().add(player);
            camera.getChildren().addAll(stars);
            stars.forEach(e -> e.update());
            camera.getChildren().addAll(coins);
            coins.forEach(e -> e.update());

            if (enemies.isEmpty()) {
                theEnd = true;
                camera.getChildren().add(getFinalResult());
            } else {    
                camera.getChildren().addAll(playerShots);
                playerShots.forEach(e -> e.update());
                camera.getChildren().addAll(enemies);
                enemies.forEach(e -> e.update());
            }
            
            player.setShots(playerShots);
            player.update();
            camera.update();
            background.update();
            time.update();
            result.update();

        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    private Group getFinalResult(){
        Group res = new Group();
        Text finalResult = new Text();
        finalResult.setText(result.getText());
        finalResult.setFont(new Font(FINAL_RESULT_FONT));
        finalResult.setStroke(Color.RED);
        finalResult.setFill(Color.YELLOWGREEN);
        finalResult.setTranslateZ(Z_LEVEL_4);
        if(camera.isPlayerCameraON()){
            finalResult.setTranslateX(player.getTranslateX() - WINDOW_WIDTH * 0.2);
            finalResult.setTranslateY(player.getTranslateY() - Main.WINDOW_HEIGHT * 0.4);
        }else{
            finalResult.setTranslateX(WINDOW_WIDTH * 0.3);
            finalResult.setTranslateY(WINDOW_HEIGHT * 0.5);
        }
        res.getChildren().add(finalResult);
        return res;
    }
    
}
