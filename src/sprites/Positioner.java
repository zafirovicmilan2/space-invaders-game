package sprites;

import javafx.geometry.Point2D;

import java.util.LinkedList;
import java.util.List;

public class Positioner implements Updatable {

    private Player player;
    private List<Sprite> sprites = new LinkedList<>();
    private List<Point2D> positionsCameraON = new LinkedList<>();
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

    @Override
    public void update() {
        for (int i = 0; i < sprites.size(); i++) {
            Sprite currentSprite = sprites.get(i);
            if(player.getCamera().isPlayerCameraON()){
                currentSprite.setTranslateX(player.getTranslateX() + positionsCameraON.get(i).getX());
                currentSprite.setTranslateY(player.getTranslateX() + positionsCameraON.get(i).getY());
            }else{
                currentSprite.setTranslateX(positionsCameraOFF.get(i).getX());
                currentSprite.setTranslateY(positionsCameraOFF.get(i).getY());
            }
            currentSprite.update();
        }
    }
}
