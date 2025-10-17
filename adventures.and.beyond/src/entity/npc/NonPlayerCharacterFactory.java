package entity.npc;

import directionEnum.Direction;
import entity.npc.sprite.CharacterSpriteKey;

import java.util.List;

public interface NonPlayerCharacterFactory {
    void register(String characterType,int worldPositionX, int worldPositionY, Direction direction, boolean onCollision,String dialogueKey);
    List<DrawableNonPlayerCharacter> getDrawableNonPlayerCharacterList();
    List<UpdatableNonPlayerCharacter> getUpdatableNonPlayerCharacterList();
    String[] getCharacterTypes();
}
