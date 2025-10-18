package entity.npc.factory;

import directionEnum.Direction;
import entity.npc.DrawableNonPlayerCharacter;
import entity.npc.NonPlayerCharacter;
import entity.npc.UpdatableNonPlayerCharacter;

import java.util.List;

public interface NonPlayerCharacterFactory {
    NonPlayerCharacter create(String characterType, int worldPositionX, int worldPositionY, Direction direction, boolean onCollision, String dialogueKey);
    String[] getCharacterTypes();
}
