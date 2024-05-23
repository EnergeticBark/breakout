import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.w3c.dom.Text;

/** Creates and manages the GUI for the application.
 * It doesn't know anything about the game itself, it just displays the current state of the {@link Model}, and handles
 * user input.
 * @author Seth Humphries
 * @version 1.0
 */
class View {
    // Format of the text at the top of the screen. Includes width for scores of up to 8 digits.
    public static final String INFO_TEXT_FORMAT = "Lives: %-6d Score: %8d";
    public static final String GAME_OVER_TEXT_FORMAT = "Your score was: %8d\nWould you like to play again?";

    // Variables for components of the user interface
    private final int width;  // Width of window.
    private final int height; // Height of window.

    private Canvas canvas;  // Canvas to draw game on.
    private Label infoText; // Info at top of screen.
    private Dialog<ButtonType> gameOverDialog;

    // The other parts of the model-view-controller setup
    private Controller controller;
    private final Model model;
   
    // constructor method - we get told the width and height of the window
    View(Model model) {
        Debug.trace("View::<constructor>");
        this.model = model;
        width = model.width;
        height = model.height;
    }

    // start is called from the Main class, to start the GUI up
    
    void start(Stage window) {
        // breakout is basically one big drawing canvas, and all the objects are
        // drawn on it as rectangles, except for the text at the top - this
        // is a label which sits 'in front of' the canvas.
        
        // Note that it is important to create control objects (Pane, Label,Canvas etc.)
        // here not in the constructor (or as initialisations to instance variables),
        // to make sure everything is initialised in the right order
        // user interface objects
        // basic layout pane
        Pane pane = new Pane(); // a simple layout pane

        // canvas object - we set the width and height here (from the constructor), 
        // and the pane and window set themselves up to be big enough
        canvas = new Canvas(width, height);
        pane.getChildren().add(canvas); // add the canvas to the pane
        
        // infoText box for the score - a label which we position in front of
        // the canvas (by adding it to the pane after the canvas)
        infoText = new Label(String.format(INFO_TEXT_FORMAT, model.getLives(), model.getScore()));
        infoText.setTranslateX(10);       // these commands set the position of the text box
        infoText.setTranslateY(10);       // (measuring from the top left corner)
        pane.getChildren().add(infoText); // add label to the pane

        // dialog box for when the game is over (player ran out of lives)
        gameOverDialog = new Dialog<>();
        gameOverDialog.setTitle("Game over!");
        gameOverDialog.setContentText(String.format(GAME_OVER_TEXT_FORMAT, model.getScore()));
        gameOverDialog.getDialogPane().getButtonTypes().add(ButtonType.NO);
        gameOverDialog.getDialogPane().getButtonTypes().add(ButtonType.YES);

        // Make a new JavaFX Scene, containing the complete GUI
        Scene scene = new Scene(pane);   
        scene.getStylesheets().add("breakout.css"); // tell the app to use our css file

        // Add an event handler for key presses.
        scene.setOnKeyPressed(keyEvent -> {
            // Send the event to the controller
            controller.userKeyPressInteraction(keyEvent);
        });

        // Add an event handler for key releases.
        scene.setOnKeyReleased(keyEvent -> {
            // Send the event to the controller
            controller.userKeyReleaseInteraction(keyEvent);
        });

        // put the scene in the window and display it
        window.setScene(scene);
        window.setTitle("Breakout");
        window.show();
    }

    void setController(Controller controller) {
        this.controller = controller;
    }

    // drawing the game image
    private void drawPicture() {
        // the game loop is running 'in the background' so we have
        // added the following line to make sure it doesn't change
        // the model in the middle of us updating the image
        synchronized (model) {
            // get the 'paint brush' to draw on the canvas
            GraphicsContext gc = canvas.getGraphicsContext2D();

            // clear the whole canvas to white
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, width, height);
            
            // draw the paddle and ball
            displayGameObj(gc, model.getBall());   // Display the ball
            displayGameObj(gc, model.getPaddle()); // Display the paddle

            // *[2]****************************************************[2]*
            // * Display the bricks that make up the game                 *
            // * Code to display bricks from the brick array              *
            // * Only a visible brick is to be displayed                  *
            // ************************************************************
            for (GameObj brick: model.getLevel().getBricks()) {
                if (brick.getVisible()) {
                    displayGameObj(gc, brick);
                }
            }

            // update the score
            infoText.setText(String.format(INFO_TEXT_FORMAT, model.getLives(), model.getScore()));

            if (model.getGameFinished() && !gameOverDialog.isShowing()) {
                gameOverDialog.setContentText(String.format(GAME_OVER_TEXT_FORMAT, model.getScore()));
                gameOverDialog.showAndWait().ifPresent(response -> {
                    controller.gameOverDialogInteraction(response);
                });
            }
        }
    }

    // Display a game object - it is just a rectangle on the canvas
    private void displayGameObj(GraphicsContext gc, GameObj go) {
        gc.drawImage(go.getSprite(), go.left(), go.top());
    }

    // This is how the Model talks to the View
    // This method gets called BY THE MODEL, whenever the model changes
    // It has to do whatever is required to update the GUI to show the new game position
    void update() {
        Debug.trace("Update");
        drawPicture(); // Re draw game
    }
}
