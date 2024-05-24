import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollisionTest {
    @Test
    @DisplayName("KineticGameObj moving left and hitting right side of GameObj")
    void movingLeftHit() {
        final Vector2 size10Square = new Vector2(10, 10);

        final Vector2 stationaryPosition = new Vector2(0, 0);
        GameObj stationary = new GameObj(stationaryPosition, size10Square, null, null);

        final Vector2 movingPosition = new Vector2(5, 0);
        KineticGameObj moving = new KineticGameObj(movingPosition, size10Square, null, null);
        moving.setVelocity(new Vector2(-5, 0));

        Collision collision = new Collision(moving, stationary);
        assertAll(
                () -> assertTrue(collision.getHitX()),
                () -> assertFalse(collision.getHitY())
        );
    }

    @Test
    @DisplayName("KineticGameObj moving up and hitting bottom side of GameObj")
    void movingUpHit() {
        final Vector2 size10Square = new Vector2(10, 10);

        final Vector2 stationaryPosition = new Vector2(0, 0);
        GameObj stationary = new GameObj(stationaryPosition, size10Square, null, null);

        final Vector2 movingPosition = new Vector2(0, 5);
        KineticGameObj moving = new KineticGameObj(movingPosition, size10Square, null, null);
        moving.setVelocity(new Vector2(0, -5));

        Collision collision = new Collision(moving, stationary);
        assertAll(
                () -> assertFalse(collision.getHitX()),
                () -> assertTrue(collision.getHitY())
        );
    }
}