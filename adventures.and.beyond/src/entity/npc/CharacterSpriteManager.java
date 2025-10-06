package entity.npc;

import directionEnum.Direction;
import entity.npc.sprite.CharacterSprite;
import entity.npc.sprite.CharacterSpriteRegistry;


import java.awt.image.BufferedImage;


import static java.util.Objects.isNull;

public class CharacterSpriteManager {

    private static class Holder{
        private static final CharacterSpriteManager INSTANCE = new CharacterSpriteManager();
    }

    private CharacterSpriteManager() {
    }

    public static CharacterSpriteManager getInstance(){
        return Holder.INSTANCE;
    }

    public int getCharacterMaxFrameLimit(String characterAnimationKey){
        CharacterSprite characterSprite =getCharacterSprite(characterAnimationKey);
        return characterSprite.getMaxFrameLimit();
    }

    public BufferedImage[] getAnimationArray(String characterAnimationKey,Direction currentDirection,boolean isIdle){
        CharacterSprite characterSprite =this.getCharacterSprite(characterAnimationKey);

        if(!isIdle){
            return  characterSprite.getMovingMotionAnimationByDirection(currentDirection);
        }
        return null;
    };

    private CharacterSprite getCharacterSprite(String characterAnimationKey){
        CharacterSprite sprite =CharacterSpriteRegistry.getInstance().getCharacterSpriteHashMap().get(characterAnimationKey);
        if(isNull(sprite)){
            System.out.println("CUSTOM ERROR: given character animation key is wrong. Animation key: "+characterAnimationKey);
        }
        return sprite;

    }
}
