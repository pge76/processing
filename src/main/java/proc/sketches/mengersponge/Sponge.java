package proc.sketches.mengersponge;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;


public class Sponge extends PApplet {

    List<Box> sponge = new ArrayList<>();

    float a = 0;

    public void settings() {
        size(400, 400, P3D);
    }

    public void setup() {
        Box b = new Box(this, new PVector(0,0), 200);
        sponge.add(b);
    }

    @Override
    public void mousePressed() {
        if(sponge.size() < 2000) { // random value to prevent too many iterations
            List<Box> nextGen = new ArrayList<>();
            for (Box b : sponge) {
                List<Box> newBoxes = b.generate();
                nextGen.addAll(newBoxes);
            }
            sponge = nextGen;
        }
    }

    @Override
    public void keyPressed() {
    }

    public void draw() {
        background(51);
        stroke(255);
        translate(width / 2.0f, height / 2.0f);
        noFill();
        lights();

        rotateX(a);
        rotateY(a);

        for (Box b: sponge) {
            b.show();
        }

        a+= 0.01;
    }

    public static void main(String... args) {
        PApplet.main(Sponge.class);
    }
}

