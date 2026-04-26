package game.controller;


import game.panel.GamePanel;

public interface GameController {
    void startGame();

    void stopGame();

    GamePanel getGamePanel();
}
