package entity.factory.player;

import entity.player.Player;

public interface PlayerFactory {
    Player createPlayer(int positionX,
                        int positionY);

}
