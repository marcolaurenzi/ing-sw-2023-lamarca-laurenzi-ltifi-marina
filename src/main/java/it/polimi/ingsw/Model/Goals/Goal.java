package it.polimi.ingsw.Model.Goals;

import it.polimi.ingsw.Model.BookShelf;

public interface Goal {
    public boolean isAchieved(BookShelf bookShelf);
}
