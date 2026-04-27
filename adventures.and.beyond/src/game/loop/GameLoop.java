package game.loop;

public interface GameLoop {
    void start(Runnable repaintAction);

    void stop();
}
