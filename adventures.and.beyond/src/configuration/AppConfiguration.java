package configuration;

import animation.service.player.PlayerAnimationService;
import animation.service.player.PlayerAnimationServiceImpl;
import collision.detector.npc.NonPlayerCharacterCollisionDetector;
import collision.detector.npc.NonPlayerCharacterCollisionDetectorImpl;
import collision.detector.object.ObjectCollisionDetector;
import collision.detector.object.ObjectCollisionDetectorImpl;
import collision.detector.service.NonPlayerCharacterCollisionService;
import collision.detector.service.NonPlayerCharacterCollisionServiceImpl;
import collision.detector.service.PlayerCollisionService;
import collision.detector.service.PlayerCollisionServiceImpl;
import collision.detector.tile.TileCollisionDetector;
import collision.detector.tile.TileCollisionDetectorImpl;
import drawable.service.player.PlayerDrawService;
import drawable.service.player.PlayerDrawServiceImpl;
import entity.factory.player.PlayerFactory;
import entity.factory.player.PlayerFactoryImpl;
import entity.manager.EntityManager;
import entity.manager.EntityManagerImpl;
import entity.npc.factory.NonPlayerCharacterFactory;
import entity.npc.factory.NonPlayerCharacterFactoryImpl;
import entity.npc.registry.NonPlayerCharacterRegistry;
import entity.npc.registry.NonPlayerCharacterRegistryImpl;
import entity.player.Player;
import game.controller.GameController;
import game.controller.GameControllerImpl;
import game.loop.GameLoop;
import game.loop.GameLoopImpl;
import game.panel.GamePanel;
import game.render.GameRenderer;
import game.render.GameRendererImpl;
import game.update.GameUpdater;
import game.update.GameUpdaterImpl;
import helper.Timer;
import input.KeyHandler;
import input.model.player.RegisteredPlayerInput;
import input.reader.PlayerInputReader;
import input.reader.PlayerInputReaderImpl;
import input.reader.UIInputReader;
import input.reader.UIInputReaderImpl;
import input.service.DefaultKeyControlService;
import input.service.KeyControlService;
import input.state.ReadKeyState;
import input.state.RegisteredKeyState;
import input.state.RegisteredKeyStateImpl;
import movement.player.PlayerMovementService;
import movement.player.PlayerMovementServiceImpl;
import ui.UserInterfaceManager;
import ui.notifier.factory.DetailNotifierFactoryImpl;
import ui.notifier.registry.DetailNotifierRegistry;
import ui.notifier.registry.DetailNotifierRegistryImpl;
import world.manager.WorldMapManager;
import world.manager.WorldMapManagerFactory;
import world.manager.WorldMapManagerImpl;
import world.worldmap.WorldMap;

public class AppConfiguration {

    private final RegisteredKeyState registeredKeyState;
    private final PlayerAnimationService playerAnimationService;
    private final PlayerDrawService playerDrawService;
    private final PlayerMovementService playerMovementService;
    private final PlayerCollisionService playerCollisionService;
    private final PlayerInputReader playerInputReader;
    private final PlayerFactory playerFactory;
    private final EntityManager entityManager;
    private final DetailNotifierFactoryImpl detailNotifierFactory;
    private final DetailNotifierRegistry detailNotifierRegistry;
    private final UserInterfaceManager userInterfaceManager;
    private final KeyHandler keyHandler;
    private final ReadKeyState readKeyState;
    private final UIInputReader uiInputReader;

    private final GameUpdater gameUpdater;

    private final GameLoop gameLoop;
    private final GameRenderer gameRenderer;
    private final GamePanel gamePanel;
    private final GameController gameController;
    private final WorldMapManager worldMapManager;
    private final WorldMap worldMap;
    private final NonPlayerCharacterRegistry nonPlayerCharacterRegistry;
    private final NonPlayerCharacterFactory nonPlayerCharacterFactory;

    private final NonPlayerCharacterCollisionService nonPlayerCharacterCollisionService;

    private final TileCollisionDetector tileCollisionDetector;
    private final ObjectCollisionDetector objectCollisionDetector;

    private final NonPlayerCharacterCollisionDetector nonPlayerCharacterCollisionDetector;
    private final KeyControlService keyControlService;
    private final Timer timer;

    public AppConfiguration() {
        // 🔹 Base shared state
        this.registeredKeyState = new RegisteredKeyStateImpl();


        // 🔹 World
        this.worldMap = new WorldMap(WorldMapManagerImpl.loadMapMatrix(),
                                     "worldMap_01");
        this.worldMapManager = new WorldMapManagerImpl(worldMap);

        this.tileCollisionDetector = new TileCollisionDetectorImpl(worldMapManager);
        this.objectCollisionDetector = new ObjectCollisionDetectorImpl(worldMapManager);

        // 🔹 Core services
        this.playerAnimationService = new PlayerAnimationServiceImpl();
        this.playerDrawService = new PlayerDrawServiceImpl();
        this.playerMovementService = new PlayerMovementServiceImpl();
        this.playerCollisionService = new PlayerCollisionServiceImpl(this.tileCollisionDetector,
                                                                     this.objectCollisionDetector);


        this.playerInputReader = new PlayerInputReaderImpl(this.registeredKeyState);

        // 🔹 Entity layer
        this.playerFactory = new PlayerFactoryImpl(this.playerMovementService,
                                                   this.playerInputReader,
                                                   this.playerAnimationService,
                                                   this.playerDrawService,
                                                   this.playerCollisionService);
        this.nonPlayerCharacterCollisionDetector = new NonPlayerCharacterCollisionDetectorImpl();
        this.nonPlayerCharacterCollisionService = new NonPlayerCharacterCollisionServiceImpl(this.tileCollisionDetector,
                                                                                             this.nonPlayerCharacterCollisionDetector);
        this.nonPlayerCharacterFactory = new NonPlayerCharacterFactoryImpl(this.nonPlayerCharacterCollisionService);

        this.nonPlayerCharacterRegistry = new NonPlayerCharacterRegistryImpl(this.nonPlayerCharacterFactory);

        this.entityManager = new EntityManagerImpl(this.playerFactory,
                                                   this.nonPlayerCharacterRegistry);

        // 🔹 UI layer
        this.detailNotifierFactory = new DetailNotifierFactoryImpl(this.entityManager);
        this.detailNotifierRegistry = new DetailNotifierRegistryImpl(this.detailNotifierFactory);
        this.userInterfaceManager = new UserInterfaceManager(this.detailNotifierRegistry);


        // 🔹 Input
        this.keyControlService = new DefaultKeyControlService(this.registeredKeyState);

        this.keyHandler = new KeyHandler(this.registeredKeyState,
                                         this.keyControlService);
        this.readKeyState = keyHandler.readRegisteredKeyState();
        this.uiInputReader = new UIInputReaderImpl(readKeyState);


        this.timer = new Timer(this.keyHandler);

        // 🔹 Game loop
        this.gameUpdater = new GameUpdaterImpl(entityManager,
                                               userInterfaceManager,
                                               uiInputReader);
        this.gameLoop = new GameLoopImpl(gameUpdater);

        // 🔹 Rendering
        this.gameRenderer = new GameRendererImpl(entityManager,
                                                 userInterfaceManager,
                                                 worldMapManager,
                                                 timer);
        this.gamePanel = new GamePanel(gameRenderer,
                                       keyHandler);

        // 🔹 Controller
        this.gameController = new GameControllerImpl(gameLoop,
                                                     gamePanel);
    }


    public RegisteredKeyState getRegisteredKeyState() {
        return registeredKeyState;
    }

    public PlayerAnimationService getPlayerAnimationService() {
        return playerAnimationService;
    }

    public PlayerDrawService getPlayerDrawService() {
        return playerDrawService;
    }

    public PlayerMovementService getPlayerMovementService() {
        return playerMovementService;
    }

    public PlayerCollisionService getPlayerCollisionService() {
        return playerCollisionService;
    }

    public PlayerInputReader getPlayerInputReader() {
        return playerInputReader;
    }

    public PlayerFactory getPlayerFactory() {
        return playerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public DetailNotifierFactoryImpl getDetailNotifierFactory() {
        return detailNotifierFactory;
    }

    public DetailNotifierRegistry getDetailNotifierRegistry() {
        return detailNotifierRegistry;
    }

    public UserInterfaceManager getUserInterfaceManager() {
        return userInterfaceManager;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public ReadKeyState getReadKeyState() {
        return readKeyState;
    }

    public UIInputReader getUiInputReader() {
        return uiInputReader;
    }

    public GameUpdater getGameUpdater() {
        return gameUpdater;
    }

    public GameLoop getGameLoop() {
        return gameLoop;
    }

    public GameRenderer getGameRenderer() {
        return gameRenderer;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public GameController getGameController() {
        return gameController;
    }

    public WorldMapManager getWorldMapManager() {
        return worldMapManager;
    }

    public RegisteredPlayerInput getRegisteredPlayerInput() {
        return this.playerInputReader.readPlayerInput();
    }
}
