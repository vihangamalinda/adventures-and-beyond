package game.update;

import entity.manager.EntityManager;
import input.KeyHandler;
import input.reader.UIInputReader;
import input.reader.UIInputReaderImpl;
import input.state.ReadKeyState;
import ui.UserInterfaceManager;

public class GameUpdaterImpl implements GameUpdater {

    private final EntityManager entityManager;
    private final UserInterfaceManager userInterfaceManager;
    private final UIInputReader uiInputReader;

    public GameUpdaterImpl(EntityManager entityManager, UserInterfaceManager userInterfaceManager) {
        this.entityManager = entityManager;
        this.userInterfaceManager = userInterfaceManager;
        ReadKeyState readKeyState = KeyHandler.getInstance().readRegisteredKeyState();
        this.uiInputReader = new UIInputReaderImpl(readKeyState);
    }


    @Override
    public void update() {
        boolean onPause = this.uiInputReader.readUIInputs().pausePressed;
        if (onPause) {
            this.userInterfaceManager.pauseGameModal();
        } else {
            this.entityManager.updateEntities();
        }
    }

}
