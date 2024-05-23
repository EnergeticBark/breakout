import javafx.animation.AnimationTimer;
import javafx.application.Platform;

/**
 * Represents all the actual content and functionality of the game.
 * It manages all the game objects that the {@link View} needs (the {@link Paddle}, {@link Ball}, bricks, and the
 * score), provides methods to allow the {@link Controller} to move the paddle (and a couple of other functions - change
 * the speed or stop the game), and runs a background process (a 'thread') that moves the ball every 16 milliseconds and
 * checks for collisions.
 * @author Seth Humphries
 * @version 1.0
 */
class Model {
    // First, a collection of useful values for calculating sizes and layouts etc.
    private static final int MENU_HEIGHT = 40; // Height of menu bar space at the top

    private static final int HIT_BRICK = 50;   // Score for hitting a brick

    private View view; // Instance variable for the View component of MVC.

    // The game 'model' - these represent the state of the game
    // and are used by the View to display it
    private Ball ball; // The ball
    private Level level;         // The level, which contains the list of bricks.
    private Paddle paddle;       // The paddle
    private int score = 0;       // The score
    private int lives = 3;       // Number of lives

    // variables that control the game 
    private boolean gameFinished = false; // Set to true to end the game.
    private boolean fast = false;         // Set true to make the ball go faster.

    // Variables that keep track of which keys are held.
    private boolean leftHeld = false;
    private boolean rightHeld = false;

    // initialisation parameters for the model
    public int width;  // Width of game
    public int height; // Height of game

    // CONSTRUCTOR - needs to know how big the window will be
    Model(int w, int h) {
        Debug.trace("Model::<constructor>");  
        width = w; 
        height = h;
    }

    
    // Animating the game.
    // The game is animated by using a 'thread'. Threads allow the program to do 
    // two (or more) things at the same time. In this case the main program is
    // doing the usual thing (View waits for input, sends it to Controller,
    // Controller sends to Model, Model updates), but a second thread runs in 
    // a loop, updating the position of the ball, checking if it hits anything
    // (and changing direction if it does) and then telling the View the Model 
    // changed.
    
    // When we use more than one thread, we have to take care that they don't
    // interfere with each other (for example, one thread changing the value of 
    // a variable at the same time the other is reading it). We do this by 
    // SYNCHRONIZING methods. For any object, only one synchronized method can
    // be running at a time - if another thread tries to run the same or another
    // synchronized method on the same object, it will stop and wait for the
    // first one to finish.

    /**
     * Start the animation thread.
     */
    void startGame() {
        initialiseGame();                       // set the initial game state
        Thread t = new Thread( this::runGame ); // create a thread running the runGame method
        t.setDaemon(true);                      // Tell system this thread can die when it finishes
        t.start();                              // Start the thread running
    }

    void setView(View view) {
        this.view = view;
    }

    /**
     * Initialise the game - reset the score and create the game objects.
     */
    private void initialiseGame() {
        ball = new Ball(new Vector2(30, 200));
        level = new Level();
        paddle = new Paddle();
        score = 0;
    }

    /**
     * The main game loop.
     */
    private void runGame() {
        try {
            Debug.trace("Model::runGame: Game starting");
            /* AnimationTimer calls its handle method and redraws the screen once per monitor refresh.
             * This keeps the window's framerate smooth and separate from our Model's internal update rate. */
            new AnimationTimer() {
                @Override
                public void handle(long l) {
                    updateView(); // Refresh screen
                }
            }.start();

            while (!gameFinished) {
                updateGame();                     // update the game state
                Thread.sleep(getFast() ? 8 : 16); // wait a few milliseconds
            }
            Debug.trace("Model::runGame: Game finished"); 
        } catch (Exception e) {
            Debug.error("Model::runAsSeparateThread error: " + e.getMessage());
        }
    }

    /**
     * Updates the game - this is called about 60 times per second to give the impression of movement.
     */
    private synchronized void updateGame() {
        // Move the paddle in the direction being held.
        paddle.movePaddle(getLeftHeld(), getRightHeld(), width, ball);

        // Move the ball one step (the ball knows which direction it's moving in).
        ball.move();
        // get the current ball position (top left corner)
        // Deal with possible edge of board hit
        if (ball.right() >= width) ball.changeDirectionX();
        if (ball.left() <= 0) ball.changeDirectionX();
        // Bottom
        if (ball.bottom() >= height) {
            lives -= 1;      // Remove a life from the counter.
            if (lives > 0) { // Spawn another ball if the player hasn't run out of lives.
                ball = new Ball(new Vector2(30, 200));
            } else { // Otherwise end the game.
                setGameFinished(true);
            }
        }
        if (ball.top() <= MENU_HEIGHT) ball.changeDirectionY();

        // *[3]******************************************************[3]*
        // * Code to check if a visible brick has been hit              *
        // * The ball has no effect on an invisible brick               *
        // * If a brick has been hit, change its 'visible' setting to   *
        // * false so that it will 'disappear'                          *
        // **************************************************************
        for (GameObj brick: level.getBricks()) {
            if (brick.getVisible() && ball.hit(brick)) {
                // Figure out which side of the ball hit the brick.
                final Collision collision = new Collision(ball, brick);
                // Flip velocities based on collision info.
                ball.bounce(collision);

                // Make the brick invisible
                brick.setVisible(false);
                addToScore(HIT_BRICK); // Award points for breaking the brick.
                break; // Only break one brick per update.
            }
        }
        
        // Check whether ball has hit the paddle.
        if (ball.hit(paddle)) {
            // Figure out which side of the ball hit the paddle.
            final Collision collision = new Collision(ball, paddle);
            // Flip velocities based on collision info.
            ball.bounce(collision);
        }
    }

    // This is how the Model talks to the View
    // Whenever the Model changes, this method calls the update method in
    // the View. It needs to run in the JavaFX event thread, and Platform.runLater 
    // is a utility that makes sure this happens even if called from the
    // runGame thread
    private synchronized void updateView() {
        Platform.runLater(view::update);
    }
    
    
    // Methods for accessing and updating values
    // these are all synchronized so that they can be called by the main thread
    // or the animation thread safely
    
    // Change whether the game is in the finished state.
    synchronized void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    /**
     * Toggles fast game speed, turns fast mode on if it's off and off if it's on.
     */
    synchronized void toggleFast() {
        fast = !fast;
    }
    
    // Return game speed - false is normal speed, true is fast
    synchronized Boolean getFast() {
        return fast;
    }

    // Setters and getters for the left and right keys.
    synchronized void setLeftHeld(Boolean value) {
        leftHeld = value;
    }
    synchronized Boolean getLeftHeld() {
        return leftHeld;
    }
    synchronized void setRightHeld(Boolean value) {
        rightHeld = value;
    }
    synchronized Boolean getRightHeld() {
        return rightHeld;
    }

    // Return paddle object
    synchronized GameObj getPaddle() {
        return paddle;
    }
    
    // return ball object
    synchronized GameObj getBall() {
        return ball;
    }
    
    // return bricks
    synchronized Level getLevel() {
        return level;
    }
    
    // return score
    synchronized int getScore() {
        return score;
    }
    
    // update the score
    private synchronized void addToScore(int n) {
        score += n;        
    }

    // return number of lives
    synchronized int getLives() {
        return lives;
    }
}   
    