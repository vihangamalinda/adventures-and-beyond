package game.loop;

import game.update.GameUpdater;
import helper.Constant;

public class GameLoopImpl implements GameLoop,Runnable {
    private Thread gameLoopThread;
    private final GameUpdater gameUpdater;
    private Runnable repaintAction;

    public GameLoopImpl(GameUpdater gameUpdater) {
        this.gameUpdater = gameUpdater;
    }

    @Override
    public void start(Runnable repaintAction) {
        this.repaintAction =repaintAction;
        this.gameLoopThread = new Thread(this);
        this.gameLoopThread.start();
    }
    @Override
    public void stop(){
        this.gameLoopThread = null;
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / Constant.FRAME_RATE_PER_SECOND; // 0.0166 seconds
        double delta = 0;
        long lastTime = System.nanoTime();

        while (this.gameLoopThread != null) {
           /*
           Game loop :
           Responsibilities of game loop:
                1) UPDATE : update information such as character positions

                2) DRAW : draw the screen with the updated information
            */
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                // Update responsibility
                this.gameUpdater.update();
                // Draw
                repaintAction.run();
                delta--;
            }
        }

    }
}
