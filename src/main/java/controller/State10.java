package controller;

import model.actions.MainAction;
import model.actions.QuickAction;
import view.ErrorNotify;

public class State10 implements State{

	public void handleAction(GameLogic gameLogic, MainAction action) {
		if(action.executeAction()){
				gameLogic.setState(new StartEndState());
				gameLogic.getGame().nextPlayer();
			}		
	}
	
	public void handleAction(GameLogic gameLogic, QuickAction action){
		gameLogic.getGame().notifyObserver(new ErrorNotify());
	}
}
