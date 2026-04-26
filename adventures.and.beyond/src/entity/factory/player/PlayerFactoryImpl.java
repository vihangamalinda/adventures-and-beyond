package entity.factory.player;

import animation.service.player.PlayerAnimationService;
import collision.detector.service.PlayerCollisionService;
import drawable.service.player.PlayerDrawService;
import entity.player.Player;
import input.reader.PlayerInputReader;
import movement.player.PlayerMovementService;

public class PlayerFactoryImpl implements PlayerFactory {
    private final PlayerMovementService playerMovementService;
    private final PlayerInputReader playerInputReader;
    private final PlayerAnimationService playerAnimationService;
    private final PlayerDrawService playerDrawService;
    private final PlayerCollisionService playerCollisionService;

    public PlayerFactoryImpl(PlayerMovementService playerMovementService,
                             PlayerInputReader playerInputReader,
                             PlayerAnimationService playerAnimationService,
                             PlayerDrawService playerDrawService,
                             PlayerCollisionService playerCollisionService) {
        this.playerAnimationService = playerAnimationService;
        this.playerCollisionService = playerCollisionService;
        this.playerDrawService = playerDrawService;
        this.playerInputReader = playerInputReader;
        this.playerMovementService = playerMovementService;
    }

    @Override
    public Player createPlayer(int positionX,
                               int positionY) {
        return new Player(positionX,
                          positionY,
                          playerMovementService,
                          playerInputReader,
                          playerAnimationService,
                          playerDrawService,
                          playerCollisionService);
    }

}
