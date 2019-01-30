package cameras;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import main.Main;
import sprites.Player;
import sprites.Updatable;

public class Camera extends Group implements Updatable {

    private Player player;
    private PerspectiveCamera playerCamera;
    private Scene scene;
    private boolean playerCameraON = false;

    public Camera(Player player) {

        this.player = player;
        playerCamera = new PerspectiveCamera();
        getTransforms().clear();
    }

    public void setScene(Scene scene){
        this.scene = scene;
        scene.setCamera(playerCamera);
    }

    public void changeCamera(boolean playerOrDefault){
        playerCameraON = playerOrDefault;
    }

    public boolean isPlayerCameraON(){
        return playerCameraON;
    }

    @Override
    public void update() {
        if (isPlayerCameraON()){
            playerCamera.setTranslateX(player.getTranslateX() - Main.WINDOW_WIDTH * 0.5);
            playerCamera.setTranslateY(player.getTranslateY() - Main.WINDOW_HEIGHT * 0.9);
        }else {
            playerCamera.setTranslateX(0);
            playerCamera.setTranslateY(0);
        }

    }
}
