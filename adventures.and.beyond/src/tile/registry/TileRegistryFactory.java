package tile.registry;

public class TileRegistryFactory {
    static class Holder {
        private static final TileRegistry INSTANCE = new TileRegistryImpl();
    }

    public static TileRegistry getInstance(){
        return Holder.INSTANCE;
    }
}
