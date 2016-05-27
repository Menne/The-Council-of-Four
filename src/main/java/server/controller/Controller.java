package server.controller;

import observerPattern.Observer;
import server.model.Game;
import server.model.actions.Action;

public class Controller implements Observer<Action>{
	
	private final Game game;
	
	public Controller(Game game){
		this.game=game;
	}
	


	public Game getGame() {
		return game;
	}

	
	@Override
	public void update(Action action){
		
		action.executeAction(this.game);
	}

}
	
