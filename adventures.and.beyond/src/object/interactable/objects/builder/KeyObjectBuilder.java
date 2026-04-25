package object.interactable.objects.builder;

import object.interactable.objects.KeyObject;

import static java.util.Objects.isNull;

public class KeyObjectBuilder {
    private boolean onCollision;
    private int worldPositionX;
    private int worldPositionY;
    private String keyCode;
    private boolean isActive;

    public KeyObjectBuilder onCollision(boolean onCollision) {
        this.onCollision = onCollision;
        return this;
    }

    public KeyObjectBuilder worldPositionX(int worldPositionX) {
        this.worldPositionX = worldPositionX;
        return this;
    }

    public KeyObjectBuilder worldPositionY(int worldPositionY) {
        this.worldPositionY = worldPositionY;
        return this;
    }

    public KeyObjectBuilder keyCode(String keyCode) {
        this.keyCode = keyCode;
        return this;
    }

    public KeyObjectBuilder isActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public KeyObject build() {
        validate();
        return new KeyObject(this.onCollision, this.worldPositionX, this.worldPositionY, this.keyCode, this.isActive);
    }

    private void validate() {
        if (isNull(this.keyCode)) {
            throw new RuntimeException("Necessary parameters are not fully initialize");
        }
    }
}
