import javafx.scene.image.Image;

/**
 * Bounces across the screen and damages bricks.
 * @author Seth Humphries
 * @version 1.0
 */
class Ball extends KineticGameObj {
    private static final Vector2 SIZE = new Vector2(10, 10);
    private static final Image SPRITE = new Image("ball.png");

    private static final int SPEED = 3; // Distance to move the ball on each step

    /**
     * Create a new ball at the specified position.
     * The ball's size is 10x10 pixels, and it's initial velocity is down and to the right.
     * @param position The initial position of the ball's top left corner.
     */
    Ball(Vector2 position) {
        super(position, SIZE, SPRITE);
        setVelocity(new Vector2(SPEED, SPEED));
    }
}
