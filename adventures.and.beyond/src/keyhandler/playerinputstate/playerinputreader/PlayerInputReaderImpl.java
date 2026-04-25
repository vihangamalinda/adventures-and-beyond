package keyhandler.playerinputstate.playerinputreader;


import keyhandler.playerinputstate.registeredplayerinput.RegisteredPlayerInput;
import keyhandler.state.ReadKeyState;

public class PlayerInputReaderImpl implements PlayerInputReader {
    private final ReadKeyState readKeyState;
    public PlayerInputReaderImpl(ReadKeyState readKeyState) {
        this.readKeyState = readKeyState;
    }

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
    }
}
