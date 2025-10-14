package entity.manager;

public class EntityManagerFactory {
    static class Holder{
        private static final EntityManager INSTANCE= new EntityManagerImpl();
    }

    public static EntityManager getInstance(){
        return Holder.INSTANCE;
    }
}
