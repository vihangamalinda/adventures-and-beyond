package keyhandler;

import keyhandler.inputservice.DefaultKeyControlService;
import keyhandler.inputservice.KeyControlService;
import keyhandler.state.ReadKeyState;
import keyhandler.state.RegisteredKeyState;
import keyhandler.state.RegisteredKeyStateImpl;
import ui.UserInterfaceManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private final RegisteredKeyState registeredKeyState;
    private final KeyControlService defaultKeyControlService;
    private KeyControlService activeKeyControlService;

    private KeyHandler() {
        this.registeredKeyState = new RegisteredKeyStateImpl();
        this.defaultKeyControlService = new DefaultKeyControlService(this.registeredKeyState);
        this.activeKeyControlService = this.defaultKeyControlService;
    }

    private static class Holder {
        private final static KeyHandler INSTANCE = new KeyHandler();
    }

    public static KeyHandler getInstance() {
        return Holder.INSTANCE;
    }

    public ReadKeyState readRegisteredKeyState() {
        return this.registeredKeyState;
    }

    public void setActiveKeyControlService(KeyControlService activeKeyControlService) {
        this.activeKeyControlService = activeKeyControlService;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public boolean isAnyMovementDirectionKeyPressed() {
        return this.registeredKeyState.isAnyMovementDirectionKeyPressed();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.defaultKeyControlService.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.activeKeyControlService.keyReleased(e);
    }
}
