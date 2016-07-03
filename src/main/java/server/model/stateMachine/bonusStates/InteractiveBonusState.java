package server.model.stateMachine.bonusStates;

import java.util.ArrayList;
import java.util.List;

import server.model.Game;
import server.model.actions.Action;
import server.model.stateMachine.State;

/**
 * This is a special state which is set during the execution of an interactive bonus to stop 
 * the game until the user has inserted all the parameters required by the bonus
 * @author cg31
 *
 */
public class InteractiveBonusState implements State {

	private State previousState;

	/**
	 * Constructor of InteractiveBonusState
	 * @param previousState is the state from which the bonus was gotten
	 */
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
	public void updateAvailableActions(Game game) {
		// TODO Auto-generated method stub
		
	}

}
