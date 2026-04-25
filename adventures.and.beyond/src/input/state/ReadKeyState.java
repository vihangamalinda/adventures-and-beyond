package input.state;

public interface ReadKeyState {
    boolean isUpPressed();
    boolean isDownPressed();
    boolean isLeftPressed();
    boolean isRightPressed();
    boolean isOnDevMood();
    boolean isOnPause();
    boolean isAnyMovementDirectionKeyPressed();

}
