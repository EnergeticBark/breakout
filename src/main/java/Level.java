import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Level {
    private static final int BRICKS_PER_ROW = 10;
    private static final int ROWS = 7;
    private static final int FIRST_ROW_Y = 40;

    private static final int BRICK_MARGIN = 1; // Pixels of empty space to the left and right of each brick.

    private static final int BRICK_WIDTH = 28; // Brick size
    private static final int BRICK_HEIGHT = 10;

    // All the colors of the rainbow to iterate through as we draw the bricks.
    private static final Color[] RAINBOW_COLORS = {
            Color.RED,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            Color.CYAN,
            Color.BLUE,
            Color.VIOLET,
    };

    /* TODO:
        Might make sense to build the level by positioning each brick automatically and then only specifying the color and whether to place it from top left to bottom right.
        Bricks should be laid out on a fixed grid, that's for sure. Granular control over the pixels gets tedious.
     */
    // Code to make the bricks array
    public static ArrayList<GameObj> initializeLevel() {
        ArrayList<GameObj> bricks = new ArrayList<>(BRICKS_PER_ROW * ROWS);
        int y = FIRST_ROW_Y;
        for (int rowIndex = 0; rowIndex < ROWS; rowIndex += 1) {
            bricks.addAll(createRow(y, RAINBOW_COLORS[rowIndex % 7]));
            y += (BRICK_HEIGHT + BRICK_MARGIN);
        }
        return bricks;
    }

    public static ArrayList<GameObj> createRow(int y, Color c) {
        ArrayList<GameObj> row = new ArrayList<>(BRICKS_PER_ROW);
        for (int brickIndex = 0; brickIndex < BRICKS_PER_ROW; brickIndex += 1) {
            GameObj brick = new GameObj(
                    BRICK_MARGIN + (BRICK_WIDTH + BRICK_MARGIN * 2) * brickIndex,
                    y,
                    BRICK_WIDTH,
                    BRICK_HEIGHT,
                    c);
            row.add(brick);
        }
        return row;
    }
}
