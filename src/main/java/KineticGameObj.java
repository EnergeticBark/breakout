import javafx.scene.paint.Color;

public class KineticGameObj extends GameObj {
    public int directionX = 1;                // Direction X (1, 0 or -1)
    public int directionY = 1;                // Direction Y (1, 0 or -1)

    KineticGameObj(int x, int y, int w, int h, Color c) {
        super(x, y, w, h, c);
    }

    // move in x-axis
    public void moveX(int units) {
        topX += units * directionX;
    }

    // move in y axis
    public void moveY(int units) {
        topY += units * directionY;
    }

    // change direction of movement in x-axis (-1, 0 or +1)
    public void changeDirectionX() {
        directionX = -directionX;
    }

    // change direction of movement in y-axis (-1, 0 or +1)
    public void changeDirectionY() {
        directionY = -directionY;
    }

    // Figure out which axes a collision had occurred on. The invoking object is moving and the parameter "obj" is
    // stationary.
    public CollisionAxes hitAxisOf(GameObj obj) {
        final int velocityX = 3 * directionX;
        int xSide;
        int objXSide;
        if (directionX > 0) { // Moving right
            // Check our previous right side against obj's left side.
            xSide = topX + width - velocityX;
            objXSide = obj.topX;
        } else {
            // Check our previous left side against obj's right side.
            xSide = topX - velocityX;
            objXSide = obj.topX + obj.width;
        }

        final int velocityY = 3 * directionY;
        int ySide;
        int objYSide;
        if (directionY > 0) { // Moving down
            // Check our previous bottom side against obj's top side.
            ySide = topY + height - velocityY;
            objYSide = obj.topY;
        } else {
            // Check our previous top side against obj's bottom side.
            ySide = topY - velocityY;
            objYSide = obj.topY + obj.height;
        }

        // Find the time of intersection on each axis.
        // Solve for t:
        // side + velocity*t = objSide
        final float xSideT = (float) (objXSide - xSide) / velocityX;
        final float ySideT = (float) (objYSide - ySide) / velocityY;

        // The collision happened on whichever axis was intersected last.
        return new CollisionAxes(xSideT >= ySideT, xSideT <= ySideT);
    }
}