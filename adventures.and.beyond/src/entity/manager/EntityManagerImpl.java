package entity.manager;

import dialogue.manager.DialogueKey;
import directionEnum.Direction;

import entity.npc.DrawableNonPlayerCharacter;
import entity.npc.NonPlayerCharacterFactory;
import entity.npc.NonPlayerCharacterFactoryImpl;
import entity.npc.UpdatableNonPlayerCharacter;
import entity.player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static helper.Constant.TILE_SIZE;

public class EntityManagerImpl implements EntityManager {
    private Player mainEntity;

    private  List<UpdatableNonPlayerCharacter> secondaryUpdatables;
    private List<DrawableNonPlayerCharacter> secondaryDrawables;


    public EntityManagerImpl() {
        this.registerEntities();
    }

    private void registerEntities() {
        registerMainEntity();
        registerSecondaryEntities();
    }

    private void registerMainEntity() {
        this.mainEntity = new Player(28 * TILE_SIZE, 12 * TILE_SIZE);
    }

    private void registerSecondaryEntities() {
        NonPlayerCharacterFactory nonPlayerCharacterFactory = new NonPlayerCharacterFactoryImpl();
        String[] characterTypes =nonPlayerCharacterFactory.getCharacterTypes();

        nonPlayerCharacterFactory.register(characterTypes[0],28 * TILE_SIZE, 12 * TILE_SIZE, Direction.FACING_FORWARD, false, DialogueKey.DIALOGUE_01);
        nonPlayerCharacterFactory.register(characterTypes[0],37 * TILE_SIZE, 12 * TILE_SIZE, Direction.FACING_FORWARD, false,DialogueKey.DIALOGUE_02);
        nonPlayerCharacterFactory.register(characterTypes[0],23 * TILE_SIZE, 12 * TILE_SIZE, Direction.FACING_FORWARD, false, DialogueKey.DIALOGUE_01);


        this.secondaryUpdatables =nonPlayerCharacterFactory.getUpdatableNonPlayerCharacterList();
        this.secondaryDrawables = nonPlayerCharacterFactory.getDrawableNonPlayerCharacterList();
    }

    public void drawEntities(Graphics2D graphics2D) {
        this.mainEntity.draw(graphics2D);

        for (DrawableNonPlayerCharacter character : this.secondaryDrawables) {
            character.draw(graphics2D, this.getPlayer());
        }
    }

    public void updateEntities() {
        this.mainEntity.update();

        for (UpdatableNonPlayerCharacter character : this.secondaryUpdatables) {
            character.update();
        }
    }

    public Player getPlayer() {
        return this.mainEntity;
    }
}
