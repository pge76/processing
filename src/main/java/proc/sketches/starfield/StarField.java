package proc.sketches.starfield;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;


public class StarField extends PApplet {

    private static final int NUM_STARS = 1000;
    private int speed = 20;
    private static final int DEPTH = 1000;

    private final List<Star> stars = new ArrayList<>(NUM_STARS);

    public void settings() {
        size(1920, 1080);
    }

    public void setup() {
        for (int i = 0; i < NUM_STARS; i++) {
            stars.add(new Star(random(-width, width), random(-height, height), random(100, DEPTH)));
        }
    }

    @Override
    public void keyPressed() {

        if (key == 'a') {
            speed += 1;
        }

        if(key == 'y') {
            speed -= 1;
        }


    }


    public void draw() {
        translate(width / 2.0f, height / 2.0f);
        background(0);
        fill(255);
        noStroke();

        for (int i = 0; i < NUM_STARS; i++) {
            Star star = stars.get(i);
            star.pz = star.z;
            star.z -= speed;

            float sx = map(star.x / star.z, 0, 1, 0, width / 2.0f);
            float sy = map(star.y / star.z, 0, 1, 0, height / 2.0f);

            float r = map(star.z, 0, DEPTH, 8, 0.1f);

            //System.out.printf("star[%d] x: %f, y: %f, sx %f, sy %f, z %f%n", i, star.x, star.y, sx, sy, star.z);

            ellipse(sx, sy, r, r);
            addTails(star, sx, sy);


            if (sx <= -width || sx >= width || sy <= -height || sy >= height || star.z < speed) {
                star.x = random(-width, width);
                star.y = random(-height, height);
                star.z = DEPTH;
            }
        }
    }

    private void addTails(Star star, float sx, float sy) {
        float px = map(star.x / star.pz, 0, 1, 0, width / 2.0f);
        float py = map(star.y / star.pz, 0, 1, 0, height / 2.0f);

        stroke(255);
        line(px, py, sx, sy);
        //System.out.printf("sx %f sy %f px %f py %f%n", sx, sy, px, py);
    }

    public static void main(String... args) {
        PApplet.main(StarField.class);
    }
}

