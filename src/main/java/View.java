// The View class creates and manages the GUI for the application.
// It doesn't know anything about the game itself, it just displays
// the current state of the Model, and handles user input

// We import lots of JavaFX libraries (we may not use them all, but it
// saves us having to think about them if we add new code)
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View {
    // variables for components of the user interface
    public int width;       // width of window
    public int height;      // height of window

    // user interface objects
    public Pane pane;       // basic layout pane
    public Canvas canvas;   // canvas to draw game on
    public Label infoText;  // info at top of screen

    // The other parts of the model-view-controller setup
    public Controller controller;
    public final Model model;
   
    // constructor method - we get told the width and height of the window
    public View(Model model) {
        Debug.trace("View::<constructor>");
        this.model = model;
        width = model.width;
        height = model.height;
    }

    // start is called from the Main class, to start the GUI up
    
    public void start(Stage window) {
        // breakout is basically one big drawing canvas, and all the objects are
        // drawn on it as rectangles, except for the text at the top - this
        // is a label which sits 'in front of' the canvas.
        
        // Note that it is important to create control objects (Pane, Label,Canvas etc.)
        // here not in the constructor (or as initialisations to instance variables),
        // to make sure everything is initialised in the right order
        pane = new Pane();       // a simple layout pane
        pane.setId("Breakout");  // ID to use in CSS file to style the pane if needed
        
        // canvas object - we set the width and height here (from the constructor), 
        // and the pane and window set themselves up to be big enough
        canvas = new Canvas(width, height);
        pane.getChildren().add(canvas);     // add the canvas to the pane
        
        // infoText box for the score - a label which we position in front of
        // the canvas (by adding it to the pane after the canvas)
        infoText = new Label("BreakOut: Score = " + model.score);
        infoText.setTranslateX(70);  // these commands set the position of the text box
        infoText.setTranslateY(10);  // (measuring from the top left corner)
        pane.getChildren().add(infoText);  // add label to the pane

        // Make a new JavaFX Scene, containing the complete GUI
        Scene scene = new Scene(pane);   
        scene.getStylesheets().add("breakout.css"); // tell the app to use our css file

        // Add an event handler for key presses. By using 'this' (which means 'this 
        // view object itself') we tell JavaFX to call the 'handle' method (below)
        // whenever a key is pressed
        scene.setOnKeyPressed(keyEvent -> {
            // Send the event to the controller
            controller.userKeyPressInteraction(keyEvent);
        });

        scene.setOnKeyReleased(keyEvent -> {
            // Send the event to the controller
            controller.userKeyReleaseInteraction(keyEvent);
        });

        // put the scene in the window and display it
        window.setScene(scene);
        window.setTitle("Breakout");
        window.show();
    }
    
    // drawing the game image
    public void drawPicture() {
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
            displayGameObj(gc, model.ball);   // Display the ball
            displayGameObj(gc, model.paddle);    // Display the paddle

            // *[2]****************************************************[2]*
            // * Display the bricks that make up the game                 *
            // * Code to display bricks from the brick array              *
            // * Only a visible brick is to be displayed                  *
            // ************************************************************
            for (GameObj brick: model.bricks) {
                if (brick.visible) {
                    //displayGameObj(gc, brick);
                    gc.drawImage(brick.sprite, brick.topX, brick.topY);
                }
            }

            // update the score
            infoText.setText("BreakOut: Score = " + model.score);
        }
    }

    // Display a game object - it is just a rectangle on the canvas
    public void displayGameObj(GraphicsContext gc, GameObj go) {
        gc.drawImage(go.sprite, go.topX, go.topY);
    }

    // This is how the Model talks to the View
    // This method gets called BY THE MODEL, whenever the model changes
    // It has to do whatever is required to update the GUI to show the new game position
    public void update() {
        //Debug.trace("Update");
        drawPicture();                     // Re draw game
    }
}
