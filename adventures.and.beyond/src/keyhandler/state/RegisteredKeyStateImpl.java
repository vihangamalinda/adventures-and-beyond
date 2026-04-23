package keyhandler.state;

public class RegisteredKeyStateImpl implements RegisteredKeyState {
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean onDevMood;
    private boolean onPause;

    public RegisteredKeyStateImpl() {

    }

    @Override
    public boolean isUpPressed() {
        return upPressed;
    }

    @Override
    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    @Override
    public boolean isDownPressed() {
        return downPressed;
    }

    @Override
    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    @Override
    public boolean isLeftPressed() {
        return leftPressed;
    }

    @Override
    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    @Override
    public boolean isRightPressed() {
        return rightPressed;
    }

    @Override
    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }

    @Override
    public boolean isOnDevMood() {
        return onDevMood;
    }

    public void setOnDevMood(boolean onDevMood) {
        this.onDevMood = onDevMood;
    }

    public boolean isOnPause() {
        return onPause;
    }

    @Override
    public boolean isAnyMovementDirectionKeyPressed() {
        return this.upPressed || this.downPressed || this.leftPressed || this.rightPressed;
    }

    public void setOnPause(boolean onPause) {
        this.onPause = onPause;
    }
}
