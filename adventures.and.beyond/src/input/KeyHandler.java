package input;

import input.service.DefaultKeyControlService;
import input.service.KeyControlService;
import input.state.ReadKeyState;
import input.state.RegisteredKeyState;
import input.state.RegisteredKeyStateImpl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private final RegisteredKeyState registeredKeyState;
    private final KeyControlService defaultKeyControlService;
    private KeyControlService activeKeyControlService;

    public KeyHandler(RegisteredKeyState registeredKeyState,
                       KeyControlService defaultKeyControlService) {
        this.registeredKeyState = registeredKeyState;
        this.defaultKeyControlService =defaultKeyControlService;
        this.activeKeyControlService = this.defaultKeyControlService;
    }
//
//    private static class Holder {
//        private final static KeyHandler INSTANCE = new KeyHandler();
//    }
//
//    public static KeyHandler getInstance() {
//        return Holder.INSTANCE;
//    }

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
