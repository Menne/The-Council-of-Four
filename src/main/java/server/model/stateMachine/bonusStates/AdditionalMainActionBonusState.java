package server.model.stateMachine.bonusStates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import server.model.Game;
import server.model.actions.Action;
import server.model.stateMachine.State;
import server.view.notifies.MessageNotify;

public class AdditionalMainActionBonusState implements State {
	
	private State previousState;

	public AdditionalMainActionBonusState(State previousState) {
		this.previousState=previousState;
	}
	
	@Override
	public State mainActionTransition(Game game) {
		return this.previousState;
	}

	@Override
	public State moveToNextTransition(Game game) throws IllegalStateException{
		throw new IllegalStateException();
	}

	@Override
	public List<Action> getAcceptableActions(Game game) {
		return new ArrayList<Action>();
	}

	@Override
	public void updateClients(Game game) {
		game.notifyObserver(new MessageNotify("Congratulation! You have got an additional main action!", 
				Arrays.asList(game.getCurrentPlayer())));
	}

}
