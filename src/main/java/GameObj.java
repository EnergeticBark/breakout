// An object in the game, represented as a rectangle, with a position,
// a size, a colour and a direction of movement.

// Watch out for the different spellings of Color/colour - the class uses American
// spelling, but we have chosen to use British spelling for the instance variable!

// import the JavaFX Color class
import javafx.scene.image.Image;

public class GameObj {
    // state variables for a game object
    private boolean visible = true; // Can be seen on the screen (change to false when the brick gets hit)
    private final Vector2 position; // Position - top left corner's X and Y coordinates.
    private final int width;        // Width of object
    private final int height;       // Height of object
    public Image sprite;            // Image used to represent the object

    public GameObj(Vector2 position, int w, int h, Image s) {
        this.position = position;
        width = w;
        height = h; 
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

    // Get the coordinates of each side.
    public int left() { return position.getX(); }
    public int top() { return position.getY(); }
    public int right() { return position.getX() + width; }
    public int bottom() { return position.getY() + height; }

    void setTopX(int value) {
        position.setX(value);
    }

    void translateX(int value) {
        final int newX = position.getX() + value;
        position.setX(newX);
    }

    void translateY(int value) {
        final int newY = position.getY() + value;
        position.setY(newY);
    }

    // Return whether the object is visible or not
    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean value) {
        visible = value;
    }
}
