package proc.sketches.snake;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

import static processing.core.PApplet.constrain;


class Food {
    private final PApplet parent;
    public int x;
    public int y;

    public Food(PApplet parent, int gridSize) {
        this.parent = parent;
        this.x = ((int) parent.random(0, parent.width / (gridSize + 0.0f))) * gridSize;
        this.y = ((int) parent.random(0, parent.height / (gridSize + 0.0f))) * gridSize;
    }

    void show() {
        parent.fill(0, 255, 0);
        parent.rect(this.x, this.y, 10, 10);
    }
}


class SnakeBody {
    private final PApplet parent;
    private int gridSize;
    private int xSpeed = 1;
    private int ySpeed = 0;
    private int length = 1;
    private List<PVector> tail = new ArrayList<>();


    SnakeBody(PApplet parent, int gridSize) {
        this.parent = parent;
        this.gridSize = gridSize;
        tail.add(new PVector(0, 0));
    }

    void update() {

        for (int i = 0; i < tail.size(); i++) {
            System.out.printf("%d -> %f:%f \t %d%n", i, tail.get(i).x, tail.get(i).y, this.length);
        }
        if(length > tail.size()) {
            this.tail.add(new PVector(this.tail.get(this.tail.size() - 1).x, this.tail.get(this.tail.size() - 1).y));
        }
        for( var i = this.tail.size() - 1; i > 0; i--) {
            this.tail.get(i).x = this.tail.get(i - 1).x;
            this.tail.get(i).y = this.tail.get(i - 1).y;
        }

        this.tail.get(0).x = this.tail.get(0).x + this.xSpeed * gridSize;
        this.tail.get(0).y = this.tail.get(0).y + this.ySpeed * gridSize;

        this.tail.get(0).x = constrain(this.tail.get(0).x, 0, parent.width - gridSize);
        this.tail.get(0).y = constrain(this.tail.get(0).y, 0, parent.height - gridSize);
    }

    void show() {
        parent.background(51);
        parent.fill(255);

        //parent.rect(this.x, this.y, gridSize, gridSize);
        tail.forEach(
                b -> parent.rect(b.x, b.y, gridSize, gridSize)
        );

    }

    public boolean isMovingUp() {
        return this.ySpeed == -1;
    }

    public boolean isMovingDown() {
        return this.ySpeed == 1;
    }

    public void moveDown() {
        this.ySpeed = 1;
        this.xSpeed = 0;
    }

    public void moveUp() {
        this.ySpeed = -1;
        this.xSpeed = 0;
    }

    public boolean isMovingLeft() {
        return this.xSpeed == -1;
    }

    public boolean isMovingRight() {
        return this.xSpeed == 1;
    }

    public void moveLeft() {
        this.ySpeed = 0;
        this.xSpeed = -1;
    }

    public void moveRight() {
        this.ySpeed = 0;
        this.xSpeed = 1;
    }

    public boolean isOnTopOf(Food f) {
        return this.tail.get(0).x == f.x && this.tail.get(0).y == f.y;
    }

    public void eat(Food f) {
        this.length++;
    }
}

public class Snake extends PApplet {
    SnakeBody sb;
    int gridSize = 10;
    private Food f;

    public void settings() {
        size(400, 400, P3D);
    }

    public void setup() {
        background(51);
        frameRate(5);
        sb = new SnakeBody(this, gridSize);
        f = new Food(this, gridSize);
    }

    @Override
    public void mousePressed() {
    }

    @Override
    public void keyPressed() {
        if (key == 'w' && !sb.isMovingDown()) {
            sb.moveUp();
        }
        if (key == 's' && !sb.isMovingUp()) {
            sb.moveDown();
        }
        if (key == 'a' && !sb.isMovingRight()) {
            sb.moveLeft();
        }
        if (key == 'd' && !sb.isMovingLeft()) {
            sb.moveRight();
        }
    }

    public void draw() {
        sb.update();
        sb.show();
        f.show();
        if (sb.isOnTopOf(f)) {
            sb.eat(f);
            f = new Food(this, gridSize);
        }
    }

    public static void main(String... args) {
        PApplet.main(Snake.class);
    }
}

