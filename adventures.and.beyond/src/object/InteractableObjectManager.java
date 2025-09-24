package object;

import entity.Player;
import main.GamePanel;

import java.awt.*;

import static helper.Constant.TILE_SIZE;

public class InteractableObjectManager {
    private final GamePanel gamePanel;
    private final InteractableObject[] interactableObjects;

    public InteractableObjectManager(GamePanel gamePanel) {
        this.gamePanel= gamePanel;
        this.interactableObjects = initializeInteractableObjects();
    }
    private InteractableObject[] initializeInteractableObjects() {
        InteractableObject key = new KeyObject(false,24*TILE_SIZE,22*TILE_SIZE,"KEY_07");

       return new InteractableObject[]{key};
    }

    public void drawInteractiveObjects(Graphics2D graphics2D){
        Player player = gamePanel.getPlayer();

        for (int currentIndex=0;currentIndex<=this.interactableObjects.length-1;currentIndex++){
            InteractableObject obj = this.interactableObjects[currentIndex];
            obj.draw(graphics2D,player);
        }
    }



}
