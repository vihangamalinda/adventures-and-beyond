package object;

public class InteractableObjectManagerFactory {
    static class Holder {
        private static final InteractableObjectManager INSTANCE = new InteractableObjectManagerImpl();
    }

    public static InteractableObjectManager getInstance() {
        return Holder.INSTANCE;
    }
}
