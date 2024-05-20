import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollisionTest {
    @Test
    @DisplayName("KineticGameObj moving left and hitting right side of GameObj")
    void movingLeftHit() {
        GameObj stationary = new GameObj(0, 0, 10, 10, null);
        KineticGameObj moving = new KineticGameObj(5, 0, 10, 10, null);
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
        GameObj stationary = new GameObj(0, 0, 10, 10, null);
        KineticGameObj moving = new KineticGameObj(0, 5, 10, 10, null);
        moving.velocityX = 0;
        moving.velocityY = -5;
        Collision collision = new Collision(moving, stationary);
        assertAll(
                () -> assertFalse(collision.hitX),
                () -> assertTrue(collision.hitY)
        );
    }
}