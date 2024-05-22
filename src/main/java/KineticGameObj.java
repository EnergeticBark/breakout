import javafx.scene.image.Image;

/**
 * An extension of the {@link GameObj} class with velocity.
 * @author Seth Humphries
 * @version 1.0
 */
class KineticGameObj extends GameObj {
    private Vector2 velocity;

    KineticGameObj(Vector2 position, Vector2 size, Image s) {
        super(position, size, s);
    }

    Vector2 getVelocity() {
        return velocity;
    }

    void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    boolean movingRight() {
        return velocity.getX() > 0;
    }

    boolean movingLeft() {
        return velocity.getX() < 0;
    }

    boolean movingDown() {
        return velocity.getY() > 0;
    }

    boolean movingUp() {
        return velocity.getY() < 0;
    }

    /**
     * Move this object in the direction of its current velocity.
     */
    void move() {
        translateX(velocity.getX());
        translateY(velocity.getY());
    }

    /**
     * Flip this object's current velocity on the X-axis.
     */
    void changeDirectionX() {
        velocity.setX(-velocity.getX());
    }

    /**
     * Flip this object's current velocity on the Y-axis.
     */
    void changeDirectionY() {
        velocity.setY(-velocity.getY());
    }

    void bounce(Collision collision) {
        if (collision.getHitX()) {
            changeDirectionX();
            // Push the object in the direction it would've moved if it hadn't gone inside the other.
            translateX(-collision.getXPenetration() * 2);
        }
        if (collision.getHitY()) {
            changeDirectionY();
            // Push the object in the direction it would've moved if it hadn't gone inside the other.
            translateY(-collision.getYPenetration() * 2);
        }
    }
}
