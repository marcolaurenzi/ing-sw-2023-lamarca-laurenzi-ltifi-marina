package it.polimi.ingsw.Model;

/**
 * This enum is used to set the type of the items used in the game
 */
public enum TypeEnum {
    CATS, BOOKS, GAMES, FRAMES, TROPHIES, PLANTS;

    public String toString() {
    	switch(this) {
    	case CATS:
    		return "CATS";
    	case BOOKS:
    		return "BOOKS";
    	case GAMES:
    		return "GAMES";
    	case FRAMES:
    		return "FRAMES";
    	case TROPHIES:
    		return "TROPHIES";
    	case PLANTS:
    		return "PLANTS";
    	default:
    		return null;
    	}
    }
}
