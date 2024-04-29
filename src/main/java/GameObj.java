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
    public int dirX = 1;                // Direction X (1, 0 or -1)
    public int dirY = 1;                // Direction Y (1, 0 or -1)

    public GameObj(int x, int y, int w, int h, Color c) {
        topX = x;
        topY = y;
        width = w;
        height = h; 
        colour = c;
    }

    // move in x-axis
    public void moveX(int units) {
        topX += units * dirX;
    }

    // move in y axis
    public void moveY(int units) {
        topY += units * dirY;
    }

    // change direction of movement in x-axis (-1, 0 or +1)
    public void changeDirectionX() {
        dirX = -dirX;
    }

    // change direction of movement in y-axis (-1, 0 or +1)
    public void changeDirectionY() {
        dirY = -dirY;
    }

    // Detect collision between this object and the argument object
    // It's easiest to work out if they do NOT overlap, and then
    // return the opposite
    public boolean hitBy(GameObj obj) {
        final boolean toTheRight = topX >= obj.topX + obj.width; // To the right of obj
        final boolean toTheLeft = topX + width <= obj.topX;      // To the left of obj
        final boolean above = topY >= obj.topY + obj.height;     // Above obj
        final boolean below = topY + height <= obj.topY;         // Below obj

        final boolean separate = toTheRight || toTheLeft || above || below;
        
        // use ! to return the opposite result - hitBy is 'not separate'
        return(!separate);
    }

    public CollisionAxes hitWhichSide(GameObj obj) {
        boolean movingRight = dirX > 0;
        int velocityX = 3 * dirX;
        int xSide;
        int objXSide;
        if (movingRight) {
            // Check our previous right side against obj's left side.
            xSide = topX + width - velocityX;
            objXSide = obj.topX;
        } else {
            // Check our previous left side against obj's right side.
            xSide = topX - velocityX;
            objXSide = obj.topX + obj.width;
        }

        boolean movingDown = dirY > 0;
        int velocityY = 3 * dirY;
        int ySide;
        int objYSide;
        if (movingDown) {
            // Check our previous bottom side against obj's top side.
            ySide = topY + height - velocityY;
            objYSide = obj.topY;
        } else {
            // Check our previous top side against obj's bottom side.
            ySide = topY - velocityY;
            objYSide = obj.topY + obj.height;
        }

        // Time the sides collide
        // Solve for t:
        // sideX + velocityX*t = objSideX
        float xSideT = (float) (objXSide - xSide) / velocityX;
        float ySideT = (float) (objYSide - ySide) / velocityY;

        return new CollisionAxes(xSideT > ySideT, xSideT < ySideT);
    }
}
