package world.manager;

public class WorldMapManagerFactory {
    static class Holder {
        private static final WorldMapManager INSTANCE = new WorldMapManagerImpl();
    }

    public static WorldMapManager getInstance() {
        return Holder.INSTANCE;
    }
}
