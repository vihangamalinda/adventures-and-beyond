package entity.npc.registry;

import entity.npc.DrawableNonPlayerCharacter;
import entity.npc.UpdatableNonPlayerCharacter;

import java.util.List;

public interface NonPlayerCharacterRegistry {
    List<UpdatableNonPlayerCharacter> getRegisteredUpdatableList();
    List<DrawableNonPlayerCharacter> getRegisteredDrawableList();
}
