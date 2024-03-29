package it.polimi.ingsw.View;

import it.polimi.ingsw.App;
import javafx.application.Application;

/**
 * The ViewController interface represents the view for classes that act as controllers.
 */
public interface ViewController {
    /**
     *prints an error message
     * @param serverCrashed the error message
     */
    void printError(String serverCrashed);
}
