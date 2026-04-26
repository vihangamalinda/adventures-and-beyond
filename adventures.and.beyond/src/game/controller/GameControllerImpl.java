package game.controller;

import game.loop.GameLoop;
import game.panel.GamePanel;

public class GameControllerImpl implements GameController{

    private final GameLoop gameLoop;
    private final GamePanel gamePanel;

    public GameControllerImpl(GameLoop gameLoop,GamePanel gamePanel) {
        this.gameLoop = gameLoop;
        this.gamePanel = gamePanel;
    }
    @Override
    public GamePanel getGamePanel() {
        return this.gamePanel;
    }

    @Override
    public void startGame(){
        this.gameLoop.start(gamePanel::repaint);
    }
    @Override
    public void stopGame(){
        gameLoop.stop();
    }
}
