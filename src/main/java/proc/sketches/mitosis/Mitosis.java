package proc.sketches.mitosis;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Collection;

/** Mitosis Simulation
 *  The Coding Train - Challenge #6
 */
public class Mitosis extends PApplet {
    private final ArrayList<Cell> cells = new ArrayList<>();

    public void settings() {
        size(400, 400);
    }

    public static void main(String... args) {
        PApplet.main(Mitosis.class);
    }


    @Override
    public void setup() {
        cells.add(
                new Cell(this,
                        new PVector((float) width / 2, (float) height / 2),
                        80,
                        color(random(255), random(255), random(255), 100)
                )
        );
    }

    @Override
    public void draw() {
        background(51);
        cells.forEach(
                cell -> {
                    cell.move();
                    cell.show();
                }
        );
    }

    @Override
    public void mousePressed() {
        Cell hit = null;
        Collection<? extends Cell> offSprings = new ArrayList<>();
        for (Cell cell : cells) {
            if (cell.clicked(mouseX, mouseY)) {
                hit = cell;
                offSprings = cell.mitosis();
            }
        }
        cells.addAll(offSprings);
        cells.remove(hit);
    }
}
