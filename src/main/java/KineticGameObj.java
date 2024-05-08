import javafx.scene.image.Image;

public class KineticGameObj extends GameObj {
    public int velocityX = 1;                // Direction X (1, 0 or -1)
    public int velocityY = 1;                // Direction Y (1, 0 or -1)

    KineticGameObj(int x, int y, int w, int h, Image s) {
        super(x, y, w, h, s);
    }



    // move in x-axis
    public void moveX() {
        topX += velocityX;
    }

    // move in y axis
    public void moveY() {
        topY += velocityY;
    }

    // change direction of movement in x-axis (-1, 0 or +1)
    public void changeDirectionX() {
        velocityX = -velocityX;
    }

    // change direction of movement in y-axis (-1, 0 or +1)
    public void changeDirectionY() {
        velocityY = -velocityY;
    }

    // Figure out which axes a collision had occurred on. The invoking object is moving and the parameter "obj" is
    // stationary.
    public CollisionAxes hitAxisOf(GameObj obj) {
        int xSide;
        int objXSide;
        if (velocityX > 0) { // Moving right
            // Check our previous right side against obj's left side.
            xSide = topX + width - velocityX;
            objXSide = obj.topX;
        } else {
            // Check our previous left side against obj's right side.
            xSide = topX - velocityX;
            objXSide = obj.topX + obj.width;
        }

        int ySide;
        int objYSide;
        if (velocityY > 0) { // Moving down
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