package server.model.stateMachine.bonusStates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import server.model.Game;
import server.model.actions.Action;
import server.model.stateMachine.State;
import server.view.notifies.MessageNotify;

/**
 * This is a special state which is set when the payer gets the corresponding bonus
 * @author cg31
 *
 */
public class AdditionalMainActionBonusState implements State {
	
	private State previousState;

	/**
	 * Constructor of AdditionalMainActionBonusState
	 * @param previousState is the state from which the bonus was gotten
	 */
	public AdditionalMainActionBonusState(State previousState) {
		this.previousState=previousState;
	}
	
	@Override
	public State mainActionTransition(Game game) {
		return this.previousState;
	}

	@Override
	public State moveToNextTransition(Game game){
		throw new IllegalStateException();
	}

	@Override
	public List<Action> getAcceptableActions(Game game) {
		return new ArrayList<>();
	}

	@Override
	public void updateClients(Game game) {
		game.notifyObserver(new MessageNotify("Congratulation! You have got an additional main action!", 
				Arrays.asList(game.getCurrentPlayer())));
	}

}
