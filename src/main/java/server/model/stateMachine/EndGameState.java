package server.model.stateMachine;

import java.util.ArrayList;
import java.util.List;

import server.model.Game;
import server.model.actions.Action;

public class EndGameState implements State {

	@Override
	public State moveToNextTransition(Game game) {
		return this;
	}

	@Override
	public List<Action> getAcceptableActions(Game game) {
		return new ArrayList<>();
	}

	@Override
	public void updateAvailableActions(Game game) {
		return;
	}

}
