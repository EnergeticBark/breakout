import javafx.scene.paint.Color;

public class Level {
    private static final int BRICKS_PER_ROW = 10;
    private static final int ROWS = 7;

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

    // Code to make the bricks array
    public static GameObj[] initializeLevel() {
        GameObj[] bricks = new GameObj[BRICKS_PER_ROW * ROWS];
        for (int rowIndex = 0; rowIndex < ROWS; rowIndex += 1) {
            for (int brickIndex = 0; brickIndex < BRICKS_PER_ROW; brickIndex += 1) {
                bricks[(rowIndex * BRICKS_PER_ROW) + brickIndex] = new GameObj(
                        BRICK_MARGIN + (BRICK_WIDTH + BRICK_MARGIN * 2) * brickIndex,
                        40 + (BRICK_HEIGHT + BRICK_MARGIN) * rowIndex,
                        BRICK_WIDTH,
                        BRICK_HEIGHT,
                        RAINBOW_COLORS[rowIndex % 7]
                );

            }
        }
        return bricks;
    }
}
