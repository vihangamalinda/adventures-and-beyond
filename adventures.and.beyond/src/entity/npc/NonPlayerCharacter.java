package entity.npc;

import directionEnum.Direction;
import entity.Entity;

import java.awt.*;

public class NonPlayerCharacter extends Entity {

    private final String characterAnimationKey;
    public NonPlayerCharacter(int worldPositionX, int worldPositionY, int speed, Direction direction, boolean isIdle, Rectangle solidArea, boolean onCollision, String characterAnimationKey) {
        super(worldPositionX, worldPositionY, speed, direction, isIdle, solidArea, onCollision);
        this.characterAnimationKey = characterAnimationKey;
    }

    public String getCharacterAnimationKey() {
        return this.characterAnimationKey;
    }
}
