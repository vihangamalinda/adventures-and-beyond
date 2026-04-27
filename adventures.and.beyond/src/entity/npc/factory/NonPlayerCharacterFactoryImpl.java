package entity.npc.factory;

import collision.detector.service.NonPlayerCharacterCollisionService;
import directionEnum.Direction;
import entity.npc.NonPlayerCharacter;
import entity.npc.character.Human;
import entity.npc.sprite.CharacterSpriteKey;

public class NonPlayerCharacterFactoryImpl implements NonPlayerCharacterFactory {
    private static final String[] characterTypes = {"Human_Cyan", "Human_Blue"};
    private final NonPlayerCharacterCollisionService nonPlayerCharacterCollisionService;

    public NonPlayerCharacterFactoryImpl(NonPlayerCharacterCollisionService nonPlayerCharacterCollisionService) {
        this.nonPlayerCharacterCollisionService = nonPlayerCharacterCollisionService;
    }

    @Override
    public NonPlayerCharacter create(String characterType,
                                     int worldPositionX,
                                     int worldPositionY,
                                     Direction direction,
                                     boolean onCollision,
                                     String dialogueKey) {
        return switch (characterType) {
            case "Human_Cyan" -> createHuman(worldPositionX,
                                             worldPositionY,
                                             direction,
                                             onCollision,
                                             CharacterSpriteKey.HUMAN_CYAN,
                                             dialogueKey);
            case "Human_Blue" -> createHuman(worldPositionX,
                                             worldPositionY,
                                             direction,
                                             onCollision,
                                             CharacterSpriteKey.HUMAN_CYAN,
                                             dialogueKey);
            default -> null;
        };
    }

    private NonPlayerCharacter createHuman(int worldPositionX,
                                           int worldPositionY,
                                           Direction direction,
                                           boolean onCollision,
                                           String characterAnimationKey,
                                           String dialogueKey) {
        return new Human(worldPositionX,
                         worldPositionY,
                         direction,
                         onCollision,
                         characterAnimationKey,
                         dialogueKey,
                         this.nonPlayerCharacterCollisionService
        );
    }

    @Override
    public String[] getCharacterTypes() {
        return characterTypes;
    }
}
