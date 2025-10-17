package entity.npc;

import directionEnum.Direction;
import entity.npc.character.Human;
import entity.npc.sprite.CharacterSpriteKey;

import java.util.ArrayList;
import java.util.List;

public class NonPlayerCharacterFactoryImpl implements NonPlayerCharacterFactory {
    private static final String[] characterTypes = {"Human_Cyan", "Human_Blue"};

    private final List<UpdatableNonPlayerCharacter> updatableNonPlayerCharacterList;
    private final List<DrawableNonPlayerCharacter> drawableNonPlayerCharacterList;

    public NonPlayerCharacterFactoryImpl() {
        this.updatableNonPlayerCharacterList = new ArrayList<>();
        this.drawableNonPlayerCharacterList = new ArrayList<>();
    }

    @Override
    public void register(String characterType, int worldPositionX, int worldPositionY, Direction direction, boolean onCollision, String dialogueKey) {
        switch (characterType) {
            case "Human_Cyan" ->
                    createHuman(worldPositionX, worldPositionY, direction, onCollision, CharacterSpriteKey.HUMAN_CYAN, dialogueKey);
            case "Human_Blue" ->
                    createHuman(worldPositionX, worldPositionY, direction, onCollision, CharacterSpriteKey.HUMAN_CYAN, dialogueKey);
            default -> System.out.println("ERROR on type: " + characterType);
        }
    }

    private void createHuman(int worldPositionX, int worldPositionY, Direction direction, boolean onCollision, String characterAnimationKey, String dialogueKey) {
        NonPlayerCharacter human = new Human(worldPositionX, worldPositionY, direction, onCollision, characterAnimationKey, dialogueKey);
        appendDrawable(human);
        appendUpdatable(human);
    }

    private void appendDrawable(DrawableNonPlayerCharacter drawableNonPlayerCharacter) {
        this.drawableNonPlayerCharacterList.add(drawableNonPlayerCharacter);
    }

    private void appendUpdatable(UpdatableNonPlayerCharacter updatableNonPlayerCharacter) {
        this.updatableNonPlayerCharacterList.add(updatableNonPlayerCharacter);
    }

    public List<DrawableNonPlayerCharacter> getDrawableNonPlayerCharacterList() {
        return drawableNonPlayerCharacterList;
    }

    public List<UpdatableNonPlayerCharacter> getUpdatableNonPlayerCharacterList() {
        return updatableNonPlayerCharacterList;
    }

    public String[] getCharacterTypes() {
        return characterTypes;
    }
}
