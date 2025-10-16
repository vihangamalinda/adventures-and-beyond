package entity.manager;

import dialogue.manager.DialogueKey;
import directionEnum.Direction;
import entity.npc.NonPlayerCharacter;
import entity.npc.character.Human;
import entity.npc.sprite.CharacterSpriteKey;
import entity.player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static helper.Constant.TILE_SIZE;

public class EntityManagerImpl implements EntityManager {
    private Player mainEntity;
    private final List<NonPlayerCharacter> secondaryEntities;

    public EntityManagerImpl() {
        this.secondaryEntities = new ArrayList<>();
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
        NonPlayerCharacter human = new Human(28 * TILE_SIZE, 12 * TILE_SIZE, Direction.FACING_FORWARD, false, CharacterSpriteKey.HUMAN_CYAN, DialogueKey.DIALOGUE_01);
        NonPlayerCharacter human2 = new Human(37 * TILE_SIZE, 12 * TILE_SIZE, Direction.FACING_FORWARD, false, CharacterSpriteKey.HUMAN_CYAN,DialogueKey.DIALOGUE_02);

        this.secondaryEntities.add(human);
        this.secondaryEntities.add(human2);

    }

    public void drawEntities(Graphics2D graphics2D) {
        this.mainEntity.draw(graphics2D);

        for (NonPlayerCharacter character : this.secondaryEntities) {
            character.draw(graphics2D, this.getPlayer());
        }
    }

    public void updateEntities() {
        this.mainEntity.update();

        for (NonPlayerCharacter character : this.secondaryEntities) {
            character.update();
        }
    }

    public Player getPlayer() {
        return this.mainEntity;
    }
}
