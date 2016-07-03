package server.model.actions;

import server.model.Game;

/**
 * The class that models the generic main actions of the game.
 * @author Luca
 *
 */
public interface MainAction extends Action {
	
	public default void nextState(Game game){
		game.setState(game.getState().mainActionTransition(game));
		game.getState().updateAvailableActions(game);
	}
	
}
