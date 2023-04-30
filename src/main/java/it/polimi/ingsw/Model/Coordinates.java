package it.polimi.ingsw.Model;

import java.io.Serializable;

public class Coordinates implements Serializable {
    final int x;
    final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
