package proc.sketches.gameoflife;

import processing.core.PApplet;

import java.util.Arrays;

public class GameOfLife extends PApplet {
    private static final int DIMENSION_X = 100;
    private static final int DIMENSION_Y = 80;
    private static final int GRIDSIZE = 15;

    private int generation = 0;
    private boolean animate = false;

    private int[][] grid = new int[DIMENSION_X][DIMENSION_Y];
    private int[][] nextGenGrid = new int[DIMENSION_X][DIMENSION_Y];

    public void settings() {
        size(DIMENSION_X * GRIDSIZE, DIMENSION_Y * GRIDSIZE);
    }


    public void setup() {
        for (int i = 0; i < DIMENSION_X; i++) {
            for (int j = 0; j < DIMENSION_Y; j++) {
                grid[i][j] = 0;
            }
        }
        background(51);
        frameRate(20);
    }

    @Override
    public void mousePressed() {
        int xGrid = mouseX / GRIDSIZE;
        int yGrid = mouseY / GRIDSIZE;

        flipGridPos(xGrid, yGrid);
    }

    private void flipGridPos(int xGrid, int yGrid) {
        if (grid[xGrid][yGrid] == 1) {
            grid[xGrid][yGrid] = 0;
        } else if (grid[xGrid][yGrid] == 0) {
            grid[xGrid][yGrid] = 1;
        }
    }

    @Override
    public void keyPressed() {
        if (key == ' ') {
            doGenerateNextGen();
        }

        if (key == 's') {
            animate = !animate;
        }

        if (key == 'x') {
            doClearBoard();
        }

        if (key == '1') {
            doClearBoard();
            doCreateGliderGun(3, 3, false);
        }

        if (key == '2') {
            doClearBoard();
            doCreateGliderGun(3, 3, false);
            doCreateGliderGun(60, 3, true);
        }

    }

    private void doCreateGliderGun(int x, int y, boolean inverse) {
        int dimGun = inverse ? 35 : 0;

        if (DIMENSION_X >= 50 && DIMENSION_Y >= 50) {
            grid[x + abs(dimGun)][y + 5] = 1;
            grid[x + abs(dimGun)][y + 6] = 1;

            grid[x + abs(dimGun - 1)][y + 5] =1;
            grid[x + abs(dimGun - 1)][y + 6] =1;

            grid[x + abs(dimGun - 10)][y + 5] = 1;
            grid[x + abs(dimGun - 10)][y + 6] = 1;
            grid[x + abs(dimGun - 10)][y + 7] = 1;

            grid[x + abs(dimGun - 11)][y + 4] = 1;
            grid[x + abs(dimGun - 11)][y + 8] = 1;

            grid[x + abs(dimGun - 12)][y + 3] = 1;
            grid[x + abs(dimGun - 12)][y + 9] = 1;

            grid[x + abs(dimGun - 13)][y + 3] = 1;
            grid[x + abs(dimGun - 13)][y + 9] = 1;

            grid[x + abs(dimGun - 14)][y + 6] = 1;

            grid[x + abs(dimGun - 15)][y + 4] = 1;
            grid[x + abs(dimGun - 15)][y + 8] = 1;

            grid[x + abs(dimGun - 16)][y + 5] = 1;
            grid[x + abs(dimGun - 16)][y + 6] = 1;
            grid[x + abs(dimGun - 16)][y + 7] = 1;

            grid[x + abs(dimGun - 17)][y + 6] = 1;

            grid[x + abs(dimGun - 20)][y + 3] = 1;
            grid[x + abs(dimGun - 20)][y + 4] = 1;
            grid[x + abs(dimGun - 20)][y + 5] = 1;

            grid[x + abs(dimGun - 21)][y + 3] = 1;
            grid[x + abs(dimGun - 21)][y + 4] = 1;
            grid[x + abs(dimGun - 21)][y + 5] = 1;

            grid[x + abs(dimGun - 22)][y + 2] = 1;
            grid[x + abs(dimGun - 22)][y + 6] = 1;

            grid[x + abs(dimGun - 24)][y + 1] = 1;
            grid[x + abs(dimGun - 24)][y + 2] = 1;
            grid[x + abs(dimGun - 24)][y + 6] = 1;
            grid[x + abs(dimGun - 24)][y + 7] = 1;

            grid[x + abs(dimGun - 34)][y + 3] = 1;
            grid[x + abs(dimGun - 34)][y + 4] = 1;

            grid[x + abs(dimGun - 35)][y + 3] = 1;
            grid[x + abs(dimGun - 35)][y + 4] = 1;
        }
    }

    private void doClearBoard() {
        for (int i = 0; i < DIMENSION_X; i++) {
            for (int j = 0; j < DIMENSION_Y; j++) {
                grid[i][j] = 0;
            }
        }
        generation = 0;
    }

    public void draw() {
        drawGameOfLife();
        if (animate) {
            doGenerateNextGen();
        }
        displayText();
    }

    private void displayText() {
        String text;
        if(animate) {
            fill(0, 255,0);
            text = "playing";
        } else {
            fill(255, 0,0);
            text = "stopped";
        }
        textSize(15);
        text(text, width - 70, 30);
        fill(100, 100, 255);
        text("gen: " + generation, width - 70, 50);

    }

    private void drawGameOfLife() {
        for (int i = 0; i < DIMENSION_X; i++) {
            for (int j = 0; j < DIMENSION_Y; j++) {
                if (grid[i][j] == 1) {
                    fill(0, 255, 0);
                }
                if (grid[i][j] == 0) {
                    fill(0);
                }
                if (grid[i][j] == 2) {
                    fill(255, 0, 0);
                }
                rect(i * GRIDSIZE, j * GRIDSIZE, GRIDSIZE, GRIDSIZE);
            }
        }
    }

    private void doGenerateNextGen() {
        nextGenGrid = copyArrayFrom(grid);

        for (int i = 0; i < DIMENSION_X; i++) {
            for (int j = 0; j < DIMENSION_Y; j++) {
                doGenerateNextGenForGridpos(i, j);
            }
        }
        grid = copyArrayFrom(nextGenGrid);
        generation++;
    }

    private int[][] copyArrayFrom(int[][] grid) {
        int[][] copy = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++)
            System.arraycopy(grid[i], 0, copy[i], 0, grid[i].length);
        return copy;
    }

    private void doGenerateNextGenForGridpos(int x, int y) {
        //System.out.printf("x,y: %d|%d%n", x, y);
        int cnt = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (!(i == 0 && j == 0)) {
                    // overflow mit modulus über GRIDSIZE umgesetzt
                    int checkRelativeX = (x + DIMENSION_X + i) % DIMENSION_X;
                    int checkRelativeY = (y + DIMENSION_Y + j) % DIMENSION_Y;
                    // System.out.printf("checkRelative x,y: %d|%d ==> %d%n", checkRelativeX, checkRelativeY, grid[checkRelativeX][checkRelativeY]);
                    if (grid[checkRelativeX][checkRelativeY] == 1) {
                        cnt++;
                    }
                }
            }
        }

        //System.out.printf("x,y: %d|%d - cnt: %d %n", x, y, cnt);
        // Eine tote Zelle mit genau drei lebenden Nachbarn wird in der Folgegeneration neu geboren.
        if (cnt == 3) {
            nextGenGrid[x][y] = 1;
        }

        // Lebende Zellen mit weniger als zwei lebenden Nachbarn sterben in der Folgegeneration an Einsamkeit.
        if (cnt < 2) {
            nextGenGrid[x][y] = 0;
        }

        // Lebende Zellen mit mehr als drei lebenden Nachbarn sterben in der Folgegeneration an Überbevölkerung.
        if (cnt > 3) {
            nextGenGrid[x][y] = 0;
        }
    }

    public static void main(String... args) {
        PApplet.main(GameOfLife.class);
    }
}