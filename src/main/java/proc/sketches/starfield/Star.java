package proc.sketches.starfield;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static processing.core.PApplet.map;

@Getter
@Setter
public class Star {
    float x, y, z;
    float pz;

    public Star(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pz = z;
    }
}
