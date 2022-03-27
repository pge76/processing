package proc.sketches.purplerain;

import processing.core.PApplet;

public class PurpleRain extends PApplet {

    Drop[] drops;

    int maxDrops = 400;

    public static void main(String... args) {
        PApplet.main(PurpleRain.class);
    }

    public void settings() {
        size(640, 400);
    }

    public void setup() {
        drops = new Drop[maxDrops];
        for (int i = 0; i < maxDrops; i++) {
            drops[i] = new Drop(this, random(width), random(height), random(0f, 20f));
        }
    }

    public void draw() {
        background(200);
        for (int i = 0; i < maxDrops; i++) {
            drops[i].show();
            drops[i].fall();
        }
    }

}
