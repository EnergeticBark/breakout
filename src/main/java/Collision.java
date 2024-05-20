public class Collision {
    boolean hitX;
    boolean hitY;
    int xPenetration;
    int yPenetration;

    // Figure out which side(s) the collision occurred and how far the moving object passed through the other.
    Collision(KineticGameObj moving, GameObj stationary) {
        float xSideT = Float.NEGATIVE_INFINITY;
        float ySideT = Float.NEGATIVE_INFINITY;

        if (moving.velocityX > 0) { // Moving right
            // Check moving's previous right side against stationary's left side.
            final int previousRight = moving.right() - moving.velocityX;
            xSideT = timeOfAxisCollision(stationary.left(), previousRight, moving.velocityX);
            xPenetration = moving.right() - stationary.left() - 1;
        } else if (moving.velocityX < 0) { // Moving left
            // Check moving's previous left side against stationary's right side.
            final int previousLeft = moving.left() - moving.velocityX;
            xSideT = timeOfAxisCollision(stationary.right(), previousLeft, moving.velocityX);
            xPenetration = moving.left() - stationary.right() + 1;
        }

        if (moving.velocityY > 0) { // Moving down
            // Check moving's previous bottom side against stationary's top side.
            final int previousBottom = moving.bottom() - moving.velocityY;
            ySideT = timeOfAxisCollision(stationary.top(), previousBottom, moving.velocityY);
            yPenetration = moving.bottom() - stationary.top() - 1;
        } else if (moving.velocityY < 0) { // Moving up
            // Check moving's previous top side against stationary's bottom side.
            final int previousTop = moving.top() - moving.velocityY;
            ySideT = timeOfAxisCollision(stationary.bottom(), previousTop, moving.velocityY);
            yPenetration = moving.top() - stationary.bottom() + 1;
        }

        /* The collision happened on whichever axis was intersected last, or both if they happened at the same time
        (a corner was hit). If velocity on an axis is 0, time of collision on that axis will be -infinity. */
        hitX = xSideT >= ySideT;
        hitY = xSideT <= ySideT;
    }

    // Find the time of collision on one axis.
    // Solve for t:
    // movingSide + velocity*t = stationarySide
    private static float timeOfAxisCollision(int stationarySide, int movingSide, int velocity) {
        return (float) (stationarySide - movingSide) / velocity;
    }
}

