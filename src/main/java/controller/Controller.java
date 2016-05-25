package controller;

import model.Game;
import model.actions.Action;
import observerPattern.Observer;
import view.GameNotify;

public class Controller implements Observer<Action>{
	
	private final Game game;
	
	public Controller(Game game){
		this.game=game;
		game.notifyObserver(new GameNotify(game));
	}
	


	public Game getGame() {
		return game;
	}

	
	@Override
	public void update(Action action){
		System.out.println("player almost Added");
		action.executeAction(this.game);
	}

}
	
