import javafx.scene.image.Image;

/**
 * Controlled by the player to prevent the {@link Ball} from reaching the bottom of the screen.
 * @author Seth Humphries
 * @version 1.0
 */
class Paddle extends KineticGameObj {
    // Set the default position, width and height of the paddle.
    private static final int START_X = 150;
    private static final int START_Y = 355;
    private static final Vector2 SIZE = new Vector2(50, 8);
    private static final Image SPRITE = new Image("paddle.png");

    private static final int SPEED = 5; // Distance to move paddle on each step

    Paddle() {
        super(new Vector2(START_X, START_Y), SIZE, SPRITE);
    }

    /** Move the paddle one step in the direction held.
     * The paddle won't move if both left and right are being held down at the same time.
     * @param leftHeld Whether the left arrow is being held.
     * @param rightHeld Whether the right arrow is being held.
     * @param screenWidth Width of the screen so the paddle knows how far it's allowed to go.
     * @param ball Ball game object, so we don't move the paddle inside of it.
     */
    void movePaddle(boolean leftHeld, boolean rightHeld, int screenWidth, Ball ball) {
        if (leftHeld && !rightHeld) {
            setVelocity(new Vector2(-SPEED, 0));
            move();
            Debug.trace( "Paddle::move: Move paddle = " + getVelocity().getX());
            // Ensure the paddle doesn't move inside the ball.
            if (hit(ball)) {
                setVelocity(new Vector2(SPEED, 0));
                move();
            }
            clampOnScreen(screenWidth);
        }
        if (rightHeld && !leftHeld) {
            setVelocity(new Vector2(SPEED, 0));
            move();
            Debug.trace( "Paddle::move: Move paddle = " + getVelocity().getX());
            // Ensure the paddle doesn't move inside the ball.
            if (hit(ball)) {
                setVelocity(new Vector2(-SPEED, 0));
                move();
            }
            clampOnScreen(screenWidth);
        }
    }

    /** Keep the paddle within the confines of the screen.
     * @param screenWidth Furthest to the right we'll allow the paddle to go.
     */
    private void clampOnScreen(int screenWidth) {
        final int clampedX = Math.clamp(left(), 0, screenWidth - width());
        final int difference = clampedX - left();
        translateX(difference);
    }
}
