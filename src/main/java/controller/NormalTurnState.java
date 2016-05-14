package controller;

import java.util.List;

import model.Game;
import model.actions.Action;

public interface NormalTurnState{

	public NormalTurnState mainActionTransition();
	
	public NormalTurnState quickActionTransition();
	
	public NormalTurnState additionalMainActionTransition();
	
	public NormalTurnState moveToNextTransition();
	
	public NormalTurnState pickPoliticsCardTransition();
	
	
	public List<Action> getAcceptableActions(Game game);
	
}
