package object.registry;

import object.interactable.objects.DoorObject;
import object.interactable.objects.InteractableObject;
import object.interactable.objects.KeyObject;
import object.interactable.objects.TreasureObject;

import java.util.HashMap;

import static helper.Constant.TILE_SIZE;

public class InteractableObjectRegistryImpl implements InteractableObjectRegistry{
    private HashMap<String, InteractableObject[]> interactableObjectMap;

    public InteractableObjectRegistryImpl(){
        this.interactableObjectMap = new HashMap<>();
        this.register();
    }
    private void register(){
        this.interactableObjectMap.put("worldMap_01",this.createWorldMapOneInteractableObjects());
    }
    private  InteractableObject[] createWorldMapOneInteractableObjects(){

        String keyCode01 = "KEY_07";
        InteractableObject key = KeyObject.getBuilder().onCollision(false).worldPositionX(28 * TILE_SIZE).worldPositionY(17 * TILE_SIZE).keyCode(keyCode01).isActive(true).build();
        InteractableObject door = DoorObject.getBuilder().onCollision(false).worldPositionX(22 * TILE_SIZE).worldPositionY(25 * TILE_SIZE).doorNumber("DOOR_5").openCode(keyCode01).isActive(true).build();

        String keyCode02 = "KEY_08";
        InteractableObject treasure = TreasureObject.getBuilder().onCollision(false).worldPositionX(22 * TILE_SIZE).worldPositionY(27 * TILE_SIZE).treasureNumber("TREASURE_05").openCode(keyCode02).isActive( true).build();
        InteractableObject key2 =KeyObject.getBuilder().onCollision(false).worldPositionX(16 * TILE_SIZE).worldPositionY(11 * TILE_SIZE).keyCode(keyCode02).isActive(true).build();

        return new InteractableObject[]{key,door,treasure,key2};
    }
    @Override
    public HashMap<String, InteractableObject[]> getRegisteredInteractableObjectMap(){
        return this.interactableObjectMap;
    }


}
