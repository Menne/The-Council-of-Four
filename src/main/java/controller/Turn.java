package controller;

import model.actions.Action;

public abstract class Turn {
	
	
	protected final GameLogic gameLogic;
	
	public Turn(GameLogic gameLogic) {
		this.gameLogic=gameLogic;
	}
	
	public abstract void executeActionOfTheTurn(Action action);


}