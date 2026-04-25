package collision.detector.npc;

import entity.manager.EntityManager;
import entity.manager.EntityManagerFactory;
import entity.npc.NonPlayerCharacter;
import entity.player.Player;

public class NonPlayerCharacterCollisionDetectorImpl implements NonPlayerCharacterCollisionDetector {
//    private final EntityManager entityManager;

    public NonPlayerCharacterCollisionDetectorImpl() {
//        this.entityManager = EntityManagerFactory.getInstance();
    }

    @Override
    public void checkCharacterPlayerCollision(NonPlayerCharacter character,Player player) {
//        Player player = this.entityManager.getPlayer();
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
