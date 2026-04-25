package collision.detector.service;

import collision.detector.object.ObjectCollisionDetector;
import collision.detector.object.ObjectCollisionDetectorImpl;
import collision.detector.tile.TileCollisionDetector;
import collision.detector.tile.TileCollisionDetectorImpl;
import entity.player.Player;

public class PlayerCollisionServiceImpl implements PlayerCollisionService {
    private final TileCollisionDetector tileCollisionDetector ;
    private final ObjectCollisionDetector objectCollisionDetector;

    public PlayerCollisionServiceImpl(){
        this.tileCollisionDetector= new TileCollisionDetectorImpl();
        this.objectCollisionDetector = new ObjectCollisionDetectorImpl();
    }

    @Override
    public void checkCollision(Player player){
        this.tileCollisionDetector.checkTileCollision(player);
        this.objectCollisionDetector.checkObjectCollision(player);
    }
}
