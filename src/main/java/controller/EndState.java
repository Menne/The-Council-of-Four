package controller;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.actions.Action;
import model.actions.MoveToNext;

public class EndState implements State {

	@Override
	public State mainActionTransition() throws RuntimeException{
		
		throw new RuntimeException("There are not such transictions for this state");
	}

	@Override
	public State quickActionTransition()  throws RuntimeException{
		
		throw new RuntimeException("There are not such transictions for this state");
	}

	@Override
	public State additionalMainActionTransition()  throws RuntimeException{
		
		throw new RuntimeException("There are not such transictions for this state");
	}

	@Override
	public State moveToNextTransition() {
		
		return new BeginState();
	}

	@Override
	public State pickPoliticsCardTransition() throws RuntimeException{
		
		throw new RuntimeException("There are not such transictions for this state");
	}



	@Override
	public List<Action> getAcceptableActions(Game game) {
		List<Action> acceptableActions=new ArrayList<Action>();
		acceptableActions.add(new MoveToNext(game));
		return acceptableActions;
	}

	@Override
	public String toString(Game game) {
		// TODO Auto-generated method stub
		return null;
	}

}
