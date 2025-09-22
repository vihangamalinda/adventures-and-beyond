package helper;

import directionEnum.Direction;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import static helper.Loader.getImage;

public class PlayerSpriteManager {
    private static final String[] movementKeys = {
            "face_forward_idle",
            "face_forward_moving",
            "face_backward_idle",
            "face_backward_moving",
            "face_leftward_idle",
            "face_leftward_moving",
            "face_rightward_idle",
            "face_rightward_moving"
    };
    private static final Map<String, BufferedImage[]> spriteMap = new HashMap<>();
    private static final int lastIndex = 5;

    public static void initializeSpriteMap() {
        spriteMap.put(movementKeys[0], getImageArray("forward", "idle"));
        spriteMap.put(movementKeys[1], getImageArray("forward", "walking"));
        spriteMap.put(movementKeys[2], getImageArray("backward", "idle"));
        spriteMap.put(movementKeys[3], getImageArray("backward", "walking"));
        spriteMap.put(movementKeys[4], getImageArray("left", "idle"));
        spriteMap.put(movementKeys[5], getImageArray("left", "walking"));
        spriteMap.put(movementKeys[6], getImageArray("right", "idle"));
        spriteMap.put(movementKeys[7], getImageArray("right", "walking"));
    }

    private static BufferedImage[] getImageArray(String direction, String state) {
        BufferedImage[] arr = new BufferedImage[lastIndex + 1];
        for (int i = 0; i <= lastIndex; i++) {
            arr[i] = getImage(getPath(direction, state, i));
        }
        return arr;
    }


    private static String getPath(String direction, String state, int index) {
        return "/player" + "/" + direction + "/" + state + "/" + index + ".png";
    }

    public static BufferedImage getPlayerImageByIndex(Direction direction, boolean idle, int frameIndex) {
        int keyIndex = getMovementKeyIndex(direction, idle);

        return spriteMap.get(movementKeys[keyIndex])[frameIndex];
    }

    private static int getMovementKeyIndex(Direction direction, boolean idle) {
        int keyIndex = 0;
        if (Direction.FACING_FORWARD.equals(direction)) {
            if (idle) {
                keyIndex = 0;
            } else {
                keyIndex = 1;
            }
        } else if (Direction.FACING_BACKWARD.equals(direction)) {
            if (idle) {
                keyIndex = 2;
            } else {
                keyIndex = 3;
            }
        } else if (Direction.FACING_LEFTWARD.equals(direction)) {
            if (idle) {
                keyIndex = 4;
            } else {
                keyIndex = 5;
            }
        } else if (Direction.FACING_RIGHTWARD.equals(direction)) {
            if (idle) {
                keyIndex = 6;
            } else {
                keyIndex = 7;
            }
        }
        return keyIndex;
    }
}
