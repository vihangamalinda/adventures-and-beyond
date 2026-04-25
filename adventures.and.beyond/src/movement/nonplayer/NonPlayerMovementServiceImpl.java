package movement.nonplayer;

import entity.npc.NonPlayerCharacter;
import entity.npc.character.Human;

import static directionEnum.Direction.*;
import static directionEnum.Direction.FACING_LEFTWARD;

public class NonPlayerMovementServiceImpl implements NonPlayerMovementService {

    @Override
    public void  updatePositionAndDirection(NonPlayerCharacter nonPlayerCharacter){
        // Implement the logic to update the non-player character's position and direction based on certain conditions, such as collision status or update rate.
        // This method can be called in the game loop to continuously update the non-player character's movement.
        // For example, you can iterate through a list of non-player characters and call this method for each character to update their position and direction accordingly.
        // You can also implement additional logic to handle specific behaviors for different types of non-player characters, such as humans or animals.
        // Remember to consider factors like collision detection and interaction with the player when updating the non-player character's movement.
        // This method can be further expanded to include more complex movement patterns or behaviors based on the game's requirements.
        // Overall, this method serves as a central point for managing the movement of non-player characters in the game world.


        if(this.shouldUpdatePosition(nonPlayerCharacter)){
            this.updateNonPlayerCharacterPosition(nonPlayerCharacter);
        }


        if(this.shouldChangeDirection(nonPlayerCharacter)){
            this.changeNonPlayerCharacterDirection(nonPlayerCharacter);
            nonPlayerCharacter.resetFrameCounter();
        }
    }
    private void updateNonPlayerCharacterPosition(NonPlayerCharacter nonPlayerCharacter) {
        // Implement the logic to update the non-player character's position based on its current direction and speed.
        int currentPositionX = nonPlayerCharacter.getWorldPositionX();
        int currentPositionY = nonPlayerCharacter.getWorldPositionY();
        int speed = nonPlayerCharacter.getSpeed();

        switch (nonPlayerCharacter.getDirection()) {
            case FACING_BACKWARD -> nonPlayerCharacter.setWorldPositionY(currentPositionY - speed);
            case FACING_FORWARD -> nonPlayerCharacter.setWorldPositionY(currentPositionY + speed);
            case FACING_LEFTWARD -> nonPlayerCharacter.setWorldPositionX(currentPositionX - speed);
            case FACING_RIGHTWARD -> nonPlayerCharacter.setWorldPositionX(currentPositionX + speed);
        }
    }

    private boolean shouldUpdatePosition(NonPlayerCharacter nonPlayerCharacter) {
        // Implement the logic to determine if the non-player character's position should be updated based on certain conditions, such as collision status or update rate.
        boolean onUpdateRate = nonPlayerCharacter.getFrameCounter() % 5 == 0;
        return onUpdateRate && !nonPlayerCharacter.isOnCollision() && !nonPlayerCharacter.isOnCollisionWithPlayer();
    }

    private boolean shouldChangeDirection(NonPlayerCharacter nonPlayerCharacter) {
        // Implement the logic to determine if the non-player character's direction should be changed based on certain conditions, such as update rate or collision status.
        boolean updateRate = nonPlayerCharacter.getFrameCounter() % 150 == 0;
        return updateRate && !nonPlayerCharacter.isOnCollisionWithPlayer();
    }

    private void changeNonPlayerCharacterDirection(NonPlayerCharacter nonPlayerCharacter) {
        // Implement the logic to change the non-player character's direction based on certain conditions or randomly.
        if (nonPlayerCharacter instanceof Human) {
            this.HumanDirectionChange(nonPlayerCharacter);
        } else {
            this.changeDirectionRandomly(nonPlayerCharacter);
        }
    }

    private void HumanDirectionChange(NonPlayerCharacter nonPlayerCharacter) {
        // Implement the logic to change the human non-player character's direction randomly.
        this.changeDirectionRandomly(nonPlayerCharacter);
    }

    private void changeDirectionRandomly(NonPlayerCharacter nonPlayerCharacter) {
        // Implement the logic to change the non-player character's direction randomly.
        int random = (int) (Math.random() * 4);
        switch (random) {
            case 0 -> nonPlayerCharacter.setDirection(FACING_FORWARD);
            case 1 -> nonPlayerCharacter.setDirection(FACING_BACKWARD);
            case 2 -> nonPlayerCharacter.setDirection(FACING_RIGHTWARD);
            case 3 -> nonPlayerCharacter.setDirection(FACING_LEFTWARD);
            default -> {
            }
        }
        System.out.println("Direction " + nonPlayerCharacter.getDirection().toString());
    }
}
