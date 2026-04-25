package entity.manager;

import entity.npc.DrawableNonPlayerCharacter;
import entity.npc.UpdatableNonPlayerCharacter;
import entity.npc.registry.NonPlayerCharacterRegistry;
import entity.npc.registry.NonPlayerCharacterRegistryImpl;
import entity.player.Player;

import java.awt.*;
import java.util.List;

import static helper.Constant.TILE_SIZE;

public class EntityManagerImpl implements EntityManager {

    private Player mainEntity;

    private List<UpdatableNonPlayerCharacter> secondaryUpdatableList;
    private List<DrawableNonPlayerCharacter> secondaryDrawableList;

    private final NonPlayerCharacterRegistry nonPlayerCharacterRegistry;


    public EntityManagerImpl() {
        this.nonPlayerCharacterRegistry = new NonPlayerCharacterRegistryImpl();
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
        this.secondaryUpdatableList = nonPlayerCharacterRegistry.getRegisteredUpdatableList();
        this.secondaryDrawableList = nonPlayerCharacterRegistry.getRegisteredDrawableList();
    }

    public void drawEntities(Graphics2D graphics2D) {
        this.mainEntity.draw(graphics2D);

        for (DrawableNonPlayerCharacter character : this.secondaryDrawableList) {
            character.draw(graphics2D, this.getPlayer());
        }
    }

    public void updateEntities() {
        this.mainEntity.update();

        for (UpdatableNonPlayerCharacter character : this.secondaryUpdatableList) {
            character.update(this.mainEntity);
        }
    }

    public Player getPlayer() {
        return this.mainEntity;
    }
}
