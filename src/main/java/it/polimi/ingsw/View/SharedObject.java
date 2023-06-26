package it.polimi.ingsw.View;

/**
 * The SharedObject class represents an object that can be shared between multiple threads.
 */
class SharedObject {
    /**
     * The variable of the object.
     */
    private int variable;

    /**
     * Constructs a new SharedObject with the initial variable value set to 0.
     */
    public SharedObject() {
        variable = 0;
    }

    /**
     * Sets the value of the variable to 1 and notifies all threads waiting on this object.
     */
    public synchronized void setVariable() {
        this.variable = 1;
        notifyAll();
    }

    /**
     * Waits until the variable value is set to 1.
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    public synchronized void waitForVariable() throws InterruptedException {
        while (variable != 1) {
            wait();
        }
    }
}
