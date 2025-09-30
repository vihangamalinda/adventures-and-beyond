package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    private KeyHandler(){}

    private static class Holder{
        private final static  KeyHandler INSTANCE = new KeyHandler();
    }

    public static KeyHandler getInstance(){
        return Holder.INSTANCE;
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                this.upPressed = true;
                break;
            case KeyEvent.VK_S:
                this.downPressed = true;
                break;
            case KeyEvent.VK_A:
                this.leftPressed = true;
                break;
            case KeyEvent.VK_D:
                this.rightPressed = true;
                break;
            default:

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                this.upPressed = false;
                break;
            case KeyEvent.VK_S:
                this.downPressed = false;
                break;
            case KeyEvent.VK_A:
                this.leftPressed = false;
                break;
            case KeyEvent.VK_D:
                this.rightPressed = false;
                break;
            default:

        }
    }
}
