package controller;

import model.actions.PickPoliticsCard;

public class StartEndState implements State{

	public void handleAction(GameLogic game, PickPoliticsCard action) {
		action.executeAction();
	}
}
