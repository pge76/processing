package proc.sketches.spaceinvaders;

import processing.core.PApplet;
import processing.core.PImage;

import static proc.sketches.spaceinvaders.InvaderState.FLYING;
import static processing.core.PApplet.radians;

enum InvaderState {FLYING, HIT, DEAD}

public class Invader {
    private final PApplet parent;
    private final float spriteScale;
    public float x, y;
    public InvaderState state = FLYING;
    private PImage sprite;

    public Invader(PApplet parent, PImage sprite, float x, float y, float spriteScale) {
        this.parent = parent;
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.spriteScale = spriteScale;
    }

    public void move() {

    }

    public void draw() {
        parent.imageMode(parent.CENTER);
        parent.pushMatrix(); // remember current drawing matrix)

        parent.translate(this.x, this.y);
        parent.rotate(radians(180)); // rotate 180 degrees
        parent.image(this.sprite, 0, 0, this.sprite.width * spriteScale, this.sprite.height * spriteScale);

        parent.popMatrix(); // restore previous graphics matrix

        // Restore image mode back to default
        parent.imageMode(parent.CORNER);


    }

    public boolean isHit(float x, float y) {
        float spriteWidthMinX = this.x - this.sprite.width / 2.0f * this.spriteScale;
        float spriteWidthMaxX = this.x + this.sprite.width / 2.0f * this.spriteScale;


        float spriteWidthMinY = this.y - this.sprite.height / 2.0f * this.spriteScale;
        float spriteWidthMaxY = this.y + this.sprite.height / 2.0f * this.spriteScale;

        return (x > spriteWidthMinX && x < spriteWidthMaxX && y > spriteWidthMinY && y < spriteWidthMaxY);
    }
}
