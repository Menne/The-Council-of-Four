package controller;

import model.actions.PickPoliticsCard;

public class StartEndState implements State{

	public void handleAction(GameLogic gameLogic, PickPoliticsCard action) {
		action.executeAction();
	}
}
