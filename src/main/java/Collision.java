/**
 * Figure out which side(s) a collision occurred and how far the moving object passed through the stationary object.
 * @version 1.0
 */
public class Collision {
    boolean hitX;
    boolean hitY;
    int xPenetration;
    int yPenetration;

    /**
     * @param moving The object which is moving.
     * @param stationary The object which is stationary and gets hit.
     */
    Collision(KineticGameObj moving, GameObj stationary) {
        float xSideT = Float.NEGATIVE_INFINITY;
        float ySideT = Float.NEGATIVE_INFINITY;

        if (moving.movingRight()) {
            // Check moving's previous right side against stationary's left side.
            final int previousRight = moving.right() - moving.getVelocity().getX();
            xSideT = timeOfAxisCollision(stationary.left(), previousRight, moving.getVelocity().getX());
            xPenetration = moving.right() - stationary.left() - 1;
        } else if (moving.movingLeft()) {
            // Check moving's previous left side against stationary's right side.
            final int previousLeft = moving.left() - moving.getVelocity().getX();
            xSideT = timeOfAxisCollision(stationary.right(), previousLeft, moving.getVelocity().getX());
            xPenetration = moving.left() - stationary.right() + 1;
        }

        if (moving.movingDown()) {
            // Check moving's previous bottom side against stationary's top side.
            final int previousBottom = moving.bottom() - moving.getVelocity().getY();
            ySideT = timeOfAxisCollision(stationary.top(), previousBottom, moving.getVelocity().getY());
            yPenetration = moving.bottom() - stationary.top() - 1;
        } else if (moving.movingUp()) {
            // Check moving's previous top side against stationary's bottom side.
            final int previousTop = moving.top() - moving.getVelocity().getY();
            ySideT = timeOfAxisCollision(stationary.bottom(), previousTop, moving.getVelocity().getY());
            yPenetration = moving.top() - stationary.bottom() + 1;
        }

        /* The collision happened on whichever axis was intersected last, or both if they happened at the same time
        (a corner was hit). If velocity on an axis is 0, time of collision on that axis will be -infinity. */
        hitX = xSideT >= ySideT;
        hitY = xSideT <= ySideT;
    }

    /** Find the time of collision on one axis.
     * Solve for t:
     * movingSide + velocity*t = stationarySide
     * @param stationarySide The non-moving side's position.
     * @param movingSide The moving side's position pre-collision.
     * @param velocity How fast the moving side is moving. Positive or negative.
     * @return Time the collision occurs.
     */
    private static float timeOfAxisCollision(int stationarySide, int movingSide, int velocity) {
        return (float) (stationarySide - movingSide) / velocity;
    }
}

