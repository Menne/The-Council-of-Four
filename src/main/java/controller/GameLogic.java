package controller;

import model.Game;
import model.GameState;
import model.actions.Action;
import observerPattern.Observer;
import view.View;

public class GameLogic implements Observer<Action>{
	
	private final Game game;
	private State state;
	
	
	public GameLogic(Game game, View view){
		this.game=game;
		view.registerObserver(this);
		this.state=new StartEndState();
	}
	


	public Game getGame() {
		return game;
	}
	
	
	public void setState(State state) {
		this.state = state;
	}



	@Override
	public void update(Action action){
		this.state.handleAction(this, action);
	}
}
	
