package it.polimi.ingsw.View;

class SharedObject {
    private int variable;

    public SharedObject() {
        variable = 0;
    }

    public synchronized void setVariable() {
        this.variable = 1;
        notifyAll();
    }

    public synchronized void waitForVariable() throws InterruptedException {
        while (variable != 1) {
            wait();
        }
    }
}
