import javafx.scene.SnapshotParameters;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * A set of static methods for creating a grid of multicolored bricks.
 * @author Seth Humphries
 * @version 1.0
 */
class Level {
    private static final int FIRST_ROW_Y = 40; // Leave 40px of vertical padding for the score counter.

    private static final int COLUMNS = 10;
    private static final int ROWS = 7;

    private static final Vector2 BRICK_SIZE = new Vector2(30, 10);

    // All the colors of the rainbow. Used to generate different colored brick sprites.
    private static final double[] RAINBOW_HUES = {
            0.0,        // RED
            1.0 / 6.0,  // ORANGE
            2.0 / 6.0,  // YELLOW
            4.0 / 6.0,  // GREEN
            1.0,        // CYAN
            -4.0 / 6.0, // BLUE
            -2.0 / 6.0, // VIOLET
    };

    private static final Image BRICK_SPRITE = new Image("brick.png");
    private static final Image[] RAINBOW_BRICK_SPRITES = new Image[RAINBOW_HUES.length];

    private static Image hueShiftBrickSprite(double hue) {
        final ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(hue);

        final ImageView imageView = new ImageView(BRICK_SPRITE);
        imageView.setEffect(colorAdjust);

        final SnapshotParameters snapshotParameters = new SnapshotParameters();
        snapshotParameters.setFill(Color.TRANSPARENT);
        return imageView.snapshot(snapshotParameters, null);
    }

    static {
        for (int i = 0; i < RAINBOW_HUES.length; i += 1) {
            RAINBOW_BRICK_SPRITES[i] = hueShiftBrickSprite(RAINBOW_HUES[i]);
        }
    }

    private final ArrayList<GameObj> bricks;

    /**
     * Create a new 10x7 grid of rainbow-colored bricks at the top of the screen.
     */
    Level() {
        bricks = new ArrayList<>(COLUMNS * ROWS);
        int y = FIRST_ROW_Y;
        for (int rowIndex = 0; rowIndex < ROWS; rowIndex += 1) {
            // Use rowIndex modulo 7, so it won't crash if we add more than 7 rows.
            bricks.addAll(createRow(y, RAINBOW_BRICK_SPRITES[rowIndex % 7]));
            y += (BRICK_SIZE.getY()); // Add brick height.
        }
    }

    /**
     * {@return the current level's list of brick objects (including invisible ones)}
     */
    ArrayList<GameObj> getBricks() {
        return bricks;
    }

    /**
     * Create a row of bricks.
     * @param y The Y coordinate to draw this row at.
     * @param sprite Image used to represent all the bricks in this row.
     * @return List containing each brick in the row.
     */
    private static ArrayList<GameObj> createRow(int y, Image sprite) {
        ArrayList<GameObj> row = new ArrayList<>(COLUMNS);
        for (int brickIndex = 0; brickIndex < COLUMNS; brickIndex += 1) {
            final Vector2 position = new Vector2(BRICK_SIZE.getX() * brickIndex, y);
            GameObj brick = new GameObj(position, BRICK_SIZE, sprite);
            row.add(brick);
        }
        return row;
    }
}
