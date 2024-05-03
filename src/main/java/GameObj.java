// An object in the game, represented as a rectangle, with a position,
// a size, a colour and a direction of movement.

// Watch out for the different spellings of Color/colour - the class uses American
// spelling, but we have chosen to use British spelling for the instance variable!

// import the JavaFX Color class
import javafx.scene.paint.Color;

public class GameObj {
    // state variables for a game object
    public boolean visible = true;      // Can be seen on the screen (change to false when the brick gets hit)
    public int topX = 0;                // Position - top left corner X
    public int topY = 0;                // position - top left corner Y
    public int width = 0;               // Width of object
    public int height = 0;              // Height of object
    public Color colour;                // Colour of object

    public GameObj(int x, int y, int w, int h, Color c) {
        topX = x;
        topY = y;
        width = w;
        height = h; 
        colour = c;
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
}
