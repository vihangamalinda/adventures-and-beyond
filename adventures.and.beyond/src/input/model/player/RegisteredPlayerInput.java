package input.model.player;

public class RegisteredPlayerInput {
    public final boolean upPressed;
    public final boolean downPressed;
    public final boolean leftPressed;
    public final boolean rightPressed;
    public final boolean isAtIdle;

    public RegisteredPlayerInput(boolean upPressed, boolean downPressed, boolean leftPressed, boolean rightPressed, boolean isAtIdle) {
        this.upPressed = upPressed;
        this.downPressed = downPressed;
        this.leftPressed = leftPressed;
        this.rightPressed = rightPressed;
        this.isAtIdle = isAtIdle;
    }

}
