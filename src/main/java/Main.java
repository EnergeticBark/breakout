import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Used to start the Breakout game.
 * @author Seth Humphries
 * @version 1.0
 */
public class Main extends Application {
    // The 'main' method - this is only used when launching from the command line.
    public static void main(String[] args) {
        // 'launch' initialises the system and then calls 'start'
        // (When running in BlueJ, the menu option 'Run JavaFX Application'
        // calls 'start' itself)
        launch(args);
    }

    // the 'start' method - this creates the Model, View and Controller objects and
    // makes them talk to each other, it then sets up the user interface (in the View
    // object) and starts the game running (in the Model object)
    public void start(Stage window) {
        int H = 400; // Height of game window (in pixels)
        int W = 300; // Width  of game window (in pixels)

        // set up debugging and print initial debugging message
        Debug.set(false); // change this to 'false' to stop breakout printing messages
        Debug.trace("Main::start: Breakout starting");

        // Create the Model, View and Controller objects
        Model model = new Model(W,H);
        View view = new View(model);
        Controller controller = new Controller(model);

        // Link them together, so they can talk to each other
        // Communication in MVC is one-way, so don't store the view in the controller, etc.
        model.setView(view);
        view.setController(controller);

        // start up the game interface (the View object, passing it the window
        // object that JavaFX passed to this method, and then tell the model to
        // start the game
        view.start(window);
        model.startGame();

        // application is now running - print a debug message to say so
        Debug.trace("Main::start: Breakout running");
    }
}
