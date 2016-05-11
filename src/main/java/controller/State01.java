package controller;

import model.actions.Action;
import model.actions.AdditionalMainAction;
import model.actions.QuickAction;
import view.ErrorNotify;

public class State01 implements State {

	@Override
	public void handleAction(GameLogic gameLogic, Action action) {
		if(action instanceof AdditionalMainAction)
			if(action.executeAction())
				gameLogic.setState(new State10());
			else 
				if(action instanceof QuickAction)
					if(action.executeAction()){
						gameLogic.setState(new StartEndState());
						gameLogic.getGame().nextPlayer();
					}
				else
					gameLogic.getGame().notifyObserver(new ErrorNotify());
					
				
	}
}


