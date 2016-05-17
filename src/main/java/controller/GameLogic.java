package controller;

import model.Game;
import model.actions.Action;
import observerPattern.Observer;
import view.GameNotify;
import view.View;

public class GameLogic implements Observer<Action>{
	
	private final Game game;
	
	public GameLogic(Game game, View view){
		this.game=game;
		view.registerObserver(this);
		game.notifyObserver(new GameNotify(game));
	}
	


	public Game getGame() {
		return game;
	}

	
	@Override
	public void update(Action action){
		action.executeAction();
	}

}
	
