package proc.sketches.spaceinvaders;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class ShotController {

    private final PApplet parent;
    public final float spriteScale = 0.5f;
    public final PImage sprite;

    private ArrayList<Shot> shots = new ArrayList<>();

    public ShotController(PApplet parent) {
        this.parent = parent;
        this.sprite = ImageLoader.loadFromSpriteMap(parent, "spaceinvaders/spaceships.png", 364, 1459, 40, 72);
    }

    public void addShot(float x, float y) {
        this.shots.add(new Shot(this.parent, sprite, x, y, spriteScale));
    }

    public void move() {
        this.shots.removeIf(s -> s.expired || s.explode);
        this.shots.forEach(Shot::move);
    }

    public void draw() {
        this.shots.forEach(
                shot -> {
                    if (shot.state == ShotState.FLYING) {
                        shot.draw();
                    }
                }
        );
    }

    public ArrayList<Shot> getShots() {
        return shots;
    }
}
