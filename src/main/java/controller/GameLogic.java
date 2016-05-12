package controller;

import model.Game;
import model.GameState;
import model.actions.Action;
import observerPattern.Observer;
import view.View;

public class GameLogic implements Observer<Action>{
	
	private final Game game;
	private State state;
	private Action currentAction;
	private boolean IsActionArrived;
	
	
	public GameLogic(Game game, View view){
		this.game=game;
		view.registerObserver(this);
		this.play();
		this.IsActionArrived=false;
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
		this.currentAction=action;
		this.IsActionArrived=true;
	}
	
	
	public void play() {
		while(this.game.getGameState()!=GameState.RUNNING){
			while(!this.IsActionArrived);
			this.state.handleAction(this, this.currentAction);
			this.IsActionArrived=false;	
		}
	}
	
}
	
