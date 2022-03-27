package proc.sketches.purplerain;

import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;

import static processing.core.PApplet.map;

@Getter
@Setter
public class Drop {
    private float length;
    private float thickness;
    private float ySpeed;
    private PApplet parent;
    private float x, y, z;

    public Drop(PApplet parent, float x, float y, float z) {
        this.parent = parent;
        this.x = x;
        this.y = y;
        this.z = z;

        resetDrop(z);
    }

    private void resetDrop(float z) {
        this.ySpeed = map(z, 0f, 20f, 2f, 8f);
        this.thickness = map(z, 0f, 20f, 1f, 3f);
        this.length = map(z, 0f, 20f, 10f, 20f);
    }

    public void fall() {
        y += this.ySpeed + 2f;
        if (y >= parent.height) {
            x = parent.random(parent.width);
            y = 0;
            resetDrop(parent.random(0f, 20f));
        }
    }

    public void show() {
        parent.stroke(138, 43, 226);
        parent.strokeWeight(thickness);
        parent.line(x, y, x, y + length);
    }
}
