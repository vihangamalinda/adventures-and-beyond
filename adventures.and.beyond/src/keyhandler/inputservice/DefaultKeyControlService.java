package keyhandler.inputservice;

import keyhandler.state.RegisteredKeyState;
import ui.UserInterfaceManager;

import java.awt.event.KeyEvent;

public class DefaultKeyControlService implements KeyControlService {

    private final RegisteredKeyState registeredKeyState;

    public DefaultKeyControlService(RegisteredKeyState registeredKeyState) {
        this.registeredKeyState = registeredKeyState;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                updateUpPressed(true);
                break;
            case KeyEvent.VK_S:
                updateDownPressed(true);
                break;
            case KeyEvent.VK_A:
                updateLeftPressed(true);
                break;
            case KeyEvent.VK_D:
                updateRightPressed(true);
                break;
            case KeyEvent.VK_I:
                updateOnDevMood(!this.registeredKeyState.isOnDevMood());
                break;
            case KeyEvent.VK_P:
                boolean isOnPause = this.registeredKeyState.isOnPause();
                if (isOnPause) {
                    UserInterfaceManager.getInstance().deactivateMainNotification();
                }
                updateOnPause(!isOnPause);
            default:
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                updateUpPressed(false);
                break;
            case KeyEvent.VK_S:
                updateDownPressed(false);
                break;
            case KeyEvent.VK_A:
                updateLeftPressed(false);
                break;
            case KeyEvent.VK_D:
                updateRightPressed(false);
                break;
            default:

        }
    }


    private void updateLeftPressed(boolean isPressed) {
        this.registeredKeyState.setLeftPressed(isPressed);
    }

    private void updateRightPressed(boolean isPressed) {
        this.registeredKeyState.setRightPressed(isPressed);
    }

    private void updateUpPressed(boolean isPressed) {
        this.registeredKeyState.setUpPressed(isPressed);
    }

    private void updateDownPressed(boolean isPressed) {
        this.registeredKeyState.setDownPressed(isPressed);
    }

    private void updateOnDevMood(boolean isOnDevMood) {
        this.registeredKeyState.setOnDevMood(isOnDevMood);
    }

    private void updateOnPause(boolean isOnPause) {
        this.registeredKeyState.setOnPause(isOnPause);
    }
}
