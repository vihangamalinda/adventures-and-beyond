package entity.npc.factory;

import directionEnum.Direction;
import entity.npc.NonPlayerCharacter;

public interface NonPlayerCharacterFactory {
    NonPlayerCharacter create(String characterType,
                              int worldPositionX,
                              int worldPositionY,
                              Direction direction,
                              boolean onCollision,
                              String dialogueKey);

    String[] getCharacterTypes();
}
