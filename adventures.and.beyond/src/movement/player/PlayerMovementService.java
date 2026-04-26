package movement.player;

import entity.player.Player;
import input.model.player.RegisteredPlayerInput;

public interface PlayerMovementService {
    void updateDirectionAndIdleState(Player player,
                                     RegisteredPlayerInput registeredPlayerInput);

    void updateWorldPosition(Player player,
                             RegisteredPlayerInput registeredPlayerInput);

}
