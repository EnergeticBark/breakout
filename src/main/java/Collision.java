public class Collision {
    boolean hitX;
    boolean hitY;
    int xPenetration;
    int yPenetration;

    // Figure out which side(s) a collision occurred on.
    Collision(KineticGameObj moving, GameObj stationary) {
        int movingXSide;
        int stationaryXSide;
        if (moving.velocityX > 0) { // Moving right
            // Check moving's previous right side against stationary's left side.
            movingXSide = moving.topX + moving.width - moving.velocityX;
            stationaryXSide = stationary.topX;
        } else {
            // Check moving's previous left side against stationary's right side.
            movingXSide = moving.topX - moving.velocityX;
            stationaryXSide = stationary.topX + stationary.width;
        }

        int movingYSide;
        int stationaryYSide;
        if (moving.velocityY > 0) { // Moving down
            // Check moving's previous bottom side against stationary's top side.
            movingYSide = moving.topY + moving.height - moving.velocityY;
            stationaryYSide = stationary.topY;
        } else {
            // Check moving's previous top side against stationary's bottom side.
            movingYSide = moving.topY - moving.velocityY;
            stationaryYSide = stationary.topY + stationary.height;
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

