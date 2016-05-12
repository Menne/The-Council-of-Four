package controller;

import model.actions.*;

public class State11 implements State {


	public void handleAction(GameLogic gameLogic, MainAction action) {
		if(action.executeAction())
			gameLogic.setState(new State01());  			
	}


	public void handleAction(GameLogic gameLogic, QuickAction action) {
		if(action.executeAction())
			gameLogic.setState(new State10());		
	}
	
/*	public void handleAction(GameLogic gameLogic, AdditionalMainActionBonus action){
		if(action.executeAction())
			gameLogic.setState(new state);
	}*/
}
