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
        moving.velocityY = 1;
        Collision collision = new Collision(moving, stationary);
        assertAll(
                () -> assertTrue(collision.hitX),
                () -> assertFalse(collision.hitY)
        );
    }
}