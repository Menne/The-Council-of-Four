package controller;

import model.actions.Action;

public interface State{

	public default void handleAction(GameLogic gameLogic, Action action){
		System.out.println("richiamato metodo default");
	}

	
	
}
