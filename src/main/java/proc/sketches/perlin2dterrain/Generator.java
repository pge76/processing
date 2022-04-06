package proc.sketches.perlin2dterrain;

import processing.core.PApplet;

public class Generator extends PApplet {


    private final int scale = 20;
    private final int w = 2000;
    private final int h = 1200;
    private final int cols = w / scale, rows = h / scale;
    private final float[][] terrain = new float[cols][rows];
    private final float noiseFactor = 0.2f;
    private float flying = 0.1f;

    public void settings() {
        size(600, 600, P3D);
    }

    public void setup() {
        makeNoiseField(0.0f);
    }

    private void makeNoiseField(float speed) {
        float yOff = speed;
        for (int y = 0; y < rows; y++) {
            float xOff = 0;
            for (int x = 0; x < cols; x++) {
                terrain[x][y] = map(noise(xOff,yOff), 0, 1, -50, 50);
                xOff += noiseFactor;
            }
            yOff += noiseFactor;
        }
    }

    @Override
    public void mousePressed() {
    }

    @Override
    public void keyPressed() {
    }

    public void draw() {
        background(0);
        stroke(255);
        noFill();

        makeNoiseField(flying);
        flying -= 0.1;
        translate(width / 2.0f, height / 2.0f);
        rotateX(PI / 2.2f);
        translate(-w / 2.0f, -h / 2.0f);


        for (int y = 0; y < rows - 1; y++) {
            beginShape(TRIANGLE_STRIP);
            for (int x = 0; x < cols; x++) {
                vertex(x * scale, y * scale, terrain[x][y]);
                vertex(x * scale, (y + 1) * scale, terrain[x][y + 1]);
            }
            endShape();
        }

    }

    public static void main(String... args) {
        PApplet.main(Generator.class);
    }
}
