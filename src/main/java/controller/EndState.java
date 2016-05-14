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

<<<<<<< HEAD
	@Override
	public void act(Action action, Game game) {
		game.normalNextPlayer();
		game.setState(new BeginState());
		game.getState().act(action, game);
	}
=======
>>>>>>> branch 'mvc' of https://Squalipa@bitbucket.org/Scanna/the-council-of-four.git

	@Override
	public List<Action> getAcceptableActions(Game game) {
		List<Action> acceptableActions=new ArrayList<Action>();
		acceptableActions.add(new MoveToNext(game));
		return acceptableActions;
	}

}
