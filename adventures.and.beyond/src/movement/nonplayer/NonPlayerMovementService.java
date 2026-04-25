package movement.nonplayer;

import entity.npc.NonPlayerCharacter;

public interface NonPlayerMovementService {
    void  updatePositionAndDirection(NonPlayerCharacter nonPlayerCharacter);
}
