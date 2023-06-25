package it.polimi.ingsw.Model;

import java.io.Serializable;

/**
 * This class represents the coordinates of a point on a grid.
 */
public class Coordinates implements Serializable {
    final int x;
    final int y;

    /**
     * Constructs a new instance of Coordinates with the specified x and y values.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of the point.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the point.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Checks if this Coordinates object is equal to the specified object.
     * Two Coordinates objects are considered equal if they have the same x and y values.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Coordinates && ((Coordinates) obj).getX() == this.getX() && (((Coordinates) obj).getY()) == this.getY();
    }
}
