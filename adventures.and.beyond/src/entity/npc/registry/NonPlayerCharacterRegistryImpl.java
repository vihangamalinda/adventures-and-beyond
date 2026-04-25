package entity.npc.registry;

import dialogue.manager.DialogueKey;
import directionEnum.Direction;
import entity.npc.DrawableNonPlayerCharacter;
import entity.npc.NonPlayerCharacter;
import entity.npc.UpdatableNonPlayerCharacter;
import entity.npc.factory.NonPlayerCharacterFactoryImpl;

import java.util.ArrayList;
import java.util.List;

import static helper.Constant.TILE_SIZE;

public class NonPlayerCharacterRegistryImpl implements NonPlayerCharacterRegistry {

    private final List<UpdatableNonPlayerCharacter> updatableNonPlayerCharacterList;
    private final List<DrawableNonPlayerCharacter> drawableNonPlayerCharacterList;
    private final NonPlayerCharacterFactoryImpl nonPlayerCharacterFactory;

    public NonPlayerCharacterRegistryImpl() {
        this.drawableNonPlayerCharacterList = new ArrayList<>();
        this.updatableNonPlayerCharacterList = new ArrayList<>();
        this.nonPlayerCharacterFactory = new NonPlayerCharacterFactoryImpl();
        this.registerCharacters();
    }

    private void registerCharacters() {
        String[] characterTypes = nonPlayerCharacterFactory.getCharacterTypes();

        registerCharacter(characterTypes[0], 28 * TILE_SIZE, 12 * TILE_SIZE, Direction.FACING_FORWARD, false, DialogueKey.DIALOGUE_01);
        registerCharacter(characterTypes[0], 37 * TILE_SIZE, 12 * TILE_SIZE, Direction.FACING_FORWARD, false, DialogueKey.DIALOGUE_02);
        registerCharacter(characterTypes[0], 23 * TILE_SIZE, 12 * TILE_SIZE, Direction.FACING_FORWARD, false, DialogueKey.DIALOGUE_01);
    }

    private void registerCharacter(String characterType, int worldPositionX, int worldPositionY, Direction direction, boolean onCollision, String dialogueKey) {
        NonPlayerCharacter character = nonPlayerCharacterFactory.create(characterType, worldPositionX, worldPositionY, direction, onCollision, dialogueKey);
        registerDrawable(character);
        registerUpdatable(character);
    }

    private void registerDrawable(NonPlayerCharacter nonPlayerCharacter) {
        this.drawableNonPlayerCharacterList.add(nonPlayerCharacter);
    }

    private void registerUpdatable(NonPlayerCharacter nonPlayerCharacter) {
        this.updatableNonPlayerCharacterList.add(nonPlayerCharacter);
    }

    @Override
    public List<UpdatableNonPlayerCharacter> getRegisteredUpdatableList() {
        return this.updatableNonPlayerCharacterList;
    }

    @Override
    public List<DrawableNonPlayerCharacter> getRegisteredDrawableList() {
        return this.drawableNonPlayerCharacterList;
    }


}
