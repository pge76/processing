package proc.sketches.spaceinvaders;

import processing.core.PApplet;
import processing.core.PImage;

public class ImageLoader {

    public static PImage loadFromSpriteMap(PApplet parent, String spriteMapName, int x, int y, int width, int height)  {
        PImage spriteMap = parent.loadImage(spriteMapName);
        return spriteMap.get(x, y, width, height);
    }

}
