package proc.sketches.solarsystem;

import processing.core.PApplet;

import java.util.ArrayList;

public class Planet {
    private final PApplet parent;
    private final float radius;
    private float angle;
    private final float distance;
    private final int color;
    private final float speed;
    private final ArrayList<Planet> planets;

    public Planet(PApplet parent, float radius, float angle, float distance, int color, float speed) {
        this.planets = new ArrayList<>();
        this.parent = parent;
        this.radius = radius;
        this.angle = angle;
        this.distance = distance;
        this.speed = speed;
        this.color= color;
    }

    public void spawnMoon(int total, int distance) {
        for (int i = 0; i < total; i++) {
            float r = parent.random(radius * 0.1f, radius * 0.4f);
            float d = parent.random((float) distance / 2, distance * 2);
            float a = parent.random(PApplet.TWO_PI);
            float s = parent.random(-0.03f, 0.03f);
            Planet moon = new Planet(parent, r, a, d, parent.color(200, 200, 200), s);
            moon.spawnMoon((int) parent.random(0, 2), 30);
            this.planets.add(moon);
        }
    }

    public void orbit() {
        angle += speed;
    }

    public void show() {
        parent.pushMatrix();
        parent.fill(color);
        parent.rotate(angle);
        parent.translate(distance, 0);
        parent.ellipse(0, 0, radius * 2, radius* 2);

        this.planets.forEach(
                planet -> {
                    planet.orbit();
                    planet.show();
                }
        );
        parent.popMatrix();
    }
}
