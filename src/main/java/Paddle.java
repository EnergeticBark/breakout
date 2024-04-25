import javafx.scene.paint.Color;

public class Paddle extends GameObj {
    // Set the default position, width and height of the paddle.
    private static final int START_X = 150;
    private static final int START_Y = 355;
    private static final int WIDTH = 75;
    private static final int HEIGHT = 8;

    private static final int PADDLE_MOVE = 5; // Distance to move paddle on each step

    public Paddle() {
        super(START_X, START_Y, WIDTH, HEIGHT, Color.GRAY);
    }

    // Move the paddle one step, -1 is left, +1 is right.
    public void movePaddle(int direction) {
        int dist = direction * PADDLE_MOVE;    // Actual distance to move
        Debug.trace( "Paddle::move: Move paddle = " + dist);
        moveX(dist);
    }

    // Move the paddle to within the confines of the screen.
    public void clampOnScreen(int screenWidth) {
        topX = Math.clamp(
                topX,
                0,
                screenWidth - WIDTH
                );
    }
}
