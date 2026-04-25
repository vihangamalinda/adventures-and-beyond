package collision.detector.npc;

import entity.npc.NonPlayerCharacter;
import entity.player.Player;

public class NonPlayerCharacterCollisionDetectorImpl implements NonPlayerCharacterCollisionDetector {

    public NonPlayerCharacterCollisionDetectorImpl() {
    }

    @Override
    public void checkCharacterPlayerCollision(NonPlayerCharacter character,Player player) {
        boolean isColliding = this.doCharacterCollideWithPlayer(character, player);


        if (isColliding) {
            player.setOnCollision(true);
            character.associateWithPlayer();
        } else {
            character.disassociateWithPlayer();
        }
    }

    private boolean doCharacterCollideWithPlayer(NonPlayerCharacter character, Player player) {
        return player.getSolidAreaWithWorldPositions().intersects(character.getSolidAreaWithWorldPositions());
    }
}
