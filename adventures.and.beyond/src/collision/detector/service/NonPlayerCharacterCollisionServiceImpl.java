package collision.detector.service;

import collision.detector.npc.NonPlayerCharacterCollisionDetector;
import collision.detector.npc.NonPlayerCharacterCollisionDetectorImpl;
import collision.detector.tile.TileCollisionDetector;
import collision.detector.tile.TileCollisionDetectorImpl;
import entity.npc.NonPlayerCharacter;
import entity.player.Player;

public class NonPlayerCharacterCollisionServiceImpl implements NonPlayerCharacterCollisionService {

    private final TileCollisionDetector tileCollisionDetector;
    private final NonPlayerCharacterCollisionDetector nonPlayerCharacterCollisionDetector;

    public NonPlayerCharacterCollisionServiceImpl() {
        this.tileCollisionDetector = new TileCollisionDetectorImpl();
        this.nonPlayerCharacterCollisionDetector = new NonPlayerCharacterCollisionDetectorImpl();
    }

    @Override
    public void checkCollision(NonPlayerCharacter nonPlayerCharacter,
                               Player player) {
        this.tileCollisionDetector.checkTileCollision(nonPlayerCharacter);
        this.nonPlayerCharacterCollisionDetector.checkCharacterPlayerCollision(nonPlayerCharacter,
                                                                               player);
    }
}
