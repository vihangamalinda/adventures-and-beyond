package object.interactable.objects.builder;

import object.interactable.objects.DoorObject;

import static java.util.Objects.isNull;

public class DoorObjectBuilder {
    private boolean onCollision;
    private int worldPositionX;
    private int worldPositionY;
    private String doorNumber;
    private String openCode;
    private boolean isActive;

    public DoorObjectBuilder(){}

    public DoorObjectBuilder onCollision(boolean onCollision){
        this.onCollision =onCollision;
        return this;
    }
    public DoorObjectBuilder worldPositionX(int worldPositionX){
        this.worldPositionX=worldPositionX;
        return this;
    }
    public DoorObjectBuilder worldPositionY(int worldPositionY){
        this.worldPositionY=worldPositionY;
        return this;
    }
    public DoorObjectBuilder doorNumber(String doorNumber){
        this.doorNumber=doorNumber;
        return this;
    }
    public DoorObjectBuilder openCode(String openCode){
        this.openCode =openCode;
        return this;
    }
    public DoorObjectBuilder isActive(boolean isActive){
        this.isActive = isActive;
        return this;
    }
    public DoorObject build(){
        validate();
        return  new DoorObject(this.onCollision,this.worldPositionX,this.worldPositionY,this.doorNumber,this.openCode,this.isActive);
    }

    private void validate() {
        if(isNull(this.doorNumber) ||isNull(this.openCode)){
            throw new RuntimeException("Necessary parameters are not fully initialize");
        }
    }
}
