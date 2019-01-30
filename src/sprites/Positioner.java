package sprites;

import javafx.geometry.Point2D;

import java.util.LinkedList;
import java.util.List;

public class Positioner implements Updatable {

    private Player player;
    private List<Sprite> sprites = new LinkedList<>();
    private List<Point2D> positionsCameraON = new LinkedList<>(); // distance from player
    private List<Point2D> positionsCameraOFF = new LinkedList<>();

    public Positioner(Player player) {
        this.player = player;
    }

    public void addSprite(Sprite s, double xCameraON, double yCameraON, double xCameraOFF, double yCameraOFF){
        addSprite(s, new Point2D(xCameraON, yCameraON), new Point2D(xCameraOFF, yCameraOFF));
    }

    public void addSprite(Sprite s, Point2D positionCameraON, Point2D positionCameraOFF){
        sprites.add(s);
        positionsCameraON.add(positionCameraON);
        positionsCameraOFF.add(positionCameraOFF);
    }

    public void position(Sprite s, Point2D positionCameraON, Point2D positionCameraOFF){
        if(player.getCamera().isPlayerCameraON()){
            s.setTranslateX(player.getTranslateX() + positionCameraON.getX());
            s.setTranslateY(player.getTranslateY() + positionCameraON.getY());
        }else{
            s.setTranslateX(positionCameraOFF.getX());
            s.setTranslateY(positionCameraOFF.getY());
        }
    }

    @Override
    public void update() {
        for (int i = 0; i < sprites.size(); i++) {
            position(sprites.get(i), positionsCameraON.get(i), positionsCameraOFF.get(i));
            sprites.get(i).update();
        }
    }
}
