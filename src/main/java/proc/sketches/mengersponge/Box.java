package proc.sketches.mengersponge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

@Getter
@Setter
@AllArgsConstructor
public class Box {

    PApplet parent;
    PVector pos;
    float r;

    public void show() {
        parent.pushMatrix();
        parent.translate(pos.x, pos.y, pos.z);
        parent.fill(200, 230, 50);
        parent.noStroke();
        parent.box(r);
        parent.popMatrix();
    }

    public List<Box> generate() {
        List<Box> listOfBoxes = new ArrayList<>(3 * 3 * 3);
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                for (int z = -1; z < 2; z++) {
                    float newSize = r / 3.0f;
                    if(abs(x) + abs(y) + abs(z) > 1) {
                        Box b = new Box(parent, new PVector(pos.x + x * newSize, pos.y + y * newSize, pos.z + z * newSize), newSize);
                        listOfBoxes.add(b);
                    }
                }
            }
        }
        return listOfBoxes;
    }
}
