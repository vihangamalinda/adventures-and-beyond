package collision.detector.service;

import entity.npc.NonPlayerCharacter;
import entity.player.Player;

public interface NonPlayerCharacterCollisionService {
    void checkCollision(NonPlayerCharacter nonPlayerCharacter, Player player);
}
