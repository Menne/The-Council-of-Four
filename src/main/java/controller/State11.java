package controller;

import model.actions.Action;
import model.actions.MainAction;

public class State11 implements State {

	@Override
	public void handleAction(GameLogic gameLogic, Action action) {
		if(action instanceof MainAction)
			if(action.executeAction())
				gameLogic.setState(new State01());  			
		else
			if(action.executeAction())
				gameLogic.setState(new State10());

	}

}
