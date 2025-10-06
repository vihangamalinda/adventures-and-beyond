package entity.npc.sprite;

import directionEnum.Direction;
import helper.Loader;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import static helper.ImageScaler.getStandardScaledImage;

public class CharacterSpriteRegistry {

    private final HashMap<String,CharacterSprite> characterSpriteHashMap;

    private static class Holder{
        private static final CharacterSpriteRegistry INSTANCE = new CharacterSpriteRegistry();
    }

    public CharacterSpriteRegistry(){
        this.characterSpriteHashMap = new HashMap<>();
        this.registerCharacterSprites();
    }

    public static CharacterSpriteRegistry getInstance(){
        return Holder.INSTANCE;
    }

    public HashMap<String,CharacterSprite> getCharacterSpriteHashMap(){
        return this.characterSpriteHashMap;
    }

    private void registerCharacterSprites() {
        this.registerHumanCyanSprites();
    }
    private void registerHumanCyanSprites(){
        this.registerHumanCyanMovingSprites();
    }

    private void registerHumanCyanMovingSprites() {
        String mainFolderPath = "/entity/character/human_cyan";
        HashMap<Direction, BufferedImage[]> map = new HashMap<>();

        String faceBackward = mainFolderPath + "/face_backward_moving";
        String faceForward = mainFolderPath + "/face_forward_moving";
        String faceLeftForward = mainFolderPath + "/face_leftward_moving";
        String faceRightForward = mainFolderPath + "/face_rightward_moving";

        int imgCount = 5;
        BufferedImage[] backward = getImages(faceBackward, imgCount);
        BufferedImage[] forward = getImages(faceForward, imgCount);
        BufferedImage[] leftward = getImages(faceLeftForward, imgCount);
        BufferedImage[] rightward = getImages(faceRightForward, imgCount);

        map.put(Direction.FACING_BACKWARD, backward);
        map.put(Direction.FACING_FORWARD, forward);
        map.put(Direction.FACING_LEFTWARD, leftward);
        map.put(Direction.FACING_RIGHTWARD, rightward);

       CharacterSprite characterSprite = new CharacterSprite(CharacterSpriteKey.HUMAN_CYAN, map, imgCount);
       this.characterSpriteHashMap.put(characterSprite.getName(),characterSprite);
    }

    private BufferedImage[] getImages(String folderPath,int imgCount) {
        BufferedImage[] imgArr=new BufferedImage[imgCount];
        for(int i = 0; i< imgCount; i++){
            String imgPath =String.format("/%d.png",i);
//            Need to call scaling
            BufferedImage image = getImage(folderPath +imgPath);
            imgArr[i]=image;
        }
        return imgArr;
    }

    private BufferedImage getImage(String imgPath){
        BufferedImage image =Loader.getImage(imgPath);
        return getStandardScaledImage(image);
    }
}
