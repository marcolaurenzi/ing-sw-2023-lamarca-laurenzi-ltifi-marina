package it.polimi.ingsw.Model;

/**
 * This enum is used to set the type of the items used in the game
 */
public enum TypeEnum {
    CATS, BOOKS, GAMES, FRAMES, TROPHIES, PLANTS;

    public String toString() {
		return switch (this) {
			case CATS -> "CATS";
			case BOOKS -> "BOOKS";
			case GAMES -> "GAMES";
			case FRAMES -> "FRAMES";
			case TROPHIES -> "TROPHIES";
			case PLANTS -> "PLANTS";
			default -> null;
		};
    }
}
