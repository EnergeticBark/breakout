import javafx.scene.image.Image;

/**
 * An object in the game, represented as a rectangle, with a position, size, and sprite.
 * @author Seth Humphries
 * @version 1.0
 */
public class GameObj {
    // state variables for a game object
    private boolean visible = true; // Can be seen on the screen (change to false when the brick gets hit)
    private final Vector2 position; // Position - top left corner's X and Y coordinates.
    private final Vector2 size;     // Size of the object
    private final Image sprite;           // Image used to represent the object

    public GameObj(Vector2 position, Vector2 size, Image s) {
        this.position = position;
        this.size = size;
        sprite = s;
    }

    // Detect collision between this object and the argument object
    // It's easiest to work out if they do NOT overlap, and then
    // return the opposite
    public boolean hit(GameObj obj) {
        final boolean toTheRight = left() >= obj.right(); // To the right of obj
        final boolean toTheLeft = right() <= obj.left();  // To the left of obj
        final boolean below = top() >= obj.bottom();      // Below obj
        final boolean above = bottom() <= obj.top();      // Above obj

        final boolean separate = toTheRight || toTheLeft || below || above;
        
        // use ! to return the opposite result - hitBy is 'not separate'
        return !separate;
    }

    public int width() {
        return size.getX();
    }
    public int height() {
        return size.getY();
    }

    // Get the coordinates of each side.
    public int left() { return position.getX(); }
    public int top() { return position.getY(); }
    public int right() { return position.getX() + width(); }
    public int bottom() { return position.getY() + height(); }

    void translateX(int value) {
        final int newX = position.getX() + value;
        position.setX(newX);
    }

    void translateY(int value) {
        final int newY = position.getY() + value;
        position.setY(newY);
    }

    public Image getSprite() {
        return sprite;
    }

    // Return whether the object is visible or not
    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean value) {
        visible = value;
    }
}
