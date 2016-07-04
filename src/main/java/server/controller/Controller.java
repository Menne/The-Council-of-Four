package server.controller;

import observerPattern.Observer;
import server.model.Game;
import server.model.actions.Action;

/**
 * Controller of the MVC on the server side
 * @author cg31
 *
 */
public class Controller implements Observer<Action>{
	
	private final Game game;
	
	/**
	 * @param game The game on which the controller will execute the actions
	 */
	public Controller(Game game){
		this.game=game;
	}
	

	/**
	 * @return the game on which the controller executes the actions.
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * This method receives actions from the Server View and execute them.
	 */
	@Override
	public void update(Action action){
		
		action.executeAction(this.game);
		
	}

}
	
