import javafx.scene.image.Image;

/**
 * An object in the game, represented as a rectangle, with a position, size, and sprite.
 * @author Seth Humphries
 * @version 1.0
 */
class GameObj {
    private boolean visible = true; // Can be seen on the screen (changed to false when the brick gets hit)
    private final Vector2 position; // Position - top-left corner's X and Y coordinates.
    private final Vector2 size;     // Size of the object - width and height.
    private final Image sprite;     // Image used to represent the object

    /**
     * Create a new stationary GameObj at the specified position with the specified size and sprite.
     * @param position The X and Y coordinates of the top-left corner.
     * @param size The width and height of the object. Ideally equal to the width and height of the image in sprite.
     * @param sprite Image that will be drawn to represent the object.
     */
     GameObj(Vector2 position, Vector2 size, Image sprite) {
        this.position = position;
        this.size = size;
        this.sprite = sprite;
    }

    /**
     * Detect collision between this object and the argument object.
     * @param obj The object we're checking to see if we hit.
     * @return Whether we hit the object.
     */
    boolean hit(GameObj obj) {
        // It's easiest to work out if they do NOT overlap, and then return the opposite.
        final boolean toTheRight = left() >= obj.right(); // To the right of obj.
        final boolean toTheLeft = right() <= obj.left();  // To the left of obj.
        final boolean below = top() >= obj.bottom();      // Below obj.
        final boolean above = bottom() <= obj.top();      // Above obj.

        final boolean separate = toTheRight || toTheLeft || below || above;
        
        // use ! to return the opposite result - hitBy is 'not separate'
        return !separate;
    }

    /**
     * {@return width of this object in pixels}
     */
    int width() {
        return size.getX();
    }
    /**
     * {@return height of this object in pixels}
     */
    int height() {
        return size.getY();
    }

    /**
     * {@return the X coordinate of this object's left side}
     */
    int left() {
        return position.getX();
    }
    /**
     * {@return the X coordinate of this object's right side}
     */
    int right() {
        return position.getX() + width();
    }
    /**
     * {@return the Y coordinate of this object's top side}
     */
    int top() {
        return position.getY();
    }
    /**
     * {@return the Y coordinate of this object's bottom side}
     */
    int bottom() {
        return position.getY() + height();
    }

    /**
     * Add to this object's position on the X-axis.
     * @param value Number of pixels to move right (or left if negative).
     */
    void translateX(int value) {
        final int newX = position.getX() + value;
        position.setX(newX);
    }

    /**
     * Add to this object's position on the Y-axis.
     * @param value Number of pixels to move down (or up if negative).
     */
    void translateY(int value) {
        final int newY = position.getY() + value;
        position.setY(newY);
    }

    /**
     * {@return the image used to represent this object}
     */
    Image getSprite() {
        return sprite;
    }

    /**
     * {@return whether this object is visible and should be drawn}
     */
    boolean getVisible() {
        return visible;
    }

    /**
     * Set this object's visibility. Bricks are made invisible after breaking.
     * @param value Whether this object should be made visible or invisible.
     */
    void setVisible(boolean value) {
        visible = value;
    }
}
