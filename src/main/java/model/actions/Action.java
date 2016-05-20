package model.actions;

import model.Game;
import model.parser.ActionParserVisitor;

/**
 * It's the class that models the generic action.
 * All the actions will operate on the protected attribute inherited from this class.
 * @author Luca
 *
 */
public abstract interface Action {

	
	public abstract boolean executeAction(Game game);

	public abstract ActionParserVisitor setParser(Game game);
}
