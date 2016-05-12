package controller;

import model.Game;
import model.actions.MainAction;
import model.actions.QuickAction;
import view.ErrorNotify;

public class State10 implements State{

	public void handleAction(Game game, MainAction action) {
		if(action.executeAction()){
				game.setState(new StartEndState());
				game.nextPlayer();
			}		
	}
	
	public void handleAction(Game game, QuickAction action){
		game.notifyObserver(new ErrorNotify());
	}
}
