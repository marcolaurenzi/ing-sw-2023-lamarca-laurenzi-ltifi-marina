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

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Coordinates && ((Coordinates) obj).getX() == this.getX() && (((Coordinates) obj).getY()) == this.getY();
    }
}
