package controller;

import java.util.List;

import model.Game;
import model.actions.Action;

public interface State{

	public default void handleAction(Game game, Action action){
		System.out.println("richiamato metodo default");
	}
	
	public List<Action> getAcceptableActions(Game game);

	
	
}
