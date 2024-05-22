import javafx.scene.input.KeyEvent;

/** Converts key interactions from the user (received by the {@link View} object) into commands for the game (in the
 * {@link Model} object). This class's methods are how the View talks to the Controller AND how the Controller talks to
 * the Model.
 * @author Seth Humphries
 * @version 1.0
 */
class Controller {
    private final Model model; // Instance variable for the Model component of MVC.

    /**
     * Create a controller for the provided model.
     * @param model Model this controller will send commands to.
     */
    Controller(Model model) {
        this.model = model;
        Debug.trace("Controller::<constructor>");
    }

    /**
     * Called by the View to respond to key presses in the GUI, then converts the key-presses into commands which are
     * run in the model.
     * @param event Which key has been pressed.
     */
    void userKeyPressInteraction(KeyEvent event) {
        // Print a debugging message to show a key has been pressed.
        Debug.trace("Controller::userKeyPressInteraction: keyCode = " + event.getCode());
        switch (event.getCode()) {
            case LEFT -> model.setLeftHeld(true); // Left Arrow
            case RIGHT -> model.setRightHeld(true); // Right arrow
            case F -> model.setFast(true); // Very fast ball movement
            case N -> model.setFast(false); // Normal-speed ball movement
            case S -> model.setGameState("finished"); // stop the game
        }
    }

    /**
     * Called by the View to respond to keys being released in the GUI.
     * @param event Which key has been released.
     */
    void userKeyReleaseInteraction(KeyEvent event) {
        Debug.trace("Controller::userKeyReleaseInteraction: keyCode = " + event.getCode());
        switch (event.getCode()) {
            case LEFT -> model.setLeftHeld(false);
            case RIGHT -> model.setRightHeld(false);
        }
    }
}
