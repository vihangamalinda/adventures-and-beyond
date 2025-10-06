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
        HashMap<Direction, BufferedImage[]> map = new HashMap<>();


        int imgCount = 5;
        BufferedImage[] backward = getImages(HUMAN_CYAN_MOVING_FACE_BACKWARD, imgCount);
        BufferedImage[] forward = getImages(HUMAN_CYAN_MOVING_FACE_FORWARD, imgCount);
        BufferedImage[] leftward = getImages(HUMAN_CYAN_MOVING_FACE_LEFTWARD, imgCount);
        BufferedImage[] rightward = getImages(HUMAN_CYAN_MOVING_FACE_RIGHTWARD, imgCount);

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
