package server.model.stateMachine;

import java.util.Arrays;
import java.util.List;

import server.model.Game;
import server.model.actions.Action;
import server.model.actions.MoveToNext;
import server.model.actions.standardActions.AdditionalMainAction;
import server.model.actions.standardActions.ChangePermitTiles;
import server.model.actions.standardActions.ElectCouncillorByAssistant;
import server.model.actions.standardActions.EngageAssistant;
import server.serverNotifies.AvailableActionsServerNotify;

/**
 * Models the state in which the current player has only the possibility to do a quick action 
 * @author andreapasquali
 *
 */
public class State01 implements State {

	/**
	 * coordinates the transition caused by a quick action
	 * if the current player is not the last one, it returns a new Begin State changing the current player
	 * else it returns a new Selling State
	 */
	@Override
	public State quickActionTransition(Game game) {
		game.notifyObserver(new AvailableActionsServerNotify(Arrays.asList(), 
				Arrays.asList(game.getCurrentPlayer()), 
				"Ok, your turn is over now. I will notify you when it will be your turn again"));
		if (game.getPlayers().size()==1){
			game.nextPlayer();
			return new EndGameState();
		}
		
		if (!game.getCurrentPlayer().equals(game.lastPlayer())){
			game.nextPlayer();
			return new BeginState();
		}
		else {
			game.startMarket();
			return new SellingState();
		}
	}

	/**
	 *returns a new State10 after an additional main action bonus.
	 */
	@Override
	public State additionalMainActionTransition() {
		return new State10();
	}

	/**
	 *if current player is the last one, it returns a new BeginState,
	 *else it returns a new SellingState
	 */
	@Override
	public State moveToNextTransition(Game game) {
		game.notifyObserver(new AvailableActionsServerNotify(Arrays.asList(), 
				Arrays.asList(game.getCurrentPlayer()), 
				"Ok, I will notify you when it will be your turn again"));
		if (game.getPlayers().size()==1){
			game.nextPlayer();
			return new EndGameState();
		}
		if (!game.getCurrentPlayer().equals(game.lastPlayer())){
			game.nextPlayer();
			return new BeginState();
		}
		else {
			game.startMarket();
			return new SellingState();
		}
			
		
	}
	
	/**
	 * returns a list of acceptable actions of this state
	 */
	@Override
	public List<Action> getAcceptableActions(Game game) {
		return Arrays.asList(
				new EngageAssistant(),
				new ChangePermitTiles(),
				new ElectCouncillorByAssistant(),
				new AdditionalMainAction(),
				new MoveToNext());
	}

	/**
	 * sends GameTableNotify, PlayerNotify, AvailableActionNotify to the clients
	 */
	@Override
	public void updateAvailableActions(Game game) {
		game.notifyObserver(new AvailableActionsServerNotify(game.getState().getAcceptableActions(game), 
				Arrays.asList(game.getCurrentPlayer()), game.getCurrentPlayer().getName() +
				", you have only quick actions available"));
	}
	
}


