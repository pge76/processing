package proc.sketches.spaceinvaders;

import processing.core.PApplet;

public class Game extends PApplet {
    private Ship ship;
    private InvaderController invaders;
    private ShotController shots;
    private final int numInvaderPerRow = 5;
    private final int numInvaderRows = 4;

    public static void main(String... args) {
        PApplet.main(Game.class);
    }

    public void settings() {
        size(640 * 2, 480 * 2);
    }

    public void setup() {
        ship = new Ship(this);
        invaders = new InvaderController(this);
        shots = new ShotController(this);
        invaders.createInvaders(numInvaderPerRow, numInvaderRows);
    }

    public void draw() {
        background(51);
        stroke(120);

        line(width / 2.0f, 0, width / 2.0f, height);
        line(0, height / 2.0f, width, height / 2.0f);


        line(width - 20, 0, width - 20, height);
        line(0, height - 20, width, height - 20);

        line(20, 0, 20, height);
        line(0, 20, width, 20);


        if (this.frameCount % 10 == 0) {
            invaders.moveRow();
        }
        invaders.draw();

        ship.move();
        shots.move();

        checkForHits();

        shots.draw();
        ship.draw();


    }

    private void checkForHits() {
        shots.getShots().forEach(shot -> {
            float shotTopPosX = shot.x;
            float shotTopPosY = shot.y;

            invaders.getInvaderList().forEach(
                    invader -> {
                        if (shot.state == ShotState.FLYING && invader.state == InvaderState.FLYING && invader.isHit(shotTopPosX, shotTopPosY)) {
                            shot.state = ShotState.HIT;
                            invader.state = InvaderState.HIT;
                        }
                    }
            );
        });
    }

    @Override
    public void mousePressed() {
        createShot();
    }

    private void createShot() {
        float x = ship.x + (ship.spriteScale * ship.sprite.width / 2.0f) - (shots.sprite.width * shots.spriteScale / 2.0f);
        float y = height - 20 - (ship.spriteScale * ship.sprite.height) + 5; // 5px above the ship
        this.shots.addShot(x, y);
    }

}
