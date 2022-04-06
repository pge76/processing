package proc.sketches.spaceinvaders;

import processing.core.PApplet;
import processing.core.PImage;

import static proc.sketches.spaceinvaders.ShotState.FLYING;

enum ShotState {FLYING, HIT, DONE}

public class Shot {
    private final PImage sprite;
    private final PApplet parent;
    private float spriteScale;
    private float speed = 5.0f;
    public ShotState state = FLYING;
    public float x;
    public float y;
    public boolean expired = false;
    public boolean explode = false;

    public Shot(PApplet parent, PImage shotSprite, float x, float y, float spriteScale) {
        this.x = x;
        this.y = y;
        this.sprite = shotSprite;
        this.parent = parent;
        this.spriteScale = spriteScale;
    }

    public void draw() {
        this.parent.image(this.sprite, this.x, this.y - (this.sprite.height * this.spriteScale), this.sprite.width * this.spriteScale, this.sprite.height * spriteScale);
    }

    public void move() {
        this.y -= speed;
        if (this.y < 0) {
            this.expired = true;
        }
    }
}
