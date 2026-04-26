package input.reader;

import input.model.player.RegisteredPlayerInput;
import input.state.ReadKeyState;

public class PlayerInputReaderImpl implements PlayerInputReader {
    private final ReadKeyState readKeyState;
//    private final RegisteredPlayerInput registeredPlayerInput;

    public PlayerInputReaderImpl(ReadKeyState readKeyState) {
        this.readKeyState = readKeyState;
//        this.registeredPlayerInput = new RegisteredPlayerInput(
//                this.readKeyState.isUpPressed(),
//                this.readKeyState.isDownPressed(),
//                this.readKeyState.isLeftPressed(),
//                this.readKeyState.isRightPressed(),
//                !(this.readKeyState.isAnyMovementDirectionKeyPressed())
//        );
    }

//    private void updateRegisteredPlayerInput() {
//        this.registeredPlayerInput.upPressed = this.readKeyState.isUpPressed();
//        this.registeredPlayerInput.downPressed = this.readKeyState.isDownPressed();
//        this.registeredPlayerInput.leftPressed = this.readKeyState.isLeftPressed();
//        this.registeredPlayerInput.rightPressed = this.readKeyState.isRightPressed();
//        this.registeredPlayerInput.isAtIdle = !(this.readKeyState.isAnyMovementDirectionKeyPressed());
//    }

    @Override
    public RegisteredPlayerInput readPlayerInput() {
        boolean isAtIdle = !(this.readKeyState.isAnyMovementDirectionKeyPressed());
        return new RegisteredPlayerInput(
                this.readKeyState.isUpPressed(),
                this.readKeyState.isDownPressed(),
                this.readKeyState.isLeftPressed(),
                this.readKeyState.isRightPressed(),
                isAtIdle
        );
//        this.updateRegisteredPlayerInput();
//        if(this.registeredPlayerInput.downPressed){
//            System.out.println("down is pressed");
//        }
//        return this.registeredPlayerInput;
    }
}
