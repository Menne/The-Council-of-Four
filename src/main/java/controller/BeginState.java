package controller;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.actions.Action;
import model.actions.PickPoliticsCard;

public class BeginState implements NormalTurnState {

	@Override
	public NormalTurnState mainActionTransition() throws RuntimeException{
		throw new RuntimeException("There are not such transictions for this state");
	}

	@Override
	public NormalTurnState quickActionTransition() throws RuntimeException{
		throw new RuntimeException("There are not such transictions for this state");
	}

	@Override
	public NormalTurnState additionalMainActionTransition() throws RuntimeException{
		throw new RuntimeException("There are not such transictions for this state");
	}

	@Override
	public NormalTurnState moveToNextTransition() throws RuntimeException{
		throw new RuntimeException("There are not such transictions for this state");
	}
	
	@Override
	public NormalTurnState pickPoliticsCardTransition() {
		return new State11();
	}	
	

	@Override
	public List<Action> getAcceptableActions(Game game) {
		List<Action> acceptableAction= new ArrayList<Action>();
		acceptableAction.add(new PickPoliticsCard(game));
		
		return acceptableAction;
	}

	

}
