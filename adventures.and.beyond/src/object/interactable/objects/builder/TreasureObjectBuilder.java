package object.interactable.objects.builder;

import object.interactable.objects.TreasureObject;

import static java.util.Objects.isNull;

public class TreasureObjectBuilder {
   private boolean onCollision;
   private int worldPositionX;
   private int worldPositionY;
   private String treasureNumber;
   private String openCode;
   private boolean isActive;

   public TreasureObjectBuilder onCollision(boolean onCollision){
       this.onCollision = onCollision;
       return this;
   }
    public TreasureObjectBuilder worldPositionX(int worldPositionX){
        this.worldPositionX = worldPositionX;
        return this;
    }
    public TreasureObjectBuilder worldPositionY(int worldPositionY){
        this.worldPositionY = worldPositionY;
        return this;
    }
    public TreasureObjectBuilder treasureNumber(String treasureNumber){
        this.treasureNumber = treasureNumber;
        return this;
    }
    public TreasureObjectBuilder openCode(String openCode){
        this.openCode = openCode;
        return this;
    }
    public TreasureObjectBuilder isActive(boolean isActive){
        this.isActive = isActive;
        return this;
    }
    public TreasureObject build(){
        validate();
        return new TreasureObject(this.onCollision,this.worldPositionX,this.worldPositionY,this.treasureNumber,this.openCode,this.isActive);
    }

    private void validate() {
        if(isNull(treasureNumber) || isNull(openCode)){
           throw new  RuntimeException("Necessary parameters are not fully initialize");
        }
    }
}
