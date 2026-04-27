package input.reader;

import input.model.player.RegisteredUIInputs;
import input.state.ReadKeyState;

public class UIInputReaderImpl implements UIInputReader {
    private final ReadKeyState readKeyState;

    public UIInputReaderImpl(ReadKeyState readKeyState) {
        this.readKeyState = readKeyState;
    }

    @Override
    public RegisteredUIInputs readUIInputs() {
        return new RegisteredUIInputs(this.readKeyState.isOnPause());
    }
}
