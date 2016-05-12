package controller;

import model.Game;
import model.actions.*;

public class State11 implements State {

//	private static final String="m1";

	public void handleAction(Game game, MainAction action) {
		if(action.executeAction())
			game.setState(new State01());  			
	}


	public void handleAction(Game game, QuickAction action) {
		if(action.executeAction())
			game.setState(new State10());		
	}
	
/*	public void handleAction(GameLogic gameLogic, AdditionalMainActionBonus action){
		if(action.executeAction())
			gameLogic.setState(new state);
	}*/
}
