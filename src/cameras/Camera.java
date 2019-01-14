package cameras;

import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import main.Main;
import sprites.Player;
import sprites.Sprite;

public class Camera extends Sprite {

    private Player player;
    private PerspectiveCamera defaultCamera;
    private PerspectiveCamera playerCamera;
    private Scene scene;
    private boolean playerCameraON = false;

    public Camera(Player player) {

        this.player = player;
        defaultCamera = new PerspectiveCamera();
        playerCamera = new PerspectiveCamera();
        getTransforms().clear();
    }

    public void setScene(Scene scene){
        this.scene = scene;
        scene.setCamera(defaultCamera);
    }

    public void changeCamera(boolean playerOrDefault){
        playerCameraON = playerOrDefault;
        if (playerOrDefault)
            scene.setCamera(playerCamera);
        else
            scene.setCamera(defaultCamera);
    }

    public boolean isPlayerCameraON(){
        return playerCameraON;
    }

    @Override
    public void update() {
        playerCamera.setTranslateX(player.getTranslateX() - Main.WINDOW_WIDTH/2);
    }
}
