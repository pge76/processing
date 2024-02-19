package proc.sketches.solarsystem;

import processing.core.PApplet;


public class SolarSystem extends PApplet {
    private Planet sun;

    public static void main(String... args) {
        PApplet.main(SolarSystem.class);
    }

    public void settings() {
        size(800, 800);
    }

    @Override
    public void setup() {
        sun = new Planet(this, 60, 0, 0, color(255, 255,0), 0);
        sun.spawnMoon(5, 150);
    }

    @Override
    public void draw() {
        translate((float) width / 2, (float) height / 2);
        background(0);
        sun.show();
    }

}
