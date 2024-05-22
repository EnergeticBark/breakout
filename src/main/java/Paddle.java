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

    /** Move the paddle one step.
     * @param direction -1 is left, +1 is right.
     */
    public void movePaddle(int direction) {
        final Vector2 velocity = new Vector2(PADDLE_SPEED * direction, 0);
        setVelocity(velocity);
        Debug.trace( "Paddle::move: Move paddle = " + velocity.getX());
        move();
    }

    /** Keep the paddle within the confines of the screen.
     * @param screenWidth Furthest to the right we'll allow the paddle to go.
     */
    public void clampOnScreen(int screenWidth) {
        final int clampedX = Math.clamp(left(), 0, screenWidth - WIDTH);
        final int difference = clampedX - left();
        translateX(difference);
    }
}
