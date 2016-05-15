package controller;

import java.util.List;

import model.Game;
import model.actions.Action;

public interface State{

	public State mainActionTransition();
	
	public State quickActionTransition();
	
	public State additionalMainActionTransition();
	
	public State moveToNextTransition();
	
	public State pickPoliticsCardTransition();
	
	
	public List<Action> getAcceptableActions(Game game);
	
	public String toString(Game game);
	
}
