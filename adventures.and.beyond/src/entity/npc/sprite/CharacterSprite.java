package entity.npc.sprite;

import directionEnum.Direction;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class CharacterSprite {
    private final String name;
    private final HashMap<Direction, BufferedImage[]> movingMotionAnimation;
    private final int maxFrameLimit;

    public CharacterSprite(String name, HashMap<Direction, BufferedImage[]> movingMotionAnimation, int maxFrameLimit) {
        this.name = name;
        this.movingMotionAnimation = movingMotionAnimation;
        this.maxFrameLimit = maxFrameLimit;
    }

    public String getName() {
        return this.name;
    }

    public int getMaxFrameLimit() {
        return maxFrameLimit;
    }

    public HashMap<Direction, BufferedImage[]> getMovingMotionAnimation() {
        return this.movingMotionAnimation;
    }

    public BufferedImage[] getMovingMotionAnimationByDirection(Direction directionKey){
        return this.getMovingMotionAnimation().get(directionKey);
    }
}
