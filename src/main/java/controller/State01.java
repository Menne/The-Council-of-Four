package controller;

import model.Game;
import model.actions.AdditionalMainAction;
import model.actions.MainAction;
import model.actions.QuickAction;
import view.ErrorNotify;

public class State01 implements State {

	public void handleAction(Game game, MainAction action) {
		game.notifyObserver(new ErrorNotify());				
	}

	
	public void handleAction(Game game, QuickAction action) {
		if(action.executeAction()){
			game.setState(new StartEndState());
			game.nextPlayer();
		}		
	}
	
	
	public void handleAction(Game game, AdditionalMainAction action) {
		if(action.executeAction())
			game.setState(new State10());
		
	}
}


