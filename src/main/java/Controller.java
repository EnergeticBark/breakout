// The breakout controller converts key presses from the user (received by the View object)
// into commands for the game (in the Model object)

// we need to use on JavaFX class
import javafx.scene.input.KeyEvent;

public class Controller {
    // instance variables for the two other components of the MVC model
    public Model model;

    // we don't really need a constructor method, but include one to print a
    // debugging message if required
    public Controller() {
        Debug.trace("Controller::<constructor>");
    }
  
    // This is how the View talks to the Controller
    // AND how the Controller talks to the Model
    // This method is called by the View to respond to key presses in the GUI
    // The controller's job is to decide what to do. In this case it converts
    // the key-presses into commands which are run in the model
    public void userKeyPressInteraction(KeyEvent event) {
        // print a debugging message to show a key has been pressed
        Debug.trace("Controller::userKeyPressInteraction: keyCode = " + event.getCode());
    
        // KeyEvent objects have a method getCode which tells us which key has been pressed.
        // KeyEvent also provides variables LEFT, RIGHT, F, N, S (etc.) which are the codes
        // for individual keys. So you can add keys here just by using their name (which you
        // can find out by googling 'JavaFX KeyCode')
        switch (event.getCode()) {
            case LEFT -> model.setLeftHeld(true); // Left Arrow
            case RIGHT -> model.setRightHeld(true); // Right arrow
            case F -> model.setFast(true); // Very fast ball movement
            case N -> model.setFast(false); // Normal-speed ball movement
            case S -> model.setGameState("finished"); // stop the game
        }
    }

    public void userKeyReleaseInteraction(KeyEvent event) {
        Debug.trace("Controller::userKeyReleaseInteraction: keyCode = " + event.getCode());
        switch (event.getCode()) {
            case LEFT -> model.setLeftHeld(false);
            case RIGHT -> model.setRightHeld(false);
        }
    }
}
