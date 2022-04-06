package proc.sketches.spaceinvaders;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class InvaderController {
    private final PApplet parent;
    private ArrayList<Invader> invaderList = new ArrayList<>();

    private float spriteScale = 0.5f;
    private int corner = 20;

    public InvaderController(PApplet parent) {
        this.parent = parent;
    }

    public void createInvaders(int numInvaderPerRow, int numInvaderRows) {
        PImage sprite = ImageLoader.loadFromSpriteMap(this.parent, "spaceinvaders/spaceships.png", 62, 70, 133, 104);

        System.out.printf("width | height:  [%f | %f]\n", parent.width * 1.0f, parent.height * 1.0f);

        float distance = (parent.width - corner * 2.0f) / numInvaderPerRow;

        for (int i = 0; i < numInvaderPerRow; i++) {
            for (int j = 0; j < numInvaderRows; j++) {
                Invader invader = new Invader(parent, sprite, distance / 2.0f + (distance * i) + corner, corner + j * 60 + (sprite.height * spriteScale / 2.0f), spriteScale);
                //System.out.printf("adding invader at [%f, %f]\n", invader.x, invader.y);
                //System.out.printf("distance [%f]\n", distance/2.0f + (distance * i) + corner);
                invaderList.add(invader);
            }
        }
    }

    public void moveRow() {

    }

    public void draw() {
        invaderList.forEach(
                invader -> {
                    if (invader.state == InvaderState.FLYING) {
                        invader.draw();
                    }
                }
        );
    }

    public ArrayList<Invader> getInvaderList() {
        return invaderList;
    }
}
