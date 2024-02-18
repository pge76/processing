package proc.sketches.mitosis;


import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Collection;

import static processing.core.PApplet.*;

public class Cell {
    private final PApplet parent;
    private final int size;
    private final int color;
    private final PVector position;


    public Cell(PApplet parent, PVector position, int size, int color) {
        this.position = position;
        this.size = size;
        this.parent = parent;
        this.color = color;
    }

    public void move() {
        PVector velocity = PVector.random2D();
        position.add(velocity);
    }

    public void show() {
        parent.fill(color);
        parent.noStroke();
        parent.ellipse(position.x, position.y, size, size);
    }

    public boolean clicked(int mouseX, int mouseY) {
        float d = dist(position.x, position.y, mouseX, mouseY);
        return d < (float) size / 2;
    }

    public Collection<? extends Cell> mitosis() {
        Collection<Cell> cells = new ArrayList<>();
        // xOffset1 is a random position on the cells outer radius

        float angle = parent.random(TWO_PI);
        PVector offset1 = PVector.fromAngle(angle);
        offset1.mult((float) size / 2);
        offset1.add(position);

        PVector offset2 = PVector.fromAngle(angle + PI);
        offset2.mult((float) size / 2);
        offset2.add(position);

        System.out.println("color" + color);

        int r = (color >> 16) & 0xFF; // Extract red component
        int g = (color >> 8) & 0xFF;  // Extract green component
        int b = color & 0xFF;         // Extract blue component
        System.out.println("rgb:" + r + " " + g + " " + b);

        // Add or subtract a small random value to each component
        r = constrain(r + (int) (parent.random(-30, 30)), 0, 255);
        g = constrain(g + (int) (parent.random(-30, 30)), 0, 255);
        b = constrain(b + (int) (parent.random(-30, 30)), 0, 255);

        System.out.println("rgb after:" + r + " " + g + " " + b);

        // Combine the components back into a single color
        int nearColor = parent.color(r, g, b, 100);

        cells.add(new Cell(parent,  offset1, (int) (size * 0.9f), nearColor));
        cells.add(new Cell(parent,  offset2, (int) (size * 0.9f), nearColor));
        return cells;
    }
}
