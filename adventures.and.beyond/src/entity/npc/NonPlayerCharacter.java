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

    public Rectangle getSolidAreaWithWorldPositions() {
        Rectangle currentSolidArea = this.getSolidArea();
        int x = 0;
        int y = 0;
        boolean isMoving = !this.isIdle();

        if(isMoving) {
            int speed = this.getSpeed();
            switch (this.getDirection()) {
                case FACING_BACKWARD -> y = -speed;
                case FACING_FORWARD -> y = +speed;
                case FACING_LEFTWARD -> x = -speed;
                case FACING_RIGHTWARD -> x = +speed;
                default -> System.out.println("Custom error");
            }
        }
        return new Rectangle(this.getWorldPositionX() +x, this.getWorldPositionY() +y, currentSolidArea.width, currentSolidArea.height);
    }
}
