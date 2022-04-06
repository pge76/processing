package proc.sketches.spaceinvaders;

import processing.core.PApplet;
import processing.core.PImage;

import static processing.core.PApplet.constrain;

public class Ship {

    public float x, y;
    public float spriteScale = 0.5f;

    private final PApplet parent;
    public PImage sprite;

    public Ship(PApplet parent) {
        this.parent =parent;
        this.loadSprite();
        this.x = parent.width / 2.0f - (this.sprite.width * this.spriteScale / 2.0f);
        this.y = parent.height - 20;
    }

    private void loadSprite() {
        this.sprite = ImageLoader.loadFromSpriteMap(this.parent, "spaceinvaders/spaceships.png", 17, 2968, 226, 276);
    }

    public void draw() {
        parent.image(this.sprite, this.x, this.y - (this.sprite.height * spriteScale), this.sprite.width * spriteScale, this.sprite.height * spriteScale);
    }

    public void move() {
        this.x = constrain(parent.mouseX, 20, parent.width - this.sprite.width * spriteScale - 20);
    }
}
