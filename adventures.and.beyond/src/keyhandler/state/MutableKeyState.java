package keyhandler.state;

public interface MutableKeyState{
    void setUpPressed(boolean upPressed);
    void setDownPressed(boolean downPressed);
    void setLeftPressed(boolean leftPressed);
    void setRightPressed(boolean rightPressed);
    void setOnDevMood(boolean onDevMood);
    void setOnPause(boolean onPause);
}
