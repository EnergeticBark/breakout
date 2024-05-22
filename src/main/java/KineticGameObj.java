import javafx.scene.image.Image;

/**
 * An extension of the {@link GameObj} class with velocity.
 * @author Seth Humphries
 * @version 1.0
 */
class KineticGameObj extends GameObj {
    private Vector2 velocity;

    /**
     * Create a new KineticGameObj at the specified position with the specified size and sprite. By default, it's
     * velocity will be zero.
     * @param position The X and Y coordinates of the top-left corner.
     * @param size The width and height of the object. Ideally equal to the width and height of the image in sprite.
     * @param sprite Image that will be drawn to represent the object.
     */
    KineticGameObj(Vector2 position, Vector2 size, Image sprite) {
        super(position, size, sprite);
    }

    /**
     * {@return this object's current velocity.
     */
    Vector2 getVelocity() {
        return velocity;
    }
    /**
     * Set this object's velocity.
     * @param velocity How far this object will move each time {@link #move()} is called.
     */
    void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    /**
     * {@return whether this object is currently moving right}
     */
    boolean movingRight() {
        return velocity.getX() > 0;
    }
    /**
     * {@return whether this object is currently moving left}
     */
    boolean movingLeft() {
        return velocity.getX() < 0;
    }
    /**
     * {@return whether this object is currently moving up}
     */
    boolean movingUp() {
        return velocity.getY() < 0;
    }
    /**
     * {@return whether this object is currently moving down}
     */
    boolean movingDown() {
        return velocity.getY() > 0;
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

    /**
     * Resolve a collision by pushing this object away from the object it collided with.
     * @param collision Information about the collision so we know which way to bounce.
     */
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
