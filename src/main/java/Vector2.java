/**
 * 2-dimensional vector with integer X and Y components.
 * Used to represent positions and directions throughout the rest of the game.
 * @version 1.0
 */
public class Vector2 {
    private int x;
    private int y;

    Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}


