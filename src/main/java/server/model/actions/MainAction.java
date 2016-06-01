package server.model.actions;

import server.model.Game;

/**
 * The class that models the generic main actions of the game.
 * @author Luca
 *
 */
public abstract class MainAction implements Action {
	
	public void nextState(Game game){
		game.setState(game.getState().mainActionTransition(game));
	}
	
}
