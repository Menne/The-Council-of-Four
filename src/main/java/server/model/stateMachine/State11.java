package server.model.stateMachine;

import java.util.Arrays;
import java.util.List;

import server.model.Game;
import server.model.actions.*;
import server.model.actions.standardActions.AcquirePermitTile;
import server.model.actions.standardActions.BuildByKing;
import server.model.actions.standardActions.BuildByPermitTile;
import server.model.actions.standardActions.ChangePermitTiles;
import server.model.actions.standardActions.ElectCouncillor;
import server.model.actions.standardActions.ElectCouncillorByAssistant;
import server.model.actions.standardActions.EngageAssistant;
import server.model.stateMachine.bonusStates.AdditionalMainActionBonusState;
import server.model.stateMachine.bonusStates.InteractiveBonusState;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.PlayerNotify;

/**
 * Models the state in which the current player has the possibility to do a main action or a quick action
 * @author andreapasquali
 *
 */
public class State11 implements State {

	@Override
	public State mainActionTransition(Game game) {
		return new State01();
	}

	@Override
	public State quickActionTransition(Game game) {
		return new State10();
	}
	
	@Override
	public State additionalMainActionTransition() {
		return new AdditionalMainActionBonusState(this);
	}
	
	@Override
	public State interactiveBonusTransition() {
		return new InteractiveBonusState(this);
	}
	
	/**
	 * returns a list of acceptable actions of this state
	 */
	@Override
	public List<Action> getAcceptableActions(Game game) {
		return Arrays.asList(
				new ElectCouncillor(),
				new AcquirePermitTile(),
				new BuildByPermitTile(),
				new BuildByKing(),
				new EngageAssistant(),
				new ChangePermitTiles(),
				new ElectCouncillorByAssistant());
	}

	/**
	 * sends GameTableNotify, PlayerNotify, AvailableActionNotify to the clients
	 */
	@Override
	public void updateClients(Game game) {
		game.notifyObserver(new PlayerNotify(game, game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new AvailableActionsNotify(game.getState().getAcceptableActions(game), 
				Arrays.asList(game.getCurrentPlayer()), game.getCurrentPlayer().getName() +
				", you have the following available actions. Choose one of them"));
	}

	/**
	 *if current player is the last one, it returns a new BeginState,
	 *else it returns a new SellingState
	 */
	@Override
	public State moveToNextTransition(Game game) {
		if (!game.getCurrentPlayer().equals(game.lastPlayer())){
			game.nextPlayer();
			return new BeginState();
		}
		else {
			game.startMarket();
			return new SellingState();
		}
	}

}
