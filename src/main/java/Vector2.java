/**
 * 2-dimensional vector with integer X and Y components.
 * Used to represent positions and directions throughout the rest of the game.
 * @author Seth Humphries
 * @version 1.0
 */
class Vector2 {
    private int x;
    private int y;

    Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }
}
