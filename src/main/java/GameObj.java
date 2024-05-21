// An object in the game, represented as a rectangle, with a position,
// a size, a colour and a direction of movement.

// Watch out for the different spellings of Color/colour - the class uses American
// spelling, but we have chosen to use British spelling for the instance variable!

// import the JavaFX Color class
import javafx.scene.image.Image;

public class GameObj {
    // state variables for a game object
    private boolean visible = true; // Can be seen on the screen (change to false when the brick gets hit)
    private int topX;               // Position - top left corner X
    private int topY;               // position - top left corner Y
    private final int width;        // Width of object
    private final int height;       // Height of object
    public Image sprite;            // Image used to represent the object

    public GameObj(int x, int y, int w, int h, Image s) {
        topX = x;
        topY = y;
        width = w;
        height = h; 
        sprite = s;
    }

    // Detect collision between this object and the argument object
    // It's easiest to work out if they do NOT overlap, and then
    // return the opposite
    public boolean hit(GameObj obj) {
        final boolean toTheRight = topX >= obj.topX + obj.width; // To the right of obj
        final boolean toTheLeft = topX + width <= obj.topX;      // To the left of obj
        final boolean above = topY >= obj.topY + obj.height;     // Above obj
        final boolean below = topY + height <= obj.topY;         // Below obj

        final boolean separate = toTheRight || toTheLeft || above || below;
        
        // use ! to return the opposite result - hitBy is 'not separate'
        return(!separate);
    }

    // Get the coordinates of each side.
    public int left() { return topX; }
    public int top() { return topY; }
    public int right() { return topX + width; }
    public int bottom() { return topY + height; }

    void setTopX(int value) {
        topX = value;
    }

    void translateX(int value) {
        topX += value;
    }

    void translateY(int value) {
        topY += value;
    }

    // Return whether the object is visible or not
    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean value) {
        visible = value;
    }
}
