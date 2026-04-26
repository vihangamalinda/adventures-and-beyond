package game.update;

import entity.manager.EntityManager;
import input.reader.UIInputReader;
import ui.UserInterfaceManager;

public class GameUpdaterImpl implements GameUpdater {

    private final EntityManager entityManager;
    private final UserInterfaceManager userInterfaceManager;
    private final UIInputReader uiInputReader;

    public GameUpdaterImpl(EntityManager entityManager, UserInterfaceManager userInterfaceManager, UIInputReader uiInputReader) {
        this.entityManager = entityManager;
        this.userInterfaceManager = userInterfaceManager;
        this.uiInputReader = uiInputReader;
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
