import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollisionTest {
    @Test
    @DisplayName("KineticGameObj moving left and hitting right side of GameObj")
    void movingLeftHit() {
        final Vector2 stationaryPosition = new Vector2(0, 0);
        GameObj stationary = new GameObj(stationaryPosition, 10, 10, null);

        final Vector2 movingPosition = new Vector2(5, 0);
        KineticGameObj moving = new KineticGameObj(movingPosition, 10, 10, null);
        moving.velocityX = -5;
        moving.velocityY = 0;

        Collision collision = new Collision(moving, stationary);
        assertAll(
                () -> assertTrue(collision.hitX),
                () -> assertFalse(collision.hitY)
        );
    }

    @Test
    @DisplayName("KineticGameObj moving up and hitting bottom side of GameObj")
    void movingUpHit() {
        final Vector2 stationaryPosition = new Vector2(0, 0);
        GameObj stationary = new GameObj(stationaryPosition, 10, 10, null);

        final Vector2 movingPosition = new Vector2(0, 5);
        KineticGameObj moving = new KineticGameObj(movingPosition, 10, 10, null);
        moving.velocityX = 0;
        moving.velocityY = -5;

        Collision collision = new Collision(moving, stationary);
        assertAll(
                () -> assertFalse(collision.hitX),
                () -> assertTrue(collision.hitY)
        );
    }
}