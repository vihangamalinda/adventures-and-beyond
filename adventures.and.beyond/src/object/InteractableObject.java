package object;

import entity.Player;
import helper.DrawHelper;
import helper.Loader;

import java.awt.*;
import java.awt.image.BufferedImage;

import static helper.Constant.TILE_SIZE;

public abstract class InteractableObject {
    private BufferedImage image;
    private String name;
    private boolean onCollision;
    private  int worldPositionX;
    private int worldPositionY;

    public InteractableObject(String imagePath, String name, boolean onCollision, int worldPositionX, int worldPositionY) {
        this.image = Loader.getImage(imagePath);
        this.name = name;
        this.onCollision = onCollision;
        this.worldPositionX = worldPositionX;
        this.worldPositionY = worldPositionY;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnCollision() {
        return onCollision;
    }

    public void setOnCollision(boolean onCollision) {
        this.onCollision = onCollision;
    }

    public int getWorldPositionX() {
        return worldPositionX;
    }

    public void setWorldPositionX(int worldPositionX) {
        this.worldPositionX = worldPositionX;
    }

    public int getWorldPositionY() {
        return worldPositionY;
    }

    public void setWorldPositionY(int worldPositionY) {
        this.worldPositionY = worldPositionY;
    }

    public void draw(Graphics2D graphics2D,Player player){
       boolean isWithinWindowRange = DrawHelper.isWithinWindow(this.worldPositionX,this.worldPositionY,player);
       if(isWithinWindowRange){
           int windowPositionX =DrawHelper.getObjWindowPositionXRespectiveToPlayer(this.worldPositionX,player);
           int windowPositionY =DrawHelper.getObjWindowPositionYRespectiveToPlayer(this.worldPositionY,player);

           graphics2D.drawImage(this.image,windowPositionX,windowPositionY,TILE_SIZE,TILE_SIZE,null);
       }
    }
}
