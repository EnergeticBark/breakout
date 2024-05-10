public class Collision {
    boolean hitX;
    boolean hitY;
    int xPenetration;
    int yPenetration;

    // Figure out which side(s) the collision occurred and how far the moving object passed through the other.
    Collision(KineticGameObj moving, GameObj stationary) {
        int movingXSide;
        int stationaryXSide;
        if (moving.velocityX > 0) { // Moving right
            // Check moving's previous right side against stationary's left side.
            movingXSide = moving.right() - moving.velocityX;
            stationaryXSide = stationary.left();
            xPenetration = moving.right() - stationary.left() - 1;
        } else {
            // Check moving's previous left side against stationary's right side.
            movingXSide = moving.left() - moving.velocityX;
            stationaryXSide = stationary.right();
            xPenetration = moving.left() - stationary.right() + 1;
        }

        int movingYSide;
        int stationaryYSide;
        if (moving.velocityY > 0) { // Moving down
            // Check moving's previous bottom side against stationary's top side.
            movingYSide = moving.bottom() - moving.velocityY;
            stationaryYSide = stationary.top();
            yPenetration = moving.bottom() - stationary.top() - 1;
        } else {
            // Check moving's previous top side against stationary's bottom side.
            movingYSide = moving.top() - moving.velocityY;
            stationaryYSide = stationary.bottom();
            yPenetration = moving.top() - stationary.bottom() + 1;
        }

        // Find the time of intersection on each axis.
        // Solve for t:
        // movingSide + velocity*t = stationarySide
        final float xSideT = (float) (stationaryXSide - movingXSide) / moving.velocityX;
        final float ySideT = (float) (stationaryYSide - movingYSide) / moving.velocityY;

        // The collision happened on whichever axis was intersected last.
        hitX = xSideT >= ySideT;
        hitY = xSideT <= ySideT;
    }
}

