package main;

import entity.manager.EntityManager;
import entity.manager.EntityManagerFactory;
import entity.player.PlayerSpriteManager;
import game.controller.GameController;
import game.controller.GameControllerImpl;
import game.loop.GameLoop;
import game.loop.GameLoopImpl;
import game.panel.GamePanel;
import game.render.GameRenderer;
import game.render.GameRendererImpl;
import game.update.GameUpdater;
import game.update.GameUpdaterImpl;
import input.KeyHandler;
import input.reader.UIInputReader;
import input.reader.UIInputReaderImpl;
import input.state.ReadKeyState;
import ui.UserInterfaceManager;
import window.GameWindow;
import window.GameWindowImpl;
import world.manager.WorldMapManager;
import world.manager.WorldMapManagerFactory;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        PlayerSpriteManager.initializeSpriteMap();

        String title = "Adventures and Beyond";

        GameController gameController = createGameController();
        GameWindow gameWindow = new GameWindowImpl(title);
        gameWindow.initialize(gameController.getGamePanel());
        gameController.startGame();
    }

    private static GameController createGameController() {
        EntityManager entityManager = EntityManagerFactory.getInstance();
        UserInterfaceManager userInterfaceManager = UserInterfaceManager.getInstance();
        WorldMapManager worldMapManager = WorldMapManagerFactory.getInstance();
        KeyHandler keyHandler = KeyHandler.getInstance();
        ReadKeyState readKeyState = keyHandler.readRegisteredKeyState();
        UIInputReader uiInputReader = new UIInputReaderImpl(readKeyState);

        GameUpdater gameUpdater = new GameUpdaterImpl(entityManager, userInterfaceManager, uiInputReader);
        GameLoop gameLoop = new GameLoopImpl(gameUpdater);
        GameRenderer gameRenderer = new GameRendererImpl(entityManager, userInterfaceManager, worldMapManager);
        GamePanel gamePanel = new GamePanel(gameRenderer, keyHandler);

        return new GameControllerImpl(gameLoop, gamePanel);
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