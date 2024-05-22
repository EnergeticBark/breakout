import javafx.scene.image.Image;

/**
 * Bounces around the screen and damages bricks.
 * @version 1.0
 */
public class Ball extends KineticGameObj {
    private static final int BALL_SIZE = 10;
    private static final int BALL_SPEED = 3; // Distance to move the ball on each step
    private static final Image BALL_SPRITE = new Image("ball.png");

    /**
     * Create a new ball at the specified position.
     * The ball's size is 10x10 pixels, and it's initial velocity is down and to the right.
     * @param position The initial position of the ball's top left corner.
     */
    Ball(Vector2 position) {
        super(position, BALL_SIZE, BALL_SIZE, BALL_SPRITE);
        setVelocity(new Vector2(BALL_SPEED, BALL_SPEED));
    }
}

