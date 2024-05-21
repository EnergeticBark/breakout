import javafx.scene.image.Image;

public class Paddle extends KineticGameObj {
    // Set the default position, width and height of the paddle.
    private static final Vector2 START_POSITION = new Vector2(150, 355);
    private static final int WIDTH = 50;
    private static final int HEIGHT = 8;

    private static final Image PADDLE_SPRITE = new Image("paddle.png");

    private static final int PADDLE_SPEED = 5; // Distance to move paddle on each step

    public Paddle() {
        super(START_POSITION, WIDTH, HEIGHT, PADDLE_SPRITE);
    }

    // Move the paddle one step, -1 is left, +1 is right.
    public void movePaddle(int direction) {
        velocityX = PADDLE_SPEED * direction;
        Debug.trace( "Paddle::move: Move paddle = " + velocityX);
        moveX();
    }

    // Move the paddle to within the confines of the screen.
    public void clampOnScreen(int screenWidth) {
        setTopX(Math.clamp(left(), 0, screenWidth - WIDTH));
    }
}
