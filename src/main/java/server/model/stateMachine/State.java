package server.model.stateMachine;

import java.util.List;

import server.model.Game;
import server.model.actions.Action;

/**
 * The interface for the State machine pattern.
 * We have four states for the standard game phase, other two states for the market phase and other two states to handle interactive bonus.
 * In each of these states the current player can only do the available actions of the state.
 * All concrete state of the pattern will override just the methods for which the relative transition is allowed in that state.
 * @author cg31
 *
 */
public interface State{
	
	/**
	 * In addition to return the next state, it will change the current player if it's necessary.
	 * @param game current game.
	 * @return the next state after a main action execution
	 */
	public default State mainActionTransition(Game game){
		
		throw new IllegalStateException("There are not such transictions for this state");
	}

	/**
	 * In addition to return the next state, it will change the current player if it's necessary
	 * @param game current game
	 * @return the next state after a quick action execution
	 */
	public default State quickActionTransition(Game game){
		
		throw new IllegalStateException("There are not such transictions for this state");
	}
	
	/**
	 * This transition is allowed only in the begin state 
	 * @return the next state after a PickPoliticsCard execution
	 */
	public default State pickPoliticsCardTransition() {
		
		throw new IllegalStateException("There are not such transictions for this state");
	}

	/**
	 * Market phase transition
	 * @return the next state after a SellAction execution
	 */
	public default State sellActionTransition(Game game) {
		
		throw new IllegalStateException("There are not such transictions for this state");
	}

	/**
	 * Market phase transition
	 * @return the next state after a BuyAction execution
	 */
	public default State buyActionTransition(Game game){
		
		throw new IllegalStateException("There are not such transictions for this state");
	}

	/**
	 * It's the transition relative to the skip action. It will change the current player.
	 * @return the next state after a MoveToNext execution
	 */
	public State moveToNextTransition(Game game);
	
	/**
	 * Additional main action is a particular quick action
	 * @return the next state after an Additional Main Action execution
	 */
	public default State additionalMainActionTransition(){
		
		throw new IllegalStateException("There are not such transictions for this state");
	}
	
	/**
	 * Transition relative to the three interactive bonuses
	 * @return the next state after an interactive bonus execution
	 */
	public default State interactiveBonusTransition(){
		
		throw new IllegalStateException("There are not such transictions for this state");
	}
	
	/**
	 * @param game is the current game
	 * @return the available actions of the state
	 */
	public List<Action> getAcceptableActions(Game game);
	
	/**
	 * Sends the list of available actions of a player depending on the state in which the player is
	 * @param game is the current game
	 */
	public void updateAvailableActions(Game game);


	

}
