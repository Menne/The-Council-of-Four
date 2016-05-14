package controller;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.actions.Action;

public class BeginState implements State {

	@Override
	public State mainActionTransition() throws RuntimeException{
		throw new RuntimeException("There are not such transictions for this state");
	}

	@Override
	public State quickActionTransition() throws RuntimeException{
		throw new RuntimeException("There are not such transictions for this state");
	}

	@Override
	public State additionalMainActionTransition() throws RuntimeException{
		throw new RuntimeException("There are not such transictions for this state");
	}

	@Override
	public State nullActionTransition() throws RuntimeException{
		throw new RuntimeException("There are not such transictions for this state");
	}

	@Override
	public void act(Action action, Game game) {
		game.pickPoliticsCard();
		game.setState(new State11());
		game.getState().act(action, game);
	}

	@Override
	public List<Action> getAcceptableActions(Game game) {
		return new ArrayList<Action>();
	}

}
