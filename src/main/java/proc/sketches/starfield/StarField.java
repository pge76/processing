package proc.sketches.starfield;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;


public class StarField extends PApplet {

    private static final int NUM_STARS = 300;
    public static final int TAIL_LENGTH = 20;
    public static final int SPEED = 5;
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 800;

    private final List<Star> stars = new ArrayList<>(NUM_STARS);

    public void settings() {
        size(CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    public void setup() {
        for (int i = 0; i < NUM_STARS; i++) {
            stars.add(new Star(random(-width / 2.0f, width / 2.0f), random(-height / 2.0f, height / 2.0f), random(0, width)));
        }
    }

    public void draw() {
        translate(width / 2.0f, height / 2.0f);
        background(0);
        fill(255);
        noStroke();

        for (int i = 0; i < NUM_STARS; i++) {
            Star star = stars.get(i);
            star.pz = star.z + TAIL_LENGTH;
            star.z -= SPEED;

            float sx = map(star.x / star.z, 0, 1, 0, width);
            float sy = map(star.y / star.z, 0, 1, 0, height);

            if (star.z < 1) {
                star.x = random(-width / 2.0f, width / 2.0f);
                star.y = random(-height / 2.0f, height / 2.0f);
                star.z = width;
            }

            float r = map(star.z, width, 0, 1, 8);


            ellipse(sx, sy, r, r);

            //addTails(star, sx, sy);
            //System.out.printf("px %f py %f sx %f sy %f%n", px, py, sx, sy);

        }

    }

    private void addTails(Star star, float sx, float sy) {
        float px = map(star.x / star.pz, 0, 1, 0, width);
        float py = map(star.y / star.pz, 0, 1, 0, height);

        stroke(255);
        line(px, py, sx, sy);
    }

    public static void main(String... args) {
        PApplet.main("proc.sketches.starfield.StarField");
    }
}

