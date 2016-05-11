package controller;

import model.actions.Action;

public interface State {

	public void handleAction(GameLogic gameLogic, Action action);
	
	
}
