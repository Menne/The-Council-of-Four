package server.model.stateMachine.bonusStates;

import java.util.ArrayList;
import java.util.List;

import server.model.Game;
import server.model.actions.Action;
import server.model.stateMachine.State;

public class InteractiveBonusState implements State {

	private State previousState;

	public InteractiveBonusState(State previousState) {
		this.previousState=previousState;
	}
	
	@Override
	public State mainActionTransition(Game game) {
		return this;
	}
	
	@Override
	public State moveToNextTransition(Game game) {
		return this.previousState.mainActionTransition(game);
	}

	@Override
	public List<Action> getAcceptableActions(Game game) {
		return new ArrayList<>();
	}

	@Override
	public void updateClients(Game game) {
		// TODO Auto-generated method stub
		
	}

}
