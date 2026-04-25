package object.registry;

public class InteractableObjectRegistryFactory {
    static class Holder {
        private static final InteractableObjectRegistry INSTANCE = new InteractableObjectRegistryImpl();
    }

    public static InteractableObjectRegistry getInstance() {
        return Holder.INSTANCE;
    }
}
