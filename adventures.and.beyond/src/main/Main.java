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
import world.manager.WorldMapManager;
import world.manager.WorldMapManagerFactory;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        PlayerSpriteManager.initializeSpriteMap();
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Adventures and Beyond");

        GameController gameController = createGameController();

        window.add(gameController.getGamePanel());
        //to inform window to be sized to fit the preferred size and layouts of subcomponents( gamePanel)
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

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