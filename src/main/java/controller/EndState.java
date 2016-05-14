package controller;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.actions.Action;
import model.actions.MoveToNext;

public class EndState implements NormalTurnState {

	@Override
	public NormalTurnState mainActionTransition() throws RuntimeException{
		
		throw new RuntimeException("There are not such transictions for this state");
	}

	@Override
	public NormalTurnState quickActionTransition()  throws RuntimeException{
		
		throw new RuntimeException("There are not such transictions for this state");
	}

	@Override
	public NormalTurnState additionalMainActionTransition()  throws RuntimeException{
		
		throw new RuntimeException("There are not such transictions for this state");
	}

	@Override
	public NormalTurnState moveToNextTransition() {
		
		return new BeginState();
	}

	@Override
	public NormalTurnState pickPoliticsCardTransition() throws RuntimeException{
		
		throw new RuntimeException("There are not such transictions for this state");
	}


	@Override
	public List<Action> getAcceptableActions(Game game) {
		List<Action> acceptableActions=new ArrayList<Action>();
		acceptableActions.add(new MoveToNext(game));
		return acceptableActions;
	}

}
