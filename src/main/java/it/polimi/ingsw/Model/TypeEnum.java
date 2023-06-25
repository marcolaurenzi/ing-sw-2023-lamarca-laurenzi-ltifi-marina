package it.polimi.ingsw.Model;

/**
 * This enum is used to set the type of the items used in the game.
 */
public enum TypeEnum {
	/**
	 * Represents the type "CATS".
	 */
	CATS,
	/**
	 * Represents the type "BOOKS".
	 */
	BOOKS,
	/**
	 * Represents the type "GAMES".
	 */
	GAMES,
	/**
	 * Represents the type "FRAMES".
	 */
	FRAMES,
	/**
	 * Represents the type "TROPHIES".
	 */
	TROPHIES,
	/**
	 * Represents the type "PLANTS".
	 */
	PLANTS;

	/**
	 * Returns the string representation of the type.
	 *
	 * @return the string representation of the type
	 */
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
