package server.model.stateMachine;

import java.util.List;

import server.model.Game;
import server.model.actions.Action;

/**
 * The interface for the State machine pattern.
 * We have four states for the standard game phase, other two states for the market phase and other two states to handle interactive bonus.
 * In each of these states the current player can only do the available actions for the state.
 * @author Luca Scannapieco
 *
 */
public interface State{
	
	/**
	 * @param game current game.
	 * @return the next state after a main action execution
	 * @throws IllegalStateException if the state has no main actions as available actions
	 */
	public default State mainActionTransition(Game game){
		
		throw new IllegalStateException("There are not such transictions for this state");
	}

	/**
	 * @param game current game
	 * @return the next state after a quick action execution
	 * @throws IllegalStateException if the state has no quick actions as available actions
	 */
	public default State quickActionTransition(Game game){
		
		throw new IllegalStateException("There are not such transictions for this state");
	}
	
	/**
	 * @return the next state after a PickPoliticsCard execution
	 * @throws IllegalStateException if the state doesn't have PickPoliticsCard as available actions
	 */
	public default State pickPoliticsCardTransition() {
		
		throw new IllegalStateException("There are not such transictions for this state");
	}

	/**
	 * @return the next state after a SellAction execution
	 * @throws IllegalStateException if the state doesn't have SellAction as available actions
	 */
	public default State sellActionTransition(Game game) {
		
		throw new IllegalStateException("There are not such transictions for this state");
	}

	/**
	 * @return the next state after a BuyAction execution
	 * @throws IllegalStateException if the state doesn't have BuyAction as available actions
	 */
	public default State buyActionTransition(Game game){
		
		throw new IllegalStateException("There are not such transictions for this state");
	}

	/**
	 * @return the next state after a MoveToNext execution
	 * @throws IllegalStateException if the state doesn't have MoveToNext as available actions
	 */
	public State moveToNextTransition(Game game);
	
	/**
	 * @return the next state after an Additional Main Action execution
	 * @throws IllegalStateException if the state doesn't have AditionalMainAction as available actions
	 */
	public default State additionalMainActionTransition(){
		
		throw new IllegalStateException("There are not such transictions for this state");
	}
	
	/**
	 * @return the next state after an interactive bonus execution
	 * @throws IllegalStateException if the state doesn't have InteractiveBonus as available actions
	 */
	public default State interactiveBonusTransition(){
		
		throw new IllegalStateException("There are not such transictions for this state");
	}
	
	/**
	 * @param game is the current game
	 * @return the available actions for this state
	 */
	public List<Action> getAcceptableActions(Game game);
	
	/**
	 * Sends the list of available actions of a player depending on the state in which the player is
	 * @param game is the current game
	 */
	public void updateAvailableActions(Game game);


	

}
