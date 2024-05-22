import javafx.scene.image.Image;

/**
 * An extension of the {@link GameObj} class with velocity.
 * @version 1.0
 */
public class KineticGameObj extends GameObj {
    private Vector2 velocity;

    KineticGameObj(Vector2 position, Vector2 size, Image s) {
        super(position, size, s);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public boolean movingRight() {
        return velocity.getX() > 0;
    }

    public boolean movingLeft() {
        return velocity.getX() < 0;
    }

    public boolean movingDown() {
        return velocity.getY() > 0;
    }

    public boolean movingUp() {
        return velocity.getY() < 0;
    }

    /**
     * Move this object in the direction of its current velocity.
     */
    public void move() {
        translateX(velocity.getX());
        translateY(velocity.getY());
    }

    /**
     * Flip this object's current velocity on the X-axis.
     */
    public void changeDirectionX() {
        velocity.setX(-velocity.getX());
    }

    /**
     * Flip this object's current velocity on the Y-axis.
     */
    public void changeDirectionY() {
        velocity.setY(-velocity.getY());
    }

    public void bounce(Collision collision) {
        if (collision.hitX) {
            changeDirectionX();
            // Push the object in the direction it would've moved if it hadn't gone inside the other.
            translateX(-collision.xPenetration * 2);
        }
        if (collision.hitY) {
            changeDirectionY();
            // Push the object in the direction it would've moved if it hadn't gone inside the other.
            translateY(-collision.yPenetration * 2);
        }
    }
}