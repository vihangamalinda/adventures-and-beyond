package collision.detector.npc;

import entity.npc.NonPlayerCharacter;
import entity.player.Player;

public interface NonPlayerCharacterCollisionDetector {
    void checkCharacterPlayerCollision(NonPlayerCharacter character, Player player);
}
