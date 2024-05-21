import javafx.scene.SnapshotParameters;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Level {
    private static final int FIRST_ROW_Y = 40; // Leave 40px of vertical padding for the score counter.

    private static final int BRICKS_PER_ROW = 10;
    private static final int ROWS = 7;

    private static final int BRICK_WIDTH = 30; // Brick size
    private static final int BRICK_HEIGHT = 10;

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

    private static final Image brickSprite = new Image("brick.png");
    private static final Image[] rainbowBrickSprites = new Image[RAINBOW_HUES.length];

    public static Image hueShiftSprite(Image sprite, double hue) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(hue);

        ImageView imageView = new ImageView(sprite);
        imageView.setEffect(colorAdjust);

        SnapshotParameters snapshotParameters = new SnapshotParameters();
        snapshotParameters.setFill(Color.TRANSPARENT);
        return imageView.snapshot(snapshotParameters, null);
    }

    static {
        for (int i = 0; i < RAINBOW_HUES.length; i += 1) {
            rainbowBrickSprites[i] = hueShiftSprite(brickSprite, RAINBOW_HUES[i]);
        }
    }

    /* TODO:
        Might make sense to build the level by positioning each brick automatically and then only specifying the color and whether to place it from top left to bottom right.
        Bricks should be laid out on a fixed grid, that's for sure. Granular control over the pixels gets tedious.
     */
    // Code to make the bricks array
    public static ArrayList<GameObj> initializeLevel() {
        ArrayList<GameObj> bricks = new ArrayList<>(BRICKS_PER_ROW * ROWS);
        int y = FIRST_ROW_Y;
        for (int rowIndex = 0; rowIndex < ROWS; rowIndex += 1) {
            bricks.addAll(createRow(y, rainbowBrickSprites[rowIndex % 7]));
            y += (BRICK_HEIGHT);
        }
        return bricks;
    }

    public static ArrayList<GameObj> createRow(int y, Image i) {
        ArrayList<GameObj> row = new ArrayList<>(BRICKS_PER_ROW);
        for (int brickIndex = 0; brickIndex < BRICKS_PER_ROW; brickIndex += 1) {
            final Vector2 position = new Vector2(BRICK_WIDTH * brickIndex, y);
            GameObj brick = new GameObj(position, BRICK_WIDTH, BRICK_HEIGHT, i);
            row.add(brick);
        }
        return row;
    }
}
