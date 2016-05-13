package controller;

import java.util.List;

import model.Game;
import model.actions.Action;

public interface State{

	public default void handleAction(Game game, Action action) throws IllegalArgumentException{
		throw new IllegalArgumentException("Wrong action has been passed to the controller");
	}
	
	public List<Action> getAcceptableActions(Game game);
	
}
