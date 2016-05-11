package controller;

import model.actions.Action;
import model.actions.MainAction;
import view.ErrorNotify;

public class State10 implements State{

	@Override
	public void handleAction(GameLogic gameLogic, Action action) {
		if(action instanceof MainAction){
			if(action.executeAction()){
				gameLogic.setState(new StartEndState());
				gameLogic.getGame().nextPlayer();
			}
		else
			gameLogic.getGame().notifyObserver(new ErrorNotify());
		
		}		
		
	}
}
