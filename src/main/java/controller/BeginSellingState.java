package controller;

import java.util.List;

import model.Game;
import model.actions.Action;

public class BeginSellingState implements State {

	@Override
	public State sellActionTransition() {
		
		return new EndSellingState();
	}

	@Override
	public State moveToNextTransition(Game game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> getAcceptableActions(Game game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString(Game game) {
		// TODO Auto-generated method stub
		return null;
	}

}
