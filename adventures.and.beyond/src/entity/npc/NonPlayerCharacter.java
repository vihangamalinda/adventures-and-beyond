package entity.npc;

import directionEnum.Direction;
import entity.Entity;

import java.awt.*;

public class NonPlayerCharacter extends Entity {

    private final String characterAnimationKey;
    private boolean isOnCollisionWithPlayer;
    public NonPlayerCharacter(int worldPositionX, int worldPositionY, int speed, Direction direction, boolean isIdle, Rectangle solidArea, boolean onCollision, String characterAnimationKey) {
        super(worldPositionX, worldPositionY, speed, direction, isIdle, solidArea, onCollision);
        this.characterAnimationKey = characterAnimationKey;
        this.isOnCollisionWithPlayer =false;
    }

    public String getCharacterAnimationKey() {
        return this.characterAnimationKey;
    }

    protected boolean isOnCollisionWithPlayer() {
        return isOnCollisionWithPlayer;
    }


    protected void setOnCollisionWithPlayer(boolean onCollisionWithPlayer) {
        isOnCollisionWithPlayer = onCollisionWithPlayer;
    }


    public void associateWithPlayer(){
        this.setOnCollisionWithPlayer(true);
    }
    public void disassociateWithPlayer(){
        this.setOnCollisionWithPlayer(false);
    }

}
