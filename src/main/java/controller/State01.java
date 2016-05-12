package controller;

import model.actions.AdditionalMainAction;
import model.actions.MainAction;
import model.actions.QuickAction;
import view.ErrorNotify;

public class State01 implements State {

	public void handleAction(GameLogic gameLogic, MainAction action) {
		gameLogic.getGame().notifyObserver(new ErrorNotify());				
	}

	
	public void handleAction(GameLogic gameLogic, QuickAction action) {
		if(action.executeAction()){
			gameLogic.setState(new StartEndState());
			gameLogic.getGame().nextPlayer();
		}		
	}
	
	
	public void handleAction(GameLogic gameLogic, AdditionalMainAction action) {
		if(action.executeAction())
			gameLogic.setState(new State10());
		
	}
}


