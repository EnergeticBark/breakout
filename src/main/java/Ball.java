import javafx.scene.image.Image;

public class Ball extends KineticGameObj {
    private static final int BALL_SIZE = 10;       // Ball size
    private static final int BALL_SPEED = 3;       // Distance to move the ball on each step
    private static final Image BALL_SPRITE = new Image("ball.png");

    Ball(int x, int y) {
        super(x, y, BALL_SIZE, BALL_SIZE, BALL_SPRITE);
        velocityX = BALL_SPEED;
        velocityY = BALL_SPEED;
    }
}

