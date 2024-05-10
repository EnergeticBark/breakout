import javafx.scene.image.Image;

public class KineticGameObj extends GameObj {
    public int velocityX = 1;                // Direction X (1, 0 or -1)
    public int velocityY = 1;                // Direction Y (1, 0 or -1)

    KineticGameObj(int x, int y, int w, int h, Image s) {
        super(x, y, w, h, s);
    }

    // move in x-axis
    public void moveX() {
        topX += velocityX;
    }

    // move in y axis
    public void moveY() {
        topY += velocityY;
    }

    // change direction of movement in x-axis (-1, 0 or +1)
    public void changeDirectionX() {
        velocityX = -velocityX;
    }

    // change direction of movement in y-axis (-1, 0 or +1)
    public void changeDirectionY() {
        velocityY = -velocityY;
    }

    public void bounce(Collision collision) {
        if (collision.hitX) {
            changeDirectionX();
            // Push the object in the direction it would've moved if it hadn't gone inside the other.
            topX -= collision.xPenetration * 2;
        }
        if (collision.hitY) {
            changeDirectionY();
            // Push the object in the direction it would've moved if it hadn't gone inside the other.
            topY -= collision.yPenetration * 2;
        }
    }
}