package movement.player;

import directionEnum.Direction;
import entity.player.Player;
import keyhandler.playerinputstate.registeredplayerinput.RegisteredPlayerInput;

public class PlayerMovementServiceImpl implements PlayerMovementService {

//    private final ReadKeyState readKeyState;

    public PlayerMovementServiceImpl() {

    }

    @Override
    public void updateDirectionAndIdleState(Player player, RegisteredPlayerInput registeredPlayerInput) {
        this.updatePlayerIdleState(player, registeredPlayerInput);
        this.updateDirection(player, registeredPlayerInput);
    }

    private void updateDirection(Player player, RegisteredPlayerInput registeredPlayerInput) {

        Direction direction = player.getDirection();

        if (registeredPlayerInput.upPressed) {
            direction = Direction.FACING_BACKWARD;
        } else if (registeredPlayerInput.downPressed) {
            direction = Direction.FACING_FORWARD;
        } else if (registeredPlayerInput.leftPressed) {
            direction = Direction.FACING_LEFTWARD;
        } else if (registeredPlayerInput.rightPressed) {
            direction = Direction.FACING_RIGHTWARD;
        }

        //should check this condition
        boolean shouldUpdateDirection = !player.isIdle() && !direction.equals(player.getDirection());
        if (shouldUpdateDirection) {
            player.setDirection(direction);
        }
    }


    private void updatePlayerIdleState(Player player, RegisteredPlayerInput registeredPlayerInput) {
        player.setIdle(registeredPlayerInput.isAtIdle);
    }

    @Override
    public void updateWorldPosition(Player player, RegisteredPlayerInput registeredPlayerInput) {
        if (player.isOnCollision()) {
            return;
        }

        if (registeredPlayerInput.upPressed) {
            this.moveUpDirection(player);
        } else if (registeredPlayerInput.downPressed) {
            this.moveDownDirection(player);
        } else if (registeredPlayerInput.leftPressed) {
            this.moveLeftDirection(player);
        } else if (registeredPlayerInput.rightPressed) {
            this.moveRightDirection(player);
        }
    }

    private void moveUpDirection(Player player) {
        int newWorldPositionY = player.getWorldPositionY() - player.getSpeed();
        player.setWorldPositionY(newWorldPositionY);
    }

    private void moveDownDirection(Player player) {
        int newWorldPositionY = player.getWorldPositionY() + player.getSpeed();
        player.setWorldPositionY(newWorldPositionY);
    }

    private void moveLeftDirection(Player player) {
        int newWorldPositionX = player.getWorldPositionX() - player.getSpeed();
        player.setWorldPositionX(newWorldPositionX);
    }

    private void moveRightDirection(Player player) {
        int newWorldPositionX = player.getWorldPositionX() + player.getSpeed();
        player.setWorldPositionX(newWorldPositionX);
    }
}
