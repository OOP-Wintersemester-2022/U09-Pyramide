package Pyramide;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Rectangle;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;


public class Pyramide extends GraphicsApp {

    /* Private Konstanten */
    private static final int CANVAS_HEIGHT = 800;
    private static final int CANVAS_WIDTH = 800;
    private static final int HORIZONTAL_CENTER = CANVAS_WIDTH / 2;
    private static final Color BACKGROUND_COLOR = Colors.BLACK;

    /* Konstanten für die Bricks */
    private final int BRICK_WIDTH = 30;
    private final int BRICK_HEIGHT = 12;
    private final int BRICKS_IN_BASE = 14;
    private final int PYRAMID_ROWS = BRICKS_IN_BASE;
    private final Color BRICK_COLOR = Colors.ORANGE;

    /*
     * Die initialize-Methode wird einmalig zum Start des Programms
     * aufgerufen.
     */

    @Override
    public void initialize() {
        setupCanvas();
    }

    /*
     * Die draw-Methode wird so lange wiederholt aufgerufen, bis das Programm
     * beendet wird.
     */

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        drawPyramid();
    }

    private void setupCanvas() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        drawBackground(BACKGROUND_COLOR);
    }

    /*
     * Für die Pyramide wird zunächst die x-Position des Steins unten links ausgehend von der Fenstermitte berechnet.
     * Dann wird für in einer for-Schleife jede Reihe der Pyramide gezeichnet.
     * Dafür werden x- und y-Position der Reihe sowie die Anzahl der Steine der Reihe berechnet.
     */
    private void drawPyramid() {
        int xPosStart = HORIZONTAL_CENTER - (BRICK_WIDTH * BRICKS_IN_BASE / 2)  ;
        for (int currentRow = 1; currentRow <= PYRAMID_ROWS; currentRow++) {
            int yPos = calcCurrentYPos(currentRow);
            int numBricks = PYRAMID_ROWS - currentRow + 1;
            int xPosRowStart = calcCurrentXPos(currentRow, xPosStart);
            drawPyramidRow(numBricks, xPosRowStart, yPos);
        }
    }

    /*
     * Diese Methode beschreibt das Zeichnen einer einzelnen Reihe der Pyramide.
     * Für jede Reihe wird die Anzahl der Steine in der Reihe und die x- und y-Position des ersten Steins übergeben.
     * In der for-Schleife werden die x-Positionen für die übergebene Anzahl an Steinen berechnet.
     * Die berechnete x-Position und die übergebene y-Position werden als Parameter an drawBrick() übergeben.
     */
    private void drawPyramidRow(int numBricks, int xPosStart, int yPos) {
        for (int j = 0; j < numBricks; j++) {
            int xPosBrickInRow = xPosStart + (j * BRICK_WIDTH);
            drawBrick(xPosBrickInRow, yPos);
        }
    }

    /*
     * In dieser Methode wird ein Stein erstellt und gezeichnet.
     * Es werden die x- und y-Position des Steins übergeben.
     * Für eine kleine Lücke zwischen den Steinen wird ein Border der Breite 1 gesetzt.
     */
    private void drawBrick(int xPos, int yPos){
        Rectangle brick = new Rectangle(xPos, yPos, BRICK_WIDTH, BRICK_HEIGHT, BRICK_COLOR);
        brick.setBorder(BACKGROUND_COLOR, 1);
        brick.draw();
    }

    /*
     * Die y-Positon der übergebenen Reihe wird ausgehend
     * von dem unteren Fensterrand und der Höhe eines Steins berechnet.
     */
    private int calcCurrentYPos(int row){
        return CANVAS_HEIGHT - row * BRICK_HEIGHT;
    }

    /*
     * Die x-Positon des linken Steins in der übergebenen Reihe wird ausgehend
     * von der Startposition des unteren linken Steins und der Breite eines Steins berechnet.
     */
    private int calcCurrentXPos(int row, int xPosStart){
        return xPosStart + ((row -1) * (BRICK_WIDTH / 2));
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}