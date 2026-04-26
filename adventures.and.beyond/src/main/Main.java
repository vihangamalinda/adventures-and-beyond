package main;

import configuration.AppConfiguration;
import entity.player.PlayerSpriteManager;
import window.GameWindow;
import window.GameWindowImpl;

public class Main {
    public static void main(String[] args) {
        PlayerSpriteManager.initializeSpriteMap();

        String title = "Adventures and Beyond";

        AppConfiguration appConfiguration = new AppConfiguration();

        GameWindow gameWindow = new GameWindowImpl(title);
        gameWindow.initialize(appConfiguration.getGamePanel());
        appConfiguration.getGameController().startGame();
    }
}

/**
 * Responsibility Map of Game classes
 * <p>
 * GameController = "brain"
 * GameLoop       = "heartbeat"
 * GameUpdater    = "logic"
 * GameRenderer   = "eyes"
 * GamePanel      = "body"
 **/