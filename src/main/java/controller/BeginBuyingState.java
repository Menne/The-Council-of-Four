package controller;

import java.util.List;

import model.Game;
import model.actions.Action;

public class BeginBuyingState implements State {

	@Override
	public State buyActionTransition() {
		
		return new EndBuyingState();
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
